package com.specl.log.server;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.specl.log.util.ClasspathResources;

/**
 * @author wang xiaolei 2010-8-19
 * 
 */
public class ServerConfigLoader {

	private static final String SERVER_CONFIG = "logserver.properties";

	private Properties p;

	/**
	 * 
	 */
	private ServerConfigLoader() {
		ClasspathResources r = new ClasspathResources(SERVER_CONFIG);
		InputStream inputStream = r.getInputStream();
		p = new Properties();
		try {
			p.load(inputStream);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private String getPropery(String propName) {
		return this.p.getProperty(propName);
	}

	static private class SingletonHolder {
		static ServerConfigLoader instance = new ServerConfigLoader();
	}

	public static String getProperty(String propName) {
		return SingletonHolder.instance.getPropery(propName);
	}

}
