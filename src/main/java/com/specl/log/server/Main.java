package com.specl.log.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.specl.log.util.Constants;

/**
 * @author wang xiaolei 2010-8-20
 * 
 */
public class Main {

	private static final Logger logger = LoggerFactory.getLogger("syslog");

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// 注册 一个回调线程(shutdown hook)
		Runtime.getRuntime().addShutdownHook(new HookThread("Hook Thread"));

		Constants.SYSPROP_MODE_VALUE = "server";

		LogServer server = new LogServer();
		server.setName("logserver thread");
		server.start();

		logger.info("log daemon started...");
	}

}
