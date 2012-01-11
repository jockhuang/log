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
package com.specl.log.server;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.specl.log.util.Constants;

/**
 * @author JOCK
 * 
 */

public class ThreadPool {

	private ThreadPool() {
	}

	/**
	 * 
	 */
	private static ThreadPoolExecutor instance = null;

	/**
	 * @return
	 */
	public static synchronized ThreadPoolExecutor getExecutorInstance() {

		String corePoolSizeStr = ServerConfigLoader
				.getProperty(Constants.PROP_NAME_COREPOOLSIZE);

		String maxPoolSizeStr = ServerConfigLoader
				.getProperty(Constants.PROP_NAME_MAXIMUNPOOLSIZE);

		String keepAliveTimeStr = ServerConfigLoader
				.getProperty(Constants.PROP_NAME_KEEPALIVETIME);

		// parameters
		int corePoolSize = Integer.valueOf(corePoolSizeStr);
		int maximumPoolSize = Integer.valueOf(maxPoolSizeStr);
		int keepAliveTime = Integer.valueOf(keepAliveTimeStr);

		if (instance == null) {
			instance = new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
					keepAliveTime, TimeUnit.SECONDS,
					new LinkedBlockingQueue<Runnable>(5),
					new ThreadPoolExecutor.CallerRunsPolicy());
		}
		return instance;
	}
}
