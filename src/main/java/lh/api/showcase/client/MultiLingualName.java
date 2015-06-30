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

import java.util.HashMap;
import java.util.Map;

import lh.api.showcase.shared.LanguageCode;

public class MultiLingualName {

	public static interface HasMultiLingualName {
		public MultiLingualName getMultiLingualName () ;
	}
	
	private final Map<LanguageCode, String> namesMap = new HashMap<LanguageCode, String> () ;
	
	public MultiLingualName (Map<LanguageCode, String> namesMap) {
		super () ;
		if (namesMap != null) {
			this.namesMap.putAll(namesMap) ;
		}
	}

	public MultiLingualName(MultiLingualName multiLingualName) {
		this (multiLingualName.namesMap) ;
	}

	public Map<LanguageCode, String> getNamesMap () {
		return namesMap ;
	}

	public String getName () {
		StringBuilder sb = new StringBuilder () ;	
		for (Map.Entry<LanguageCode, String> ent : namesMap.entrySet()) {
			sb.append("(") ;
			sb.append(ent.getKey().toString().toLowerCase()) ;
			sb.append(") ") ;
			sb.append(ent.getValue()) ;
		}
		return sb.toString();
	}
}
