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

package lh.api.showcase.client;

import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

import lh.api.showcase.client.UiUtils.MessageType;
import lh.api.showcase.shared.api.lh.HttpErrorResponseException;

public class HttpErrorUtils {
	
	private HttpErrorUtils () { super () ; throw new IllegalStateException () ; }
	
	public static void handleHttpErrorException (Throwable err, BasicFormResultDisplay display) {
		if (err == null) {
			return ;
		}
		if (err instanceof HttpErrorResponseException) {
			HttpErrorResponseException httpError = (HttpErrorResponseException)err ;
			StringBuilder sb = new StringBuilder () ;
			if (httpError.getStatusCode() <= 299) {
				return ;
			} else if (httpError.getStatusCode() == 400
					||httpError.getStatusCode() == 404
					|| httpError.getStatusCode() == 405) {
				sb.append(Messages.Util.INSTANCE.get().badRequest()) ;
			} else if (httpError.getStatusCode() == 403) {
				sb.append(Messages.Util.INSTANCE.get().serverProblem()) ;
			} else {
				String body = httpError.getResponseBody() ;
				if (body == null || body.isEmpty()) {
					sb.append(err.getMessage()) ;
				} else {
					try {
						JSONValue jsv = JSONParser.parseStrict(body);
						JSONObject  jobj = jsv.isObject() ;
						
						JSONObject jsProcessingErrorsObj = jobj.get("ProcessingErrors").isObject() ;
						JSONObject jsProcessingErrorObj = jsProcessingErrorsObj.get("ProcessingError").isObject() ;
	
						@SuppressWarnings("unused")
						JSONBoolean jsIsRetryable = jsProcessingErrorObj.get("@RetryIndicator").isBoolean() ;
						@SuppressWarnings("unused")
						JSONString jsType = jsProcessingErrorObj.get("Type").isString() ;
						@SuppressWarnings("unused")
						JSONString jsDescription = jsProcessingErrorObj.get("Description").isString() ;
						@SuppressWarnings("unused")
						JSONString jsInfoUrl = jsProcessingErrorObj.get("InfoURL").isString() ;
						JSONNumber jsCode = jsProcessingErrorObj.get("Code").isNumber() ;
						
						int code = (int) jsCode.doubleValue() ;
						if (code >= 2000 && code < 3000) {
							sb.append(Messages.Util.INSTANCE.get().badRequest()) ;
						} if (code >= 3000 && code < 4000) {
							sb.append(Messages.Util.INSTANCE.get().resourceNotFound()) ;
						} else {
							sb.append(err.getMessage()) ;
						}
					} catch (IllegalArgumentException | NullPointerException e) {
						sb.append(err.getMessage()) ;
					}
				}
			}
			
			if (display != null) {
				display.setJson((httpError.getResponseBody()!= null)?(httpError.getResponseBody()):(""), null);
				display.setMessage(sb.toString(), MessageType.ERROR);
			}
		}
	}
}
