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

package lh.api.showcase.server.lh.api.referencedata;

import static org.junit.Assert.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import lh.api.showcase.server.api.lh.referencedata.ReferenceDataRequestFactoryImpl;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

public class ReferenceDataRequestFactoryImplTest {


	private static Logger logger = Logger.getLogger(ReferenceDataRequestFactoryImplTest.class.getName());
	
	@Test
	public void shouldConstructCountriesRequestUri() {

		ReferenceDataRequestFactoryImpl reqFact = new ReferenceDataRequestFactoryImpl () ;
		try {
			URI constructedUri = reqFact.getRequestUri((NameValuePair) new BasicNameValuePair("countries", "DK"),
									null,
									Arrays.asList((NameValuePair) new BasicNameValuePair("lang", "EN"))) ;
			
			URI referenceUri = new URI(
					"https://api.lufthansa.com/v1/references/countries/DK?lang=EN") ;
				
			logger.log(Level.INFO, "constructed: " + constructedUri.toString());
			logger.log(Level.INFO, "reference: " + referenceUri.toString());
			assertTrue (referenceUri.equals(constructedUri)) ;
		} catch (URISyntaxException e) {
			assertTrue(false);
		}
	} 
}
