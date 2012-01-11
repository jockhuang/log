package com.specl.log.client;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.LoggerFactory;

import com.specl.log.util.ClasspathResources;

/**
 * @author wang xiaolei 2010-8-19
 * 
 */
public class ClientConfigLoader {

	private static final org.slf4j.Logger logger = LoggerFactory
			.getLogger("ClientConfigLoader");

	private static final String SERVER_CONFIG = "logserver-client.properties";

	private Properties p;

	/**
	 * 
	 */
	private ClientConfigLoader() {
		ClasspathResources r = new ClasspathResources(SERVER_CONFIG);
		InputStream inputStream = r.getInputStream();
		p = new Properties();
		try {
			if (null != inputStream)
				p.load(inputStream);
			else
				logger.warn(" logServer client configure file [ logserver-client.properties ] can not find ! ");
				
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private Properties getP() {
		return p;
	}

	/**
	 * @return
	 */
	public static Properties getPropery() {
		return SingletonHolder.instance.getP();
	}

	static private class SingletonHolder {
		static ClientConfigLoader instance = new ClientConfigLoader();
	}

}
