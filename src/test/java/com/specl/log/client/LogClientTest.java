package com.specl.log.client;

import org.junit.Test;

/**
 * @author wang xiaolei 2010-8-20
 * 
 */
public class LogClientTest {

	@Test
	public void Log() {
		Logger log = LogFactory.getLogger("filelog");
		log
				.log("file log xiaoleigood coming ___________________________________------------------------------------- ");
		// Logger log2 = LogFactory.getLogger("syslog");
		// log2.log("syslog xiaoleigood coming ");
	}
}
