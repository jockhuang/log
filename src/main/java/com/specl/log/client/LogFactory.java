/*
   Copyright [2010] [Jock Huang]

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 */
package com.specl.log.client;

import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import com.specl.log.util.Constants;

/**
 * @author JOCK, wangxiaolei
 * 
 * 
 */
public class LogFactory {

	/**
	 * 
	 */
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory
			.getLogger("LogServer client LogFactory");

	/**
	 * 
	 */
	private static Map<String, Logger> loggerMap = new ConcurrentHashMap<String, Logger>();

	//
	static {
		if ("client".equals(Constants.SYSPROP_MODE_VALUE)) {
			Properties props = ClientConfigLoader.getPropery();
			if (null != props) {
				Set<Entry<Object, Object>> set = props.entrySet();
				for (Map.Entry<Object, Object> en : set) {
					String propName = (String) en.getKey();
					if (propName.endsWith(".logger")) {
						String value = (String) en.getValue();
						String[] va = value.split(",");
						String name = va[0];
						String serverIP = va[1];
						String serverPort = va[2];
						String localPort = va[3];

						try {
							loggerMap.put(name, new Logger(name, serverIP,
									Integer.parseInt(serverPort), Integer
											.parseInt(localPort)));

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			} else {
				log.error(" LogServer init error");
			}
		}
	}

	/**
	 * 获取封装好的Logger
	 * 
	 * @param key
	 * @return
	 */
	public static Logger getLogger(String key) {
		return loggerMap.get(key);
	}

}
