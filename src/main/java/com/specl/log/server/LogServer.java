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
import java.net.DatagramSocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.specl.log.util.Constants;

/**
 * @author JOCK
 *
 */

/**
 * 在一个集群式的服务中，要查看每台服务器的日志是一个麻烦事。 为了便于集中的查看日志，分析日志，写了一个UDP方式的日志服务器。
 * 可以根据业务ID，来区分日志，每个业务ID每天一个日志文件。 这是服务器端代码。
 * 
 */
public class LogServer extends Thread {

	private static final Logger logger = LoggerFactory.getLogger("syslog");

	private String port = ServerConfigLoader
			.getProperty(Constants.PROP_NAME_LISTENPORT);

	/**
	 * 
	 * 运行server
	 */
	public void run() {
		try {
			DatagramSocket dSocket = new DatagramSocket(Integer.parseInt(port));
			logger.info("listening " + port);

			while (true) {
				byte[] buf = new byte[3072];
				DatagramPacket packet = new DatagramPacket(buf, buf.length);
				dSocket.receive(packet);
				ThreadPool.getExecutorInstance().execute(
						new ThreadPoolTask(packet));
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}

	}
}
