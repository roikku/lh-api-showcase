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

import java.io.IOException;
import java.util.logging.Logger;

import org.json.JSONException;

import lh.api.showcase.server.api.ApiAuth;
import lh.api.showcase.server.api.lh.ApiAuthLhImpl;

public enum DefaultApiAuthentication {
	INSTANCE;
	
	private Logger logger = Logger.getLogger(DefaultApiAuthentication.class.getName());

	private final ApiAuth auth ;
	
	DefaultApiAuthentication () {
		logger.info("Create default API authentication");
		ApiAuth authTmp = null ;
		try {
			authTmp = new ApiAuthLhImpl (DefaultConfiguration.INSTANCE.get()) ;
		} catch (IOException | JSONException e) {
			logger.severe(e.getMessage());
		} finally {
			auth = authTmp ;
		}
	}
	
	public ApiAuth get() {
		return auth;
	}
}
