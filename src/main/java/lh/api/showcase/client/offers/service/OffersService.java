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


package lh.api.showcase.client.offers.service;

import lh.api.showcase.shared.AirportCode;
import lh.api.showcase.shared.CabinClass;
import lh.api.showcase.shared.api.lh.HttpErrorResponseException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("offers")
public interface OffersService extends RemoteService {
	String getSeatMaps (String flightNumber, AirportCode origin, AirportCode destination, String departureDate, CabinClass cabinClass) throws HttpErrorResponseException ;
}
