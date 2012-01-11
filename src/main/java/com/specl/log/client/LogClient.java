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
import java.net.InetAddress;

/**
 * @author JOCK
 * 
 */
@Deprecated
public class LogClient extends Thread {

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		try {
			Thread.currentThread().sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		String[] options = { "test", "这是一个测试，,this is a test." };
		try {
			for (int i = 0; i < 5; i++) {
				udpLog("localhost", 55001, options);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * server IP address
	 */
	public static void main(String[] args) throws Exception {
		LogClient[] lc = new LogClient[10];
		for (LogClient l : lc) {
			l = new LogClient();
			l.start();
		}

	}

	public static void udpLog(String serverIP, int port, String[] options)
			throws Exception {

		DatagramSocket clientSocket = new DatagramSocket();
		InetAddress address = InetAddress.getByName(serverIP);

		StringBuilder data = new StringBuilder();
		for (int i = 0; i < options.length; i++) {
			options[i] = options[i] == null ? "" : options[i];
			data.append(String.format("%04x", options[i].getBytes().length)
					+ options[i]);
		}
		String s = String.valueOf(System.currentTimeMillis());
		data.append(String.format("%04x", s.getBytes().length) + s);
		byte[] sendData = (String.format("%04x",
				data.toString().getBytes().length) + data).getBytes();
		clientSocket.setSoTimeout(200);

		DatagramPacket sendPacket = new DatagramPacket(sendData,
				sendData.length, address, port);

		clientSocket.send(sendPacket);
		clientSocket.close();
	}
}
