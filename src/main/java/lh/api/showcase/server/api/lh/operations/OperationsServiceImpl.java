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

package lh.api.showcase.server.api.lh.operations;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import lh.api.showcase.client.operations.service.OperationsService;
import lh.api.showcase.server.util.HttpQueryUtils;
import lh.api.showcase.shared.AirportCode;
import lh.api.showcase.shared.api.lh.HttpErrorResponseException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class OperationsServiceImpl extends RemoteServiceServlet implements OperationsService {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(OperationsServiceImpl.class.getName());
	
	@Override
	public String getSchedules(AirportCode origin,
			AirportCode destination, String departureDate, Boolean directFlight) throws HttpErrorResponseException {
		
		// e.g., https://api.lufthansa.com/v1/operations/schedules/FRA/KIX/2014-11-01?directFlights=true
		OperationsRequestFactoryImpl reqFact = new OperationsRequestFactoryImpl () ;
		try {
			URI uri = reqFact.getRequestUri((NameValuePair) new BasicNameValuePair("schedules", ""),
									(List<NameValuePair>) Arrays.asList(
											(NameValuePair) new BasicNameValuePair(origin.toString(), ""),
											(NameValuePair) new BasicNameValuePair(destination.toString(), ""),
											(NameValuePair) new BasicNameValuePair(departureDate, "")),
									Arrays.asList((NameValuePair) new BasicNameValuePair("directFlights", (directFlight==null)?("false"):(directFlight.toString().toLowerCase())))) ;
			
			return HttpQueryUtils.executeQuery (uri) ;
			
		} catch (URISyntaxException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
		return null;
	}

	
	@Override
	public String getFlightStatus(String flightNumber, String departureDate) throws HttpErrorResponseException {
		
		// e.g., https://api.lufthansa.com/v1/operations/flightstatus/LH741/2015-06-25
		OperationsRequestFactoryImpl reqFact = new OperationsRequestFactoryImpl () ;
		try {
			URI uri = reqFact.getRequestUri((NameValuePair) new BasicNameValuePair("flightstatus", ""),
									(List<NameValuePair>) Arrays.asList(
											(NameValuePair) new BasicNameValuePair(flightNumber, ""),
											(NameValuePair) new BasicNameValuePair(departureDate, "")),
									null) ;
			
			return HttpQueryUtils.executeQuery (uri) ;
			
		} catch (URISyntaxException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
		return null;
	}


	@Override
	public String getFlightStatusByRoute(AirportCode origin,
			AirportCode destination, String departureDate) throws HttpErrorResponseException {

		// e.g., https://api.lufthansa.com/v1/operations/flightstatus/route/FRA/KIX/2015-06-25
		OperationsRequestFactoryImpl reqFact = new OperationsRequestFactoryImpl () ;
		try {
			URI uri = reqFact.getRequestUri((NameValuePair) new BasicNameValuePair("flightstatus", ""),
									(List<NameValuePair>) Arrays.asList(
											(NameValuePair) new BasicNameValuePair("route", ""),
											(NameValuePair) new BasicNameValuePair(origin.toString(), ""),
											(NameValuePair) new BasicNameValuePair(destination.toString(), ""),
											(NameValuePair) new BasicNameValuePair(departureDate, "")),
									null) ;
			
			return HttpQueryUtils.executeQuery (uri) ;
			
		} catch (URISyntaxException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
		return null;
	}


	@Override
	public String getArrivalsStatus(AirportCode arrivalAirport,
			String startDate, String endDate) throws HttpErrorResponseException {

		// e.g., https://api.lufthansa.com/v1/operations/flightstatus/arrivals/FRA/2015-06-25T14:00/2015-06-25T16:00
		OperationsRequestFactoryImpl reqFact = new OperationsRequestFactoryImpl () ;
		try {
			URI uri = reqFact.getRequestUri((NameValuePair) new BasicNameValuePair("flightstatus", ""),
									(List<NameValuePair>) Arrays.asList(
											(NameValuePair) new BasicNameValuePair("arrivals", ""),
											(NameValuePair) new BasicNameValuePair(arrivalAirport.toString(), ""),
											(NameValuePair) new BasicNameValuePair(startDate, ""),
											(NameValuePair) new BasicNameValuePair(endDate, "")),
									null) ;
			
			return HttpQueryUtils.executeQuery (uri) ;
			
		} catch (URISyntaxException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
		return null;
	}


	@Override
	public String getDeparturesStatus(AirportCode departureAirport,
			String startDate, String endDate) throws HttpErrorResponseException {

		// e.g., https://api.lufthansa.com/v1/operations/flightstatus/departures/FRA/2015-06-25T14:00/2015-06-25T16:00
		OperationsRequestFactoryImpl reqFact = new OperationsRequestFactoryImpl () ;
		try {
			URI uri = reqFact.getRequestUri((NameValuePair) new BasicNameValuePair("flightstatus", ""),
									(List<NameValuePair>) Arrays.asList(
											(NameValuePair) new BasicNameValuePair("departures", ""),
											(NameValuePair) new BasicNameValuePair(departureAirport.toString(), ""),
											(NameValuePair) new BasicNameValuePair(startDate, ""),
											(NameValuePair) new BasicNameValuePair(endDate, "")),
									null) ;
			
			return HttpQueryUtils.executeQuery (uri) ;
			
		} catch (URISyntaxException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
		return null;
	}

}
