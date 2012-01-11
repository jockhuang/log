/*
   Copyright [2010] [Jock Huang]

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 */
package com.specl.log.server;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.specl.log.util.StringUtil;

/**
 * @author JOCK,wangxiaolei
 * 
 */
public class ThreadPoolTask implements Runnable {

	// private static final Logger logger = LoggerFactory.getLogger("syslog");

	private DatagramPacket packet;

	/**
	 * @param packet
	 */
	ThreadPoolTask(DatagramPacket packet) {
		this.packet = packet;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		InetAddress address = packet.getAddress();
		String content = new String(packet.getData());
		content = StringUtil.decodingString(content);
		String[] c = content.split(",");
		String logName = c[0];
		content = c[1];

		try {
			writeLog(logName, content, address.toString());
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}

	}

	/**
	 * @param logName
	 * @param content
	 * @throws Exception
	 */
	public void writeLog(String logName, String content, String sourceIP)
			throws Exception {
		Logger logger = LoggerFactory.getLogger(logName);
		logger.info("{}\t{}", sourceIP, content);
	}

	/**
	 * 解析收到的UDP数据报
	 * 
	 * @param content
	 *            收到的数据报
	 * @return
	 */
	public static List<String> parseRcvPackage(String content) {

		if (content == null)
			return null;
		int tempLen = Integer.parseInt(content.substring(0, 4), 16);
		content = content.substring(4);
		byte[] arr = content.getBytes();
		int totalLen = arr.length;
		if (tempLen != totalLen) {
			return null;
		}
		List<String> result = new ArrayList<String>();
		int pos = 0;
		// System.out.println(9889);
		while (pos < totalLen - 1) {
			byte[] b_len = new byte[4];
			System.arraycopy(arr, pos, b_len, 0, 4);
			int len = Integer.parseInt(new String(b_len), 16);
			pos += 4;
			byte[] b_element = new byte[len];
			System.arraycopy(arr, pos, b_element, 0, len);
			String element = new String(b_element);
			pos += len;
			result.add(element);
		}
		return result;
	}

	public Object getTask() {
		return this.packet;
	}
}
