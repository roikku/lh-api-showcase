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

package lh.api.showcase.server.api;

import org.apache.http.impl.client.CloseableHttpClient;

import lh.api.showcase.server.config.proxy.HasProxySettings;
import lh.api.showcase.server.DefaultHttpClient;

public class HttpClientFactoryImpl implements HttpClientFactory {

	@Override
	public CloseableHttpClient getHttpClient(
			HasProxySettings proxySetting) {
		return DefaultHttpClient.INSTANCE.get ();
	}
}
