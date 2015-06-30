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

package lh.api.showcase.client.referencedata.countries;

import java.util.Map;

import lh.api.showcase.client.MultiLingualName;
import lh.api.showcase.shared.LanguageCode;

public class Country implements MultiLingualName.HasMultiLingualName {

	private final String code ;
	private final String zone ;
	private final MultiLingualName multiLingualName ;
	
	public Country(String code, String zone, Map<LanguageCode, String> namesMap) {
		super();
		this.code = code;
		this.zone = zone;
		this.multiLingualName = new MultiLingualName (namesMap) ;
	}

	public String getName () {
		return multiLingualName.getName() ;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getZone() {
		return zone;
	}
	
	@Override
	public MultiLingualName getMultiLingualName() {
		return multiLingualName;
	}
}
