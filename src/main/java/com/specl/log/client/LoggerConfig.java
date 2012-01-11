package com.specl.log.client;

/**
 * @author wang xiaolei 2010-8-19
 * 
 */
public class LoggerConfig {

	public LoggerConfig() {
		super();
	}

	public LoggerConfig(String name, String serverIP, int serverPort,
			int localPort) {
		super();
		this.name = name;
		this.serverIP = serverIP;
		this.serverPort = serverPort;
		this.localPort = localPort;
	}

	/**
	 *可以设置日志发送类 的名称.
	 */
	private String name;

	/**
	 * 
	 */
	private String serverIP;

	/**
	 * 
	 */
	private int serverPort;

	/**
	 * 
	 */
	private int localPort;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getServerIP() {
		return serverIP;
	}

	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}

	public int getServerPort() {
		return serverPort;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}

	public int getLocalPort() {
		return localPort;
	}

	public void setLocalPort(int localPort) {
		this.localPort = localPort;
	}

}
