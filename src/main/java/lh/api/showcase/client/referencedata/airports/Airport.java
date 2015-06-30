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

package lh.api.showcase.client.referencedata.airports;

import java.util.Map;

import lh.api.showcase.client.MultiLingualName;
import lh.api.showcase.shared.LanguageCode;

public class Airport implements MultiLingualName.HasMultiLingualName {

	private final String airportCode ;
	private final String cityCode ;
	private final String countryCode ;
	private final String locationType ;
	private final Double latitude ;
	private final Double longitude ;
	private final MultiLingualName multiLingualName ;
	
	public Airport(Airport airport) {
		this(airport.airportCode, airport.cityCode, airport.countryCode,
				airport.locationType, airport.latitude, airport.longitude,
				airport.multiLingualName);
	}
	
	public Airport(String airportCode, String cityCode,
			String countryCode, String locationType, Double latitude,
			Double longitude, MultiLingualName multiLingualName ) {
		super();
		this.airportCode = airportCode;
		this.cityCode = cityCode;
		this.countryCode = countryCode;
		this.locationType = locationType;
		this.latitude = latitude;
		this.longitude = longitude;
		this.multiLingualName = new MultiLingualName(multiLingualName) ;
	}
	
	public Airport(String airportCode, String cityCode,
			String countryCode, String locationType, Double latitude,
			Double longitude, Map<LanguageCode, String> namesMap) {
		super();
		this.airportCode = airportCode;
		this.cityCode = cityCode;
		this.countryCode = countryCode;
		this.locationType = locationType;
		this.latitude = latitude;
		this.longitude = longitude;
		this.multiLingualName = new MultiLingualName (namesMap) ;
	}
	
	public String getName () {
		return multiLingualName.getName() ;
	}

	public String getAirportCode() {
		return airportCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public String getLocationType() {
		return locationType;
	}

	public Double getLatitude() {
		return latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	@Override
	public MultiLingualName getMultiLingualName() {
		return multiLingualName;
	}
}
