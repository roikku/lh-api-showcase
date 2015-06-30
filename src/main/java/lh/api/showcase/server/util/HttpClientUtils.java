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

package lh.api.showcase.server.util;

import lh.api.showcase.server.config.proxy.HasProxySettings;

import org.apache.http.HttpHost;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import com.google.common.base.Preconditions;

import java.util.logging.Logger;


public class HttpClientUtils {

	private static Logger logger = Logger.getLogger(HttpClientUtils.class.getName());
	
	private HttpClientUtils () { super () ; throw new IllegalStateException () ; }
	
    public static CloseableHttpClient createHttpClient (HasProxySettings proxySetting, 
    		PoolingHttpClientConnectionManager connectionManager) {
    	// http://hc.apache.org/httpcomponents-client-ga/tutorial/html/connmgmt.html#d5e475
    	
    	HttpClientBuilder clientBuilder = HttpClients.custom() ;
    	if (proxySetting != null && proxySetting.isActive()) {
    		logger.info("Set the http proxy (" + proxySetting.getHost() + ":" + proxySetting.getPort() + ")") ;
    		CredentialsProvider credsProvider = Preconditions.checkNotNull(proxySetting.getCredentialsProvider()) ;
        	HttpHost proxy = new HttpHost(proxySetting.getHost(), proxySetting.getPort());
        	DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
 
        	clientBuilder.setRoutePlanner(routePlanner).setDefaultCredentialsProvider(credsProvider) ;
    	} 
    	if (connectionManager != null) {
    		clientBuilder.setConnectionManager (connectionManager) ;
    	}
    	return clientBuilder.build () ;
    }
}
