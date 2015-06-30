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

package lh.api.showcase.client.referencedata.airports;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;

import lh.api.showcase.client.JsonParser;
import lh.api.showcase.client.JsonParserUtils;

public class AirportsResponseJsonParser implements JsonParser<List<Airport>> {

	@Override
	public List<Airport> parse(String json) {
		
		List<Airport> ret = new ArrayList<Airport> () ;
		
		JSONValue jsv = JSONParser.parseStrict(json);
		JSONObject  jobj = jsv.isObject() ;
		
		JSONValue jsAirportResourceVal = jobj.get("AirportResource") ;
		JSONObject  jsAirportResourceObj = jsAirportResourceVal.isObject() ;
		
		JSONValue jsAirportsVal = jsAirportResourceObj.get("Airports") ;
		JSONObject  jsAirportsObj = jsAirportsVal.isObject() ;

		JSONValue jsAirportVal = jsAirportsObj.get("Airport") ;
		JSONObject  jsAirportObj = jsAirportVal.isObject() ;
				
		if (jsAirportObj == null) {
			JSONArray jarrayAirports = jsAirportVal.isArray() ;
			if (jarrayAirports != null) {
				for (int i = 0 ; i < jarrayAirports.size() ; ++i) {
					JSONObject jsAirportObject =jarrayAirports.get(i).isObject() ;
					if (jsAirportObject == null) {
						continue ;
					}
					Airport airport = JsonParserUtils.getAirportInfo (jsAirportObject) ;
					if (airport != null) {
						ret.add(airport) ;
					}
				}
			}
		} else {
			Airport airport = JsonParserUtils.getAirportInfo (jsAirportObj) ;
			if (airport != null) {
				ret.add(airport) ;
			}
		}
		return ret ;
	}
}

