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

package lh.api.showcase.server.api.lh.referencedata;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import lh.api.showcase.client.referencedata.service.ReferenceDataService;
import lh.api.showcase.server.api.lh.ApiAuthLhImpl;
import lh.api.showcase.server.util.HttpQueryUtils;
import lh.api.showcase.shared.AircraftCode;
import lh.api.showcase.shared.AirlineCode;
import lh.api.showcase.shared.AirportCode;
import lh.api.showcase.shared.CityCode;
import lh.api.showcase.shared.CountryCode;
import lh.api.showcase.shared.LanguageCode;
import lh.api.showcase.shared.api.lh.HttpErrorResponseException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ReferenceDataServiceImpl extends RemoteServiceServlet implements ReferenceDataService {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(ApiAuthLhImpl.class.getName());

	
	public ReferenceDataServiceImpl () {
		super () ;
	}
	
	
	@Override
	public String getCountries(CountryCode countryCode, LanguageCode lang) throws HttpErrorResponseException {

		ReferenceDataRequestFactoryImpl reqFact = new ReferenceDataRequestFactoryImpl () ;
		try {
			URI uri = reqFact.getRequestUri((NameValuePair) new BasicNameValuePair("countries", (countryCode==null)?(""):(countryCode.toString())),
									null,
									Arrays.asList((NameValuePair) new BasicNameValuePair("lang", (lang==null)?(""):(lang.toString())))) ;
			
			return HttpQueryUtils.executeQuery (uri) ;
			
		} catch (URISyntaxException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
		return null;
	}
	

	@Override
	public String getCities(CityCode cityCode, LanguageCode lang) throws HttpErrorResponseException {

		ReferenceDataRequestFactoryImpl reqFact = new ReferenceDataRequestFactoryImpl () ;
		try {
			URI uri = reqFact.getRequestUri((NameValuePair) new BasicNameValuePair("cities", (cityCode==null)?(""):(cityCode.toString())),
									null,
									Arrays.asList((NameValuePair) new BasicNameValuePair("lang", (lang==null)?(""):(lang.toString())))) ;
			
			return HttpQueryUtils.executeQuery (uri) ;
			
		} catch (URISyntaxException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
		return null;
	}
	

	@Override
	public String getAirports(AirportCode airportCode, LanguageCode lang) throws HttpErrorResponseException {
		ReferenceDataRequestFactoryImpl reqFact = new ReferenceDataRequestFactoryImpl () ;
		try {
			URI uri = reqFact.getRequestUri((NameValuePair) new BasicNameValuePair("airports", (airportCode==null)?(""):(airportCode.toString())),
									null,
									Arrays.asList((NameValuePair) new BasicNameValuePair("lang", (lang==null)?(""):(lang.toString())))) ;
			
			return HttpQueryUtils.executeQuery (uri) ;
			
		} catch (URISyntaxException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
		return null;
	}

	@Override
	public String getNearestAirports(Double latitude, Double longitude,
			LanguageCode lang) throws HttpErrorResponseException {
		ReferenceDataRequestFactoryImpl reqFact = new ReferenceDataRequestFactoryImpl () ;
		try {
			URI uri = reqFact.getRequestUri((NameValuePair) new BasicNameValuePair("airports", ""),
									(List<NameValuePair>) Arrays.asList((NameValuePair) new BasicNameValuePair("nearest", latitude.toString() + "," + longitude.toString())),
									Arrays.asList((NameValuePair) new BasicNameValuePair("lang", (lang==null)?(""):(lang.toString())))) ;
			
			return HttpQueryUtils.executeQuery (uri) ;
			
		} catch (URISyntaxException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
		return null;
	}

	@Override
	public String getAirlines(AirlineCode airlineCode) throws HttpErrorResponseException {
		ReferenceDataRequestFactoryImpl reqFact = new ReferenceDataRequestFactoryImpl () ;
		try {
			URI uri = reqFact.getRequestUri((NameValuePair) new BasicNameValuePair("airlines", (airlineCode==null)?(""):(airlineCode.toString())),
									null,
									null) ;
			
			return HttpQueryUtils.executeQuery (uri) ;
			
		} catch (URISyntaxException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
		return null;
	}

	@Override
	public String getAircraft(AircraftCode aircraftCode) throws HttpErrorResponseException {
		ReferenceDataRequestFactoryImpl reqFact = new ReferenceDataRequestFactoryImpl () ;
		try {
			URI uri = reqFact.getRequestUri((NameValuePair) new BasicNameValuePair("aircraft", (aircraftCode==null)?(""):(aircraftCode.toString())),
									null,
									null) ;
			
			return HttpQueryUtils.executeQuery (uri) ;
			
		} catch (URISyntaxException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
		return null;
	}

}
