package com.specl.log.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wang xiaolei 2010-8-19
 * 
 */
public class HookThread extends Thread {

	private static final Logger logger = LoggerFactory.getLogger("syslog");

	public HookThread(String name) {
		this.setName(name);
	}

	@Override
	public void run() {
		logger.info("Application hook coming soon...");
	}

}
