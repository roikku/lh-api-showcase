/*
 * Copyright 2015 Loic Merckel
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package lh.api.showcase.server;

import java.util.logging.Logger;

import lh.api.showcase.server.config.HasConfiguration;
import lh.api.showcase.server.util.HttpClientUtils;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

public enum DefaultHttpClient {
	INSTANCE;
	
	private Logger logger = Logger.getLogger(DefaultHttpClient.class.getName());
	
	private final CloseableHttpClient httpClient;
	private final HasConfiguration conf ;
	
	private final int maxTotalConnections = 50 ; 
	private final int maxTotalPerRouteConnections = 50 ; 
	
	DefaultHttpClient () {
		logger.info("Create default http client");
		
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(maxTotalConnections);
        connectionManager.setDefaultMaxPerRoute(maxTotalPerRouteConnections);
        
        conf = DefaultConfiguration.INSTANCE.get() ;
        httpClient = HttpClientUtils.createHttpClient(conf.getHttpProxySettings(), connectionManager) ;
        
        logger.info("Default http client has " + connectionManager.getMaxTotal() + " max total connections and " 
        			+ connectionManager.getDefaultMaxPerRoute() + " max connections per route");
	}
	
	public CloseableHttpClient get () {
		return httpClient ;
	}
}
