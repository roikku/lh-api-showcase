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
import java.net.URI;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

import lh.api.showcase.server.DefaultApiAuthentication;
import lh.api.showcase.server.DefaultConfiguration;
import lh.api.showcase.server.api.ApiAuth;
import lh.api.showcase.server.api.HttpClientFactory;
import lh.api.showcase.server.api.HttpClientFactoryImpl;
import lh.api.showcase.server.config.proxy.HasProxySettings;
import lh.api.showcase.shared.api.lh.HttpErrorResponseException;

import org.apache.commons.io.IOUtils;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

public class HttpQueryUtils {

	private static Logger logger = Logger.getLogger(HttpQueryUtils.class.getName());
	
	private HttpQueryUtils () { super () ; throw new IllegalStateException () ; }
	
	
	public static String executeQuery (URI uri) throws HttpErrorResponseException {
		return executeQuery (uri, DefaultApiAuthentication.INSTANCE.get()) ;
	}
	
	
	public static String executeQuery(URI uri, ApiAuth apiAuth) throws HttpErrorResponseException {
		return executeQuery(uri, apiAuth, DefaultConfiguration.INSTANCE.get()
				.getHttpProxySettings(), new HttpClientFactoryImpl(), 3);
	}
	
		
	public static String executeQuery(URI uri, ApiAuth apiAuth,
			HasProxySettings proxySetting, HttpClientFactory httpClientFactory,
			final int maxRetries) throws HttpErrorResponseException {

		//logger.info("uri: " + uri.toString());

		AtomicInteger tryCounter = new AtomicInteger (0) ;
		while (true) {
		
			CloseableHttpClient httpclient = httpClientFactory.getHttpClient(proxySetting) ;
			HttpGet httpGet = new HttpGet(uri);
			httpGet.addHeader("Authorization", apiAuth.getAuthHeader());
			httpGet.addHeader("Accept", "application/json");
			
			//logger.info("auth: " + apiAuth.getAuthHeader()) ;
			//logger.info("query: " + httpGet.toString());
	
			CloseableHttpResponse response = null;
			try {
				response = httpclient.execute(httpGet);
				StatusLine status = response.getStatusLine();  
			    BufferedHttpEntity entity = new BufferedHttpEntity(response.getEntity());
			    String json = IOUtils.toString(entity.getContent(), "UTF8") ;
			    EntityUtils.consume(entity);
			    //logger.info("response: " + json);
			    
			    // check for errors
				if (status != null && status.getStatusCode() > 299){
					if (status.getStatusCode() == 401) {
						// token has probably expired
						logger.info("Authentication Error. Token will be refreshed") ;
						if (tryCounter.getAndIncrement() < maxRetries) {
							if (apiAuth.updateAccessToken()) {
								logger.info("Token successfully refreshed") ;
								// we retry with the new token
								logger.info("Retry number " + tryCounter.get()) ;
								continue ;
							}	
						}
					} 
					throw new HttpErrorResponseException (status.getStatusCode(), status.getReasonPhrase(), json) ;
				} 
			    return json ;
			} catch (IOException e) {
				logger.severe(e.getMessage());
				break ;
			} finally {
				try {
					if (response != null) {
						response.close();
					}
				} catch (IOException e) {
					logger.log(Level.SEVERE, e.getMessage());
				}
			}
		}
		return null;
	}
}
