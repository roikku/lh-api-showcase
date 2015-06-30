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

package lh.api.showcase.server.config.auth;

import java.util.logging.Logger;

import org.apache.commons.configuration.XMLConfiguration;

public class AuthenticationSettings implements HasAuthenticationSettings {

	private static Logger logger = Logger.getLogger(AuthenticationSettings.class.getName());
	
	private String apiKey = null;
	private String apiSecret = null;
	private String callbackUrl = null;
	
	public AuthenticationSettings (XMLConfiguration config) {
		super () ;
		
		this.apiKey = ""; 
		this.apiSecret = ""; 
		this.callbackUrl = ""; 
		
		if (config != null && !config.isEmpty()) {
			logger.info("Load authentication settings");
			this.apiKey = config.getString("authentication.api.key") ;
			this.apiSecret = config.getString("authentication.api.secret") ;
			this.callbackUrl = config.getString("authentication.api.callback") ;
		}
	}
	
	@Override
	public String getClientId() {
		return apiKey;
	}

	@Override
	public String getClientSecret() {
		return apiSecret;
	}

	@Override
	public String getCallBackUrl() {
		return callbackUrl;
	}
}
