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

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.logging.Logger;

import lh.api.showcase.server.api.ApiAuth;
import lh.api.showcase.server.api.HttpClientFactory;
import lh.api.showcase.server.config.proxy.HasProxySettings;
import lh.api.showcase.shared.api.lh.HttpErrorResponseException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicStatusLine;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class HttpQueryUtilsTest {

	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(HttpQueryUtilsTest.class.getName());
	
	private CloseableHttpClient httpClientMock = Mockito.mock(CloseableHttpClient.class);
	private CloseableHttpResponse responseMock = Mockito.mock(CloseableHttpResponse.class);
	private HttpEntity entityMock = Mockito.mock(HttpEntity.class);

	private ApiAuth apiAuthMock = Mockito.mock(ApiAuth.class);
	
	private URI uri = URI.create("http://localhost") ;
	
	private class HttpClientFactoryTestImpl implements HttpClientFactory {

		@Override
		public CloseableHttpClient getHttpClient(
				HasProxySettings proxySetting) {
			return httpClientMock;
		}
	}
	
    
	@Before
    public void init() throws ClientProtocolException, IOException {
		Mockito.when(httpClientMock.execute((HttpGet) Mockito.any())).thenReturn(responseMock);
		Mockito.when(responseMock.getEntity()).thenReturn(entityMock);
    }

    
	@Test(expected = HttpErrorResponseException.class)
	public void shouldThrowWhenFailToRefreshToken() throws ClientProtocolException, IOException, HttpErrorResponseException {

		Mockito.when(responseMock.getStatusLine()).thenReturn(new BasicStatusLine(HttpVersion.HTTP_1_1, HttpStatus.SC_UNAUTHORIZED, "Unauthorized"));
		Mockito.when(entityMock.getContent()).thenReturn(this.getClass().getResource("/empty_response.txt").openStream());
		Mockito.when(apiAuthMock.updateAccessToken()).thenReturn(false);

		try {
			HttpQueryUtils.executeQuery(uri, apiAuthMock, null, new HttpClientFactoryTestImpl (), 1) ;
		} finally {
			Mockito.verify(apiAuthMock, Mockito.times(1)).updateAccessToken() ;
		}
	}
	
	
	@Test(expected = HttpErrorResponseException.class)
	public void shouldRetryToRefreshToken() throws ClientProtocolException, IOException, HttpErrorResponseException {

		Mockito.when(responseMock.getStatusLine()).thenReturn(new BasicStatusLine(HttpVersion.HTTP_1_1, HttpStatus.SC_UNAUTHORIZED, "Unauthorized"));
		Mockito.when(entityMock.getContent()).thenAnswer(new Answer<InputStream>() {
			@Override
			public InputStream answer(InvocationOnMock invocation) throws Throwable {
				return this.getClass().getResource("/empty_response.txt").openStream();
			}});
		Mockito.when(apiAuthMock.updateAccessToken()).thenReturn(true);

		int maxRetried = 3 ;
		try {
			HttpQueryUtils.executeQuery(uri, apiAuthMock, null, new HttpClientFactoryTestImpl (), maxRetried) ;
			
		} finally {
			Mockito.verify(apiAuthMock, Mockito.times(maxRetried)).updateAccessToken() ;
			Mockito.verify(httpClientMock, Mockito.times(maxRetried+1)).execute((HttpGet) Mockito.any()) ;
		}
	}
}
