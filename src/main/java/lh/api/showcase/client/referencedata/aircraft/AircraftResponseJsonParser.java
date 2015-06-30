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

package lh.api.showcase.client.referencedata.aircraft;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import lh.api.showcase.client.JsonParser;
import lh.api.showcase.client.JsonParserUtils;

public class AircraftResponseJsonParser implements JsonParser<List<Aircraft>> {

	@Override
	public List<Aircraft> parse(String json) {

		List<Aircraft> ret = new ArrayList<Aircraft> () ;
		
		JSONValue jsv = JSONParser.parseStrict(json);
		JSONObject  jobj = jsv.isObject() ;
		
		JSONValue jsAircraftResourceVal = jobj.get("AircraftResource") ;		
		JSONObject  jsAircraftResourceObj = jsAircraftResourceVal.isObject() ;
		
		JSONValue jsAircraftSummariesVal = jsAircraftResourceObj.get("AircraftSummaries") ;
		JSONObject  jsAircraftSummariesObj = jsAircraftSummariesVal.isObject() ;

		JSONValue jsAircraftSummaryVal = jsAircraftSummariesObj.get("AircraftSummary") ;
		JSONObject  jsAircraftSummaryObj = jsAircraftSummaryVal.isObject() ;

		if (jsAircraftSummaryObj == null) {
			JSONArray jarrayAircraftSummary = jsAircraftSummaryVal.isArray() ;
			if (jarrayAircraftSummary != null) {
				for (int i = 0 ; i < jarrayAircraftSummary.size() ; ++i) {
					JSONObject jsTmpAircraftSummaryObject =jarrayAircraftSummary.get(i).isObject() ;
					if (jsTmpAircraftSummaryObject == null) {
						continue ;
					}
					Aircraft Aircraft = getAircraftInfo (jsTmpAircraftSummaryObject) ;
					if (Aircraft != null) {
						ret.add(Aircraft) ;
					}
				}
			}
		} else {
			Aircraft Aircraft = getAircraftInfo (jsAircraftSummaryObj) ;
			if (Aircraft != null) {
				ret.add(Aircraft) ;
			}
		}
		return ret ;
	}
	
	private Aircraft getAircraftInfo (JSONObject  jsAircraftSummaryObj) {
		
		JSONValue jsAircraftCodeVal = jsAircraftSummaryObj.get("AircraftCode") ;
		JSONValue jsAircraftAirlineEquipCodeVal = jsAircraftSummaryObj.get("AirlineEquipCode") ;
				
		if (jsAircraftCodeVal == null){
			return null ;
		}
		String aircraftCode = JsonParserUtils.getAircraftCode (jsAircraftCodeVal) ;
		
		JSONString jsAircraftAirlineEquipCodeStr = null ;
		if (jsAircraftAirlineEquipCodeVal != null){
			jsAircraftAirlineEquipCodeStr = jsAircraftAirlineEquipCodeVal.isString() ;
		}
			
		JSONObject jsNamesObj = jsAircraftSummaryObj.get("Names").isObject() ; 
		
		return new Aircraft (aircraftCode,
						(jsAircraftAirlineEquipCodeStr==null)?(""):(jsAircraftAirlineEquipCodeStr.toString().replace("\"", "")),
						JsonParserUtils.getNamesMap (jsNamesObj)) ;
	}
	
	

}
