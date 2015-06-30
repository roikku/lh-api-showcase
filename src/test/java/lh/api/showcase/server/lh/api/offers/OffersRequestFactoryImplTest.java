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

package lh.api.showcase.server.lh.api.offers;

import static org.junit.Assert.assertTrue;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import lh.api.showcase.server.api.lh.offers.OffersRequestFactoryImpl;
import lh.api.showcase.server.lh.api.referencedata.ReferenceDataRequestFactoryImplTest;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

public class OffersRequestFactoryImplTest {

	private static Logger logger = Logger.getLogger(ReferenceDataRequestFactoryImplTest.class.getName());
	
	@Test
	public void shouldConstructSeatMapsRequestUri() throws URISyntaxException {

		// e.g., https://api.lufthansa.com/v1/offers/seatmaps/LH741/KIX/FRA/2015-06-25/C
		OffersRequestFactoryImpl reqFact = new OffersRequestFactoryImpl () ;

		URI constructedUri = reqFact.getRequestUri((NameValuePair) new BasicNameValuePair("seatmaps", ""),
								(List<NameValuePair>) Arrays.asList(
										(NameValuePair) new BasicNameValuePair("LH741", ""),
										(NameValuePair) new BasicNameValuePair("KIX", ""),
										(NameValuePair) new BasicNameValuePair("FRA", ""),
										(NameValuePair) new BasicNameValuePair("2015-06-25", ""),
										(NameValuePair) new BasicNameValuePair("C", "")),
								null) ;
		URI referenceUri = new URI(
				"https://api.lufthansa.com/v1/offers/seatmaps/LH741/KIX/FRA/2015-06-25/C") ;
			
		logger.log(Level.INFO, "constructed: " + constructedUri.toString());
		logger.log(Level.INFO, "reference: " + referenceUri.toString());
		assertTrue (referenceUri.equals(constructedUri)) ;
	} 
}
