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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.NameValuePair;

public interface ApiRequestFactory<T extends Enum<T>> {

	public String getScheme();

	public String getHost();

	public String getVersion();

	public URI getRequestUri(T area,
			NameValuePair resourceNameKey,
			List<NameValuePair> subResourceNameKey,
			List<NameValuePair> optionKeyValue) throws URISyntaxException;
}
