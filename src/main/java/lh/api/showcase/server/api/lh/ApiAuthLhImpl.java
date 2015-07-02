/*
 *  Copyright 2015 Loic Merckel
 *  Copyright 2014 Dirk Boye 
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

/*
*
* The original version of this file (i.e., the one that is copyrighted 2014 Dirk Boye) 
* can  be found here:
*
*  https://github.com/dirkboye/GDriveUpload
*  
*  Massive changes have been made
*
*/


// https://code.google.com/p/gdata-java-client/
package lh.api.showcase.server.api.lh;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import lh.api.showcase.server.api.ApiAuth;
import lh.api.showcase.server.api.HttpClientFactory;
import lh.api.showcase.server.api.HttpClientFactoryImpl;
import lh.api.showcase.server.config.HasConfiguration;
import lh.api.showcase.server.config.proxy.HasProxySettings;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.concurrent.Semaphore;
import java.util.logging.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.common.base.Preconditions;


public class ApiAuthLhImpl implements ApiAuth {
	
	private static Logger logger = Logger.getLogger(ApiAuthLhImpl.class.getName());
	
    private final String clientId;
    private final String clientSecret;
    private String accessToken = "" ;
    @SuppressWarnings("unused")
	private String tokenType = "" ;
    private final int maxRetries = 3;
    
    private final HasProxySettings proxySetting ;
    private final HttpClientFactory httpClientFactory ;
    
    private final Semaphore semaphore = new Semaphore(1);
    private final Object lock = new Object ();
    private boolean tokensOK = false;

    
    public ApiAuthLhImpl(HasConfiguration config) throws IOException, JSONException {
    	this(config, new HttpClientFactoryImpl ()) ;
    }
        
    public ApiAuthLhImpl(HasConfiguration config, HttpClientFactory httpClientFactory) throws IOException, JSONException {
    	super () ;
    	Preconditions.checkNotNull(config) ;
    	Preconditions.checkNotNull(httpClientFactory) ;
    	
    	this.proxySetting = config.getHttpProxySettings() ;
    	this.httpClientFactory = httpClientFactory ;

        clientSecret = config.getAuthenticationSettings().getClientSecret();
        clientId = config.getAuthenticationSettings().getClientId();

        int retry = 0;
        boolean tokensOK = false;
        while (!tokensOK && retry < maxRetries) {
            tokensOK = updateAccessToken();
            ++retry;
        }
        if (!tokensOK) {
        	logger.info("Authentication aborted after " + maxRetries + " retries.");
            throw new IllegalStateException () ;
        }
    }
    
    @Override
    public String getAccessToken() {
    	synchronized(lock) {
    		return accessToken;
    	}
    }

    @Override
    public String getAuthHeader() {
    	synchronized(lock) {
    		return "Bearer" + " " + accessToken;
    	}
    }

    @Override
    public boolean updateAccessToken() throws UnsupportedEncodingException, JSONException, IOException {
 
    	if (!semaphore.tryAcquire()) {
    		semaphore.acquireUninterruptibly();
    		semaphore.release();
    		return tokensOK ;
    	}
    	synchronized(lock) {
	        logger.info("Updating/getting access token");
	        CloseableHttpClient httpclient = httpClientFactory.getHttpClient(proxySetting) ;
	        HttpPost httpPost = new HttpPost("https://api.lufthansa.com/v1/oauth/token");
	        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
	        nvps.add(new BasicNameValuePair("client_id", clientId));
	        nvps.add(new BasicNameValuePair("client_secret", clientSecret));
	        nvps.add(new BasicNameValuePair("grant_type", "client_credentials"));
	        CloseableHttpResponse response = null ;
	        tokensOK = false;
	        try {
		        BufferedHttpEntity postentity = new BufferedHttpEntity(new UrlEncodedFormEntity(nvps));
		        httpPost.setEntity(postentity);
		        response = httpclient.execute(httpPost);
		        BufferedHttpEntity entity = new BufferedHttpEntity(response.getEntity());
		        EntityUtils.consume(response.getEntity());
		        
	            if (response.getStatusLine().getStatusCode() == 200 && entity != null) {
	                String retSrc = EntityUtils.toString(entity);
	                JSONObject result = new JSONObject(retSrc);
	                accessToken = result.getString("access_token");
	                tokenType = result.getString("token_type");
	                tokensOK = true;
	            }
	        } finally {
	        	if (response != null) {
	        		response.close();
	        	}
	            semaphore.release();
	        }
	        if (!tokensOK) {
	            accessToken = "";
	            tokenType = "";
	        }
	        return tokensOK;
    	}
    }
}
