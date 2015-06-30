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

package lh.api.showcase.shared.api.lh;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import lh.api.showcase.shared.Pair;

public class HttpErrorResponseException extends Exception implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String OK = "OK" ;
	
	public static final String BAD_REQUEST = "Bad Request" ;
	public static final String UNAUTHORIZED = "Unauthorized" ;
	public static final String FORBIDDEN = "Forbidden" ;
	public static final String NOT_AUTHORIZED = "Not Authorized" ;
	public static final String ACCOUNT_INACTIVE = "Account Inactive" ;
	public static final String ACCOUNT_OVER_QUERIES_PER_SECOND_LIMIT = "Account Over Queries Per Second Limit" ;
	public static final String ACCOUNT_OVER_RATE_LIMIT = "Account Over Rate Limit" ;
	public static final String RATE_LIMIT_EXCEEDED = "Rate Limit Exceeded" ;
	public static final String NOT_FOUND = "Not Found" ;
	public static final String NOT_ALLOWED = "Not Allowed" ;
	
	public static final String INTERNAL_SERVER_ERROR = "Internal Server Error" ;
		
	private static Map<Pair<Integer, String>, String> errorsMap = new HashMap<Pair<Integer, String>, String> () ;
	static {
		errorsMap.put(Pair.newPair(Integer.valueOf(200), OK), "The API was able to do what you asked.") ;
		
		errorsMap.put(Pair.newPair(Integer.valueOf(400), BAD_REQUEST), "The API could not understand your request.") ;
		errorsMap.put(Pair.newPair(Integer.valueOf(401), UNAUTHORIZED), "The access token was missing or invalid.") ;
		errorsMap.put(Pair.newPair(Integer.valueOf(403), FORBIDDEN), "You have not been granted permission to access the requested method or object.") ;
		errorsMap.put(Pair.newPair(Integer.valueOf(403), NOT_AUTHORIZED), "The API key associated with your request was not recognized or the signature was incorrect.") ;
		errorsMap.put(Pair.newPair(Integer.valueOf(403), ACCOUNT_INACTIVE), "The API key you are using has not been approved or has been disabled.") ;
		errorsMap.put(Pair.newPair(Integer.valueOf(403), ACCOUNT_OVER_QUERIES_PER_SECOND_LIMIT), "You have made too many calls per second with this API key.") ;
		errorsMap.put(Pair.newPair(Integer.valueOf(403), ACCOUNT_OVER_RATE_LIMIT), "You have exceeded the rate limit for this API key.") ;
		errorsMap.put(Pair.newPair(Integer.valueOf(403), RATE_LIMIT_EXCEEDED), "The service you have requested is over-capacity.") ;
		errorsMap.put(Pair.newPair(Integer.valueOf(404), NOT_FOUND), "The API does not have the resource that you asked for.") ;
		errorsMap.put(Pair.newPair(Integer.valueOf(405), NOT_ALLOWED), "You have used a method (e.g. PUT) that isn't supported for this resource.") ;
	
		errorsMap.put(Pair.newPair(Integer.valueOf(500), INTERNAL_SERVER_ERROR), "You have used a method (e.g. PUT) that isn't supported for this resource.") ;
	}
	
	private int statusCode ;
	private String errorMessage ;
	private String responseBody ;
	
	// for serializer
	@SuppressWarnings("unused")
	private HttpErrorResponseException() {
		this(0, "", "");
	}
	
	public HttpErrorResponseException(int statusCode, String errorMessage) {
		this(statusCode, errorMessage, "");
	}
	
	public HttpErrorResponseException(int statusCode, String errorMessage, String responseBody) {
		super(errorMessage);
		this.statusCode = statusCode ;
		this.errorMessage = errorMessage ;
		this.responseBody = responseBody ;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
	
	public String getResponseBody() {
		return responseBody;
	}

	@Override
	public String getMessage() {
		if (errorsMap.containsKey(Pair.newPair(Integer.valueOf(statusCode), errorMessage))) {
			return errorsMap.get(Pair.newPair(Integer.valueOf(statusCode), errorMessage)) ;
		} else {
			return super.getMessage();
		}
	}
}
