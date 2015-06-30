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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import lh.api.showcase.server.api.ApiRequestFactory;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;

import com.google.common.base.Preconditions;

public abstract class ApiRequestFactoryLhAbstract implements ApiRequestFactory<ApiRequestFactoryLhAbstract.ApiDataArea> {

	public enum ApiDataArea {
		REFERENCES,
		OPERATIONS,
		OFFERS
	}
	
	@Override
	public String getScheme() {
		return "https";
	}

	@Override
	public String getHost() {
		return "api.lufthansa.com";
	}

	@Override
	public String getVersion() {
		return "v1";
	}
	
	public abstract URI getRequestUri(NameValuePair resourceNameKey,
			List<NameValuePair> subResourceNameKey,
			List<NameValuePair> optionKeyValue) throws URISyntaxException ;
	
	@Override
	public URI getRequestUri(ApiDataArea area, NameValuePair resourceNameKey,
			List<NameValuePair> subResourceNameKey,
			List<NameValuePair> optionKeyValue) throws URISyntaxException {

		Preconditions.checkNotNull(area) ;
		Preconditions.checkNotNull(resourceNameKey) ;
		Preconditions.checkArgument(StringUtils.isNotEmpty(resourceNameKey.getName()));
		
		URIBuilder urib = new URIBuilder () ;
		urib.setScheme(getScheme()) ;
		urib.setHost(getHost()) ;
		
		StringBuilder sb = new StringBuilder () ;
		// build path
		sb.append("/") ;
		sb.append(getVersion()) ;
		sb.append("/") ;
		// area
		sb.append(area.toString().toLowerCase()) ;
		sb.append("/") ;
		// resource
		sb.append(resourceNameKey.getName()) ;
		if (StringUtils.isNotEmpty(resourceNameKey.getValue())) {
			sb.append("/") ;
			sb.append(resourceNameKey.getValue()) ;
		}
		// sub resources
		if (subResourceNameKey != null) {
			for (NameValuePair vp : subResourceNameKey) {
				if (StringUtils.isEmpty(vp.getName())) {
					continue ;
				}
				sb.append("/") ;
				sb.append(vp.getName()) ;
				if (StringUtils.isNotEmpty(vp.getValue())) {
					sb.append("/") ;
					sb.append(vp.getValue()) ;
				}
			}
		}
		urib.setPath(sb.toString()) ;
		// parameters
		if (optionKeyValue != null && !optionKeyValue.isEmpty()) {
			urib.setParameters(optionKeyValue) ;
		}
		return urib.build();
	}
}
