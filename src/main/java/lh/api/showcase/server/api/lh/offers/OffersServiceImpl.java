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

package lh.api.showcase.server.api.lh.offers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import lh.api.showcase.client.offers.service.OffersService;
import lh.api.showcase.server.util.HttpQueryUtils;
import lh.api.showcase.shared.AirportCode;
import lh.api.showcase.shared.CabinClass;
import lh.api.showcase.shared.api.lh.HttpErrorResponseException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class OffersServiceImpl extends RemoteServiceServlet implements OffersService {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(OffersServiceImpl.class.getName());
	
	@Override
	public String getSeatMaps(String flightNumber, AirportCode origin,
			AirportCode destination, String departureDate, CabinClass cabinClass) throws HttpErrorResponseException {
		
		// e.g., https://api.lufthansa.com/v1/offers/seatmaps/LH741/KIX/FRA/2015-06-25/C
		OffersRequestFactoryImpl reqFact = new OffersRequestFactoryImpl () ;
		try {
			URI uri = reqFact.getRequestUri((NameValuePair) new BasicNameValuePair("seatmaps", ""),
									(List<NameValuePair>) Arrays.asList(
											(NameValuePair) new BasicNameValuePair(flightNumber, ""),
											(NameValuePair) new BasicNameValuePair(origin.toString(), ""),
											(NameValuePair) new BasicNameValuePair(destination.toString(), ""),
											(NameValuePair) new BasicNameValuePair(departureDate, ""),
											(NameValuePair) new BasicNameValuePair(cabinClass.toString(), "")),
									null) ;
			
			return HttpQueryUtils.executeQuery (uri) ;
			
		} catch (URISyntaxException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
		return null;
	}
}
