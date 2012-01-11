package com.specl.log.util;

import java.io.InputStream;

/**
 * @author wang xiaolei 2010-8-20
 * 
 */
public class ClasspathResources {

	private String url;

	public ClasspathResources(String url) {
		this.url = url;
	}

	/**
	 * @return
	 */
	public InputStream getInputStream() {
		return this.getClass().getClassLoader().getResourceAsStream(url);
	}
}
