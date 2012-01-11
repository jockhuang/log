/**
 * 
 */
package com.specl.log.client;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 参考了: com.opensymphony.xwork2.interceptor.TimerInterceptor
 * 
 * @author wang xiaolei 2010-8-23
 * 
 */
public class RequestInfoInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final String DEFAULT_LOGNAME = "syslog";

	/**
	 * 
	 */
	private static Logger logger;

	// ini logger
	static {
		String logName = ClientConfigLoader.getPropery().getProperty(
				"intercepterLogName", DEFAULT_LOGNAME);
		logger = LogFactory.getLogger(logName);
	}

	
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory
			.getLogger(RequestInfoInterceptor.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.interceptor.Interceptor#destroy()
	 */
	@Override
	public void destroy() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.interceptor.Interceptor#init()
	 */
	@Override
	public void init() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.opensymphony.xwork2.interceptor.Interceptor#intercept(com.opensymphony
	 * .xwork2.ActionInvocation)
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		//
		long startTime = System.currentTimeMillis();
		String result = invocation.invoke();
		long executionTime = System.currentTimeMillis() - startTime;

		StringBuilder message = new StringBuilder();
		message.append("Executed action \t");
		String namespace = invocation.getProxy().getNamespace();
		if ((namespace != null) && (namespace.trim().length() > 0)) {
			message.append(namespace).append("/");
		}
		message.append(invocation.getProxy().getActionName());
		message.append("!");
		message.append(invocation.getProxy().getMethod());
		message.append("\t").append(executionTime).append(" ms.");

		logger.log(message.toString());

		if (log.isDebugEnabled())
			log.debug(" RequestInfoInterceptor " + message.toString());

		return result;
	}

}
