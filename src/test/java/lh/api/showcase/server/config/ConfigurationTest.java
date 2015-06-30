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

import static org.junit.Assert.*;
import lh.api.showcase.server.config.proxy.HasProxySettings;
import org.junit.Test;

public class ConfigurationTest {

	@Test
	public void shouldLoadAuthenticationSetting() {
		Configuration config = new Configuration () ;
		assertTrue("client_key".equals(config.getAuthenticationSettings().getClientId())) ;
		assertTrue("client_secret".equals(config.getAuthenticationSettings().getClientSecret())) ;
		assertTrue("http://example.com:8080".equals(config.getAuthenticationSettings().getCallBackUrl())) ;
	}
	
	
	@Test
	public void shouldLoadHttpProxySetting() {
		Configuration config = new Configuration () ;
		verifyProxy (config.getHttpProxySettings()) ;
	}
	
	
	@Test
	public void shouldLoadHttpsProxySetting() {
		Configuration config = new Configuration () ;
		verifyProxy (config.getHttpsProxySettings()) ;
	}
	
	
	private void verifyProxy (HasProxySettings proxy) {
		assertTrue(Integer.valueOf(8080).equals(proxy.getPort())) ;
		assertTrue("username".equals(proxy.getUsername())) ;
		assertTrue("password".equals(proxy.getPassword())) ;
		assertTrue("host".equals(proxy.getHost())) ;
		assertTrue(Boolean.TRUE.equals(proxy.isActive())) ;
		assertTrue(proxy.getCredentialsProvider() != null) ;
	}
}
