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

package lh.api.showcase.server.api.lh;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import lh.api.showcase.server.api.ApiAuth;
import lh.api.showcase.server.api.HttpClientFactory;
import lh.api.showcase.server.config.HasConfiguration;
import lh.api.showcase.server.config.auth.HasAuthenticationSettings;
import lh.api.showcase.server.config.proxy.HasProxySettings;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicStatusLine;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.assertTrue;

public class ApiAuthLhImplTest {

	private static Logger logger = Logger.getLogger(ApiAuthLhImplTest.class.getName());
	
	
	private CloseableHttpClient httpClientMock = Mockito.mock(CloseableHttpClient.class);
	private CloseableHttpResponse responseMock = Mockito.mock(CloseableHttpResponse.class);
	private HttpEntity entityMock = Mockito.mock(HttpEntity.class);
	
	private HasConfiguration configMock = Mockito.mock(HasConfiguration.class);
	private HasAuthenticationSettings authenticationSettingsMock = Mockito.mock(HasAuthenticationSettings.class);

	private class HttpClientFactoryTestImpl implements HttpClientFactory {

		@Override
		public CloseableHttpClient getHttpClient(
				HasProxySettings proxySetting) {
			return httpClientMock;
		}
	}
	
	@Before
    public void init() throws ClientProtocolException, IOException {
		Mockito.when(responseMock.getEntity()).thenReturn(entityMock);
		
		Mockito.when(configMock.getHttpProxySettings()).thenReturn(null);
		Mockito.when(configMock.getAuthenticationSettings()).thenReturn(authenticationSettingsMock);
		
		Mockito.when(authenticationSettingsMock.getClientId()).thenReturn("clientid");
		Mockito.when(authenticationSettingsMock.getClientSecret()).thenReturn("clientsecret");
    }
	
	@Test
	public void shouldNotRefreshTokenMultitimes() throws UnsupportedOperationException, IOException, InterruptedException {

		Mockito.when(responseMock.getStatusLine()).thenReturn(new BasicStatusLine(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK"));
		Mockito.when(entityMock.getContent()).thenAnswer(new Answer<InputStream>() {
			@Override
			public InputStream answer(InvocationOnMock invocation) throws Throwable {
				return this.getClass().getResource("/token_response.txt").openStream();
			}});
		Mockito.when(httpClientMock.execute((HttpGet) Mockito.any())).thenReturn(responseMock).thenAnswer(new Answer<CloseableHttpResponse>() {
			@Override
			public CloseableHttpResponse answer(InvocationOnMock invocation) throws Throwable {
				Thread.sleep(1500);
				return responseMock;
			}});
		
		// might call execute one time to get the first token
		final ApiAuth apiAuth = new ApiAuthLhImpl (configMock, new HttpClientFactoryTestImpl()) ;
		
		// should not call execute more than one time
		List<Thread> threadList = new ArrayList<Thread> () ;
		for (int i = 0 ; i < 5 ; ++i) {
			Thread t = new Thread(new Runnable() {
		        public void run() {
		        	try {
						apiAuth.updateAccessToken() ;
					} catch (JSONException | IOException e) {
						logger.severe(e.getMessage());
						assertTrue (false) ;
					}
		        }
		    });
			threadList.add(t) ;
		    t.start(); 
		}
		for (Thread t : threadList) {
			t.join();
		}
		
		Mockito.verify(httpClientMock, Mockito.atMost(2)).execute((HttpGet) Mockito.any()) ;
	}
}
