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

package lh.api.showcase.server.lh.api.opperations;

import static org.junit.Assert.assertTrue;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import lh.api.showcase.server.api.lh.operations.OperationsRequestFactoryImpl;
import lh.api.showcase.server.lh.api.referencedata.ReferenceDataRequestFactoryImplTest;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

public class OperationsRequestFactoryImplTest {

	private static Logger logger = Logger.getLogger(ReferenceDataRequestFactoryImplTest.class.getName());
	
	@Test
	public void shouldConstructScheduleRequestUri() throws URISyntaxException {

		// e.g., https://api.lufthansa.com/v1/operations/schedules/FRA/KIX/2014-11-01?directFlights=true
		OperationsRequestFactoryImpl reqFact = new OperationsRequestFactoryImpl () ;

		URI constructedUri = reqFact.getRequestUri((NameValuePair) new BasicNameValuePair("schedules", ""),
				(List<NameValuePair>) Arrays.asList(
						(NameValuePair) new BasicNameValuePair("FRA", ""),
						(NameValuePair) new BasicNameValuePair("KIX", ""),
						(NameValuePair) new BasicNameValuePair("2014-11-01", "")),
				Arrays.asList((NameValuePair) new BasicNameValuePair("directFlights", "true"))) ;
		URI referenceUri = new URI(
				"https://api.lufthansa.com/v1/operations/schedules/FRA/KIX/2014-11-01?directFlights=true") ;
			
		logger.log(Level.INFO, "constructed: " + constructedUri.toString());
		logger.log(Level.INFO, "reference: " + referenceUri.toString());
		assertTrue (referenceUri.equals(constructedUri)) ;
	} 
	
	
	@Test
	public void shouldConstructFlightStatusRequestUri() throws URISyntaxException {

		// e.g., https://api.lufthansa.com/v1/operations/flightstatus/LH741/2015-06-25
		OperationsRequestFactoryImpl reqFact = new OperationsRequestFactoryImpl () ;

		URI constructedUri = reqFact.getRequestUri((NameValuePair) new BasicNameValuePair("flightstatus", ""),
								(List<NameValuePair>) Arrays.asList(
										(NameValuePair) new BasicNameValuePair("LH741", ""),
										(NameValuePair) new BasicNameValuePair("2015-06-25", "")),
								null) ;

		URI referenceUri = new URI(
				"https://api.lufthansa.com/v1/operations/flightstatus/LH741/2015-06-25") ;
			
		logger.log(Level.INFO, "constructed: " + constructedUri.toString());
		logger.log(Level.INFO, "reference: " + referenceUri.toString());
		assertTrue (referenceUri.equals(constructedUri)) ;
	}
}
