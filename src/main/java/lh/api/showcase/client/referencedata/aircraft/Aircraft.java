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

package lh.api.showcase.client.referencedata.aircraft;

import java.util.Map;

import lh.api.showcase.client.MultiLingualName;
import lh.api.showcase.shared.LanguageCode;

public class Aircraft implements MultiLingualName.HasMultiLingualName {

	private final String aircraftCode ;
	private final String airlineEquipCode ;
	private final MultiLingualName multiLingualName ;
	
	public Aircraft(String aircraftCode, String airlineEquipCode, Map<LanguageCode, String> namesMap) {
		super();
		this.aircraftCode = aircraftCode;
		this.airlineEquipCode = airlineEquipCode;
		this.multiLingualName = new MultiLingualName (namesMap) ;
	}
	
	public String getName () {
		return multiLingualName.getName() ;
	}

	public String getAircraftCode() {
		return aircraftCode;
	}

	public String getAirlineEquipCode() {
		return airlineEquipCode;
	}

	@Override
	public MultiLingualName getMultiLingualName() {
		return multiLingualName;
	}
}
