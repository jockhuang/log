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
package com.specl.log.client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;

import com.specl.log.util.StringUtil;

/**
 * @author JOCK,wangxiaolei
 * 
 */
public class Logger {

	protected static org.slf4j.Logger _log = org.slf4j.LoggerFactory
			.getLogger("STDOUTLOG");

	private String name;// 日志名称,服务的日志将根据这个日志名称进行归档操作.
	private String remoteIp = null; // 接受LOG服务器IP
	private int remotePort = 0; // 接受LOG服务器UDP端口
	private int localPort = 0; // 发送LOG服务器本机UDP端口

	private DatagramSocket sourceSocket = null;
	private SocketAddress targetSocket = null;

	/**
	 * @param name
	 * @param remoteIp
	 * @param remotePort
	 * @param localPort
	 * @throws SocketException
	 * @throws SocketException
	 */
	public Logger(String name, String remoteIp, int remotePort, int localPort)
			throws SocketException {
		this.name = name;
		this.remoteIp = remoteIp;
		this.remotePort = remotePort;
		this.localPort = localPort;

		try {
			sourceSocket = new DatagramSocket(localPort);
		} catch (Exception e) {
			sourceSocket = new DatagramSocket();
			_log.error(localPort + ",DatagramSocket init error,use new port="
					+ sourceSocket.getPort());
			e.printStackTrace();
		}

		try {
			targetSocket = new InetSocketAddress(remoteIp, remotePort);
		} catch (Exception e) {
			_log.error(remoteIp + ":" + remotePort
					+ ",InetSocketAddress init error");
			e.printStackTrace();
		}
	}

	/**
	 * 发送日志详细方法
	 * 
	 * @param content
	 *            发送日志内容
	 */
	public void log(String content) {
		if (content == null)
			return;

		// 发送log
		try {
			content = StringUtil.encodeString(name + "," + content);
			byte[] buf = content.getBytes();
			DatagramPacket pac = new DatagramPacket(buf, buf.length,
					targetSocket);
			sourceSocket.send(pac);
		} catch (Exception e) {
			if (sourceSocket != null) {
				synchronized (sourceSocket) {
					if (sourceSocket.isConnected()) {
						sourceSocket.close();
						sourceSocket.disconnect();
					}
					try {
						sourceSocket = new DatagramSocket();
					} catch (SocketException e1) {
						e1.printStackTrace();
					}
				}
			}

			// 发送日志出现异常,存在本地log
			// _log.error("send log error:" + remoteIp + ":" + remotePort +
			// "  localPort="+localPort + ",error:"+e.getMessage());
			_log.error("localPort:" + localPort);
		}
	}

	/**
	 * 垃圾回收关闭socket
	 */
	public void finalize() {
		if (sourceSocket != null) {
			sourceSocket.close();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemoteIp() {
		return remoteIp;
	}

	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}

	public int getRemotePort() {
		return remotePort;
	}

	public void setRemotePort(int remotePort) {
		this.remotePort = remotePort;
	}

	public int getLocalPort() {
		return localPort;
	}

	public void setLocalPort(int localPort) {
		this.localPort = localPort;
	}

	public DatagramSocket getSourceSocket() {
		return sourceSocket;
	}

	public void setSourceSocket(DatagramSocket sourceSocket) {
		this.sourceSocket = sourceSocket;
	}

	public SocketAddress getTargetSocket() {
		return targetSocket;
	}

	public void setTargetSocket(SocketAddress targetSocket) {
		this.targetSocket = targetSocket;
	}

}
