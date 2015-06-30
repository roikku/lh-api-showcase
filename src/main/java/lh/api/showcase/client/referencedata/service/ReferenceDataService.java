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

package lh.api.showcase.client.referencedata.service;

import lh.api.showcase.shared.AircraftCode;
import lh.api.showcase.shared.AirlineCode;
import lh.api.showcase.shared.AirportCode;
import lh.api.showcase.shared.CityCode;
import lh.api.showcase.shared.CountryCode;
import lh.api.showcase.shared.LanguageCode;
import lh.api.showcase.shared.api.lh.HttpErrorResponseException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("referenceData")
public interface ReferenceDataService extends RemoteService {
	public String getCountries (CountryCode countryCode, LanguageCode lang) throws HttpErrorResponseException ;
	public String getCities (CityCode cityCode, LanguageCode lang) throws HttpErrorResponseException ;
	public String getAirports (AirportCode airportCode, LanguageCode lang) throws HttpErrorResponseException ;
	public String getNearestAirports (Double latitude, Double longitude, LanguageCode lang) throws HttpErrorResponseException ;
	public String getAirlines (AirlineCode airlineCode) throws HttpErrorResponseException ;
	public String getAircraft (AircraftCode aircraftCode) throws HttpErrorResponseException ;
}
