package com.specl.log.util;

import org.junit.Test;

import com.specl.log.server.ServerConfigLoader;

public class TestPropertyLoader {

	/**
	 * 
	 */
	@Test
	public void getPropery() {
		System.out.println(ServerConfigLoader.getProperty("port"));
	}
}
