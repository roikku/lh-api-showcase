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

package lh.api.showcase.client.referencedata.airlines;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import lh.api.showcase.client.MultiLingualName;
import lh.api.showcase.shared.LanguageCode;

public class Airline implements MultiLingualName.HasMultiLingualName {

	private final String Id ;
	private final String IdIcao ;
	private final Set<String> otherIds = new HashSet<String> () ;
	private final MultiLingualName multiLingualName ;
	
	public Airline(String id, String idIcao, Set<String> otherIds, Map<LanguageCode, String> namesMap) {
		super();
		Id = id;
		IdIcao = idIcao;
		if (otherIds != null) {
			this.otherIds.addAll(otherIds) ;
		}
		this.multiLingualName = new MultiLingualName (namesMap) ;
	}
	
	public String getName () {
		return multiLingualName.getName() ;
	}

	public String getId() {
		return Id;
	}

	public String getIdIcao() {
		return IdIcao;
	}

	public Set<String> getOtherIds() {
		return otherIds;
	}

	@Override
	public MultiLingualName getMultiLingualName() {
		return multiLingualName;
	}
}
