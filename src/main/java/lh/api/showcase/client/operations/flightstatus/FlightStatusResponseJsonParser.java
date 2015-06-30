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

package lh.api.showcase.client.operations.flightstatus;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;

import lh.api.showcase.client.JsonParser;
import lh.api.showcase.client.operations.Flight;
import lh.api.showcase.client.operations.JsonParserFlightUtils;

public class FlightStatusResponseJsonParser implements JsonParser<List<FlightStatusImpl>> {

	@Override
	public List<FlightStatusImpl> parse(String json) {

		// the list should contains only one element
		List<FlightStatusImpl> ret = new ArrayList<FlightStatusImpl> () ;
		
		JSONValue jsv = JSONParser.parseStrict(json);
		JSONObject  jobj = jsv.isObject() ;
		
		JSONValue jsFlightResourceVal = jobj.get("FlightStatusResource") ;
		JSONObject  jsFlightResourceObj = jsFlightResourceVal.isObject() ;
		
		JSONValue jsFlightsVal = jsFlightResourceObj.get("Flights") ;
		JSONObject  jsFlightsObj = jsFlightsVal.isObject() ;

		JSONValue jsFlightVal = jsFlightsObj.get("Flight") ;
		JSONObject  jsFlightObj = jsFlightVal.isObject() ;
			
		if (jsFlightObj == null) {
			JSONArray jarrayFlights = jsFlightVal.isArray() ;
			if (jarrayFlights != null) {
				for (int i = 0 ; i < jarrayFlights.size() ; ++i) {
					JSONObject jsFlightObject =jarrayFlights.get(i).isObject() ;
					if (jsFlightObject == null) {
						continue ;
					}
					Flight flight = getFlightStatusInfo (jsFlightObject) ;
					if (flight != null) {
						ret.add(new FlightStatusImpl(flight)) ;
					}
				}
			}
		} else {
			Flight flight = getFlightStatusInfo (jsFlightObj) ;
			if (flight != null) {
				ret.add(new FlightStatusImpl(flight)) ;
			}
		}
		return ret ;
	}

	private Flight getFlightStatusInfo(JSONObject jsFlightObject) {
		if (jsFlightObject == null) {
			return null ;
		}
		return JsonParserFlightUtils.getFlightInfo (jsFlightObject) ;
	}
}
