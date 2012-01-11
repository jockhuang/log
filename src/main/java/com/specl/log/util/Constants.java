package com.specl.log.util;

/**
 * @author wang xiaolei 2010-8-19
 * 
 */
public class Constants {

	/**
	 * 
	 */
	private Constants() {
	}

	public static final String PROP_NAME_LISTENPORT = "port";

	public static final String PROP_NAME_COREPOOLSIZE = "corePoolSize";

	public static final String PROP_NAME_MAXIMUNPOOLSIZE = "maximumPoolSize";

	public static final String PROP_NAME_KEEPALIVETIME = "keepAliveTime";

	/**
	 * 启动参数，如果为 client 模式  LogFacotory 会进行必要的初始化动作，默认为client 模式.
	 * 在server 模式启动后会修改这个参数 为 server
	 */
	public static  String SYSPROP_MODE_VALUE = "client";
}
