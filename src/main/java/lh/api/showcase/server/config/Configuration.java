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

package lh.api.showcase.server.config;

import java.util.logging.Logger;

import org.apache.commons.configuration.XMLConfiguration;

import lh.api.showcase.server.config.auth.AuthenticationSettings;
import lh.api.showcase.server.config.auth.HasAuthenticationSettings;
import lh.api.showcase.server.config.proxy.HasProxySettings;
import lh.api.showcase.server.config.proxy.Proxy;

public class Configuration implements HasConfiguration {

	private static Logger logger = Logger.getLogger(Configuration.class.getName());
	
	private final XMLConfiguration config = ConfigurationUtils.getConfig() ;
	private final HasAuthenticationSettings auth = new AuthenticationSettings (config) ;
	
	private Boolean isHttpProxyActive = null ;
	private Boolean isHttpsProxyActive = null ;
	private Proxy httpProxy = null ;
	private Proxy httpsProxy = null ;
	
	public Configuration () {
		super () ;
		config.setThrowExceptionOnMissing(false);
	}
	
	
	@Override
	public String getAppName() {
		return "";
	}

	@Override
	public String getAppVersion() {
		return "";
	}

	
	private Proxy loadProxySetting (String protocol, String baseProperty) {
		
		if (config == null) {
			return null ;
		}
		logger.info("Load " + protocol + " proxy settings");
		
		int port = this.config.getInt(baseProperty + ".port", 8080) ;
		boolean isActive = config.getBoolean(baseProperty + ".active", false) ; 
		String user = config.getString(baseProperty + ".user") ;
		String password = config.getString(baseProperty + ".password") ;
		String host = config.getString(baseProperty + ".host") ;
		
		return new Proxy.Builder(protocol).setActivated(isActive)
				.setHost(host).setPort(port)
				.setUsername(user).setPassword(password).build();
	}
	
	
	@Override
	public synchronized HasProxySettings getHttpProxySettings() {

		if (isHttpProxyActive == null) {

			httpProxy = loadProxySetting ("http", "proxy.http") ;
			isHttpProxyActive = (httpProxy==null)?(Boolean.FALSE):(Boolean.valueOf(httpProxy.isActive())) ;			
		}
		if (isHttpProxyActive) {
			return httpProxy ;
		} else {
			return null ;
		}
	}

	
	@Override
	public synchronized HasProxySettings getHttpsProxySettings() {

		if (isHttpsProxyActive == null) {

			httpsProxy = loadProxySetting ("https", "proxy.https") ;
			isHttpsProxyActive = (httpsProxy==null)?(Boolean.FALSE):(Boolean.valueOf(httpsProxy.isActive())) ;			
		}
		if (isHttpsProxyActive) {
			return httpsProxy ;
		} else {
			return null ;
		}
	}
	

	@Override
	public HasAuthenticationSettings getAuthenticationSettings() {
		return auth;
	}
}
