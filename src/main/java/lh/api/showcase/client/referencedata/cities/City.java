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

package lh.api.showcase.client.referencedata.cities;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import lh.api.showcase.client.MultiLingualName;
import lh.api.showcase.shared.LanguageCode;

public class City implements MultiLingualName.HasMultiLingualName {

	private final String cityCode ;
	private final String countryCode ;
	private final Double latitude ;
	private final Double longitude ;
	private final Set<String> airportCodes = new HashSet<String> () ;
	private final MultiLingualName multiLingualName ;
	
	public City(String cityCode, String countryCode, Double latitude,
			Double longitude, Set<String> airportCodes, Map<LanguageCode, String> namesMap) {
		super();
		this.cityCode = cityCode;
		this.countryCode = countryCode;
		this.latitude = latitude;
		this.longitude = longitude;
		if (airportCodes != null) {
			this.airportCodes.addAll(airportCodes) ;
		}
		this.multiLingualName = new MultiLingualName (namesMap) ;
	}
	
	public String getName () {
		return multiLingualName.getName() ;
	}

	public String getCityCode() {
		return cityCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public Double getLatitude() {
		return latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public Set<String> getAirportCodes() {
		return airportCodes;
	}

	@Override
	public MultiLingualName getMultiLingualName() {
		return multiLingualName;
	}
}
