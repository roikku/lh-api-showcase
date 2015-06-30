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

package lh.api.showcase.client.operations.service;

import lh.api.showcase.shared.AirportCode;
import lh.api.showcase.shared.api.lh.HttpErrorResponseException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("operations")
public interface OperationsService extends RemoteService {
	String getSchedules (AirportCode origin, AirportCode destination, String departureDate, Boolean directFlight) throws HttpErrorResponseException ;
	String getFlightStatus (String flightNumber, String departureDate) throws HttpErrorResponseException ;
	String getFlightStatusByRoute (AirportCode origin, AirportCode destination, String departureDate) throws HttpErrorResponseException ;
	String getArrivalsStatus (AirportCode arrivalAirport, String startDate, String endDate) throws HttpErrorResponseException ;
	String getDeparturesStatus (AirportCode departureAirport, String startDate, String endDate) throws HttpErrorResponseException ;
}
