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

package lh.api.showcase.client;

import java.util.HashMap;
import java.util.Map;

import lh.api.showcase.client.referencedata.airports.Airport;
import lh.api.showcase.shared.LanguageCode;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

public class JsonParserUtils {

	private JsonParserUtils () { super () ; throw new IllegalStateException () ; }
	
	public static void getNameInfo (JSONObject  jsNameObj, Map<LanguageCode, String> res) {
		JSONValue jsLang = jsNameObj.get("@LanguageCode") ;
		JSONString  jsLangStr = jsLang.isString() ;
		
		JSONValue jsNameVal = jsNameObj.get("$") ;
		JSONString  jsNameStr = jsNameVal.isString() ;

		res.put(LanguageCode.valueOf(jsLangStr.toString().replace("\"", "").toUpperCase()), jsNameStr.toString().replace("\"", "")) ;
	}
	
	
	public static Map<LanguageCode, String> getNamesMap(JSONObject jsNamesObj) {
		
		Map<LanguageCode, String> res = new HashMap<LanguageCode, String> () ;
		
		JSONValue jsNameVal = jsNamesObj.get("Name") ;
		JSONObject jsNameObj = jsNameVal.isObject();

		if (jsNameObj == null) {

			JSONArray  jsNameArray = jsNameVal.isArray() ;
			if (jsNameArray != null) {
				for (int i = 0 ; i < jsNameArray.size() ; ++i) {
	
					JSONObject jsTmpNamesObj =jsNameArray.get(i).isObject() ;
					if (jsTmpNamesObj == null){
						continue ;
					}
					JsonParserUtils.getNameInfo(jsTmpNamesObj, res) ;				}
			}
		} else {
			JsonParserUtils.getNameInfo(jsNameObj, res) ;
		}
		return res ;
	}
	
	
	public static Airport getAirportInfo (JSONObject  jsAirportObj) {
		
		JSONValue jsAirportCodeVal = jsAirportObj.get("AirportCode") ;
		JSONValue jsCityCodeVal = jsAirportObj.get("CityCode") ;
		JSONValue jsCountryCodeVal = jsAirportObj.get("CountryCode") ;
		JSONValue jsLocationTypeVal = jsAirportObj.get("LocationType") ;
				
		if (jsAirportCodeVal == null || jsCityCodeVal == null || jsCountryCodeVal == null || jsLocationTypeVal == null){
			return null ;
		}
		JSONString  jsAirportCodeStr = jsAirportCodeVal.isString() ;
		JSONString  jsCityCodeStr = jsCityCodeVal.isString() ;
		JSONString  jsCountryCodeStr = jsCountryCodeVal.isString() ;
		JSONString  jsLocationTypeStr = jsLocationTypeVal.isString() ;
			
		JSONObject jsCoordinateObj = jsAirportObj.get("Position").isObject().get("Coordinate").isObject() ;
		JSONNumber jsLatitudeNum = jsCoordinateObj.get("Latitude").isNumber() ;
		JSONNumber jsLongitudeNum = jsCoordinateObj.get("Longitude").isNumber() ;
		
		JSONObject jsNamesObj = jsAirportObj.get("Names").isObject() ; 
				
		return new Airport((jsAirportCodeStr==null)?(""):(jsAirportCodeStr.toString().replace("\"", "")), 
						(jsCityCodeStr==null)?(""):(jsCityCodeStr.toString().replace("\"", "")), 
						(jsCountryCodeStr==null)?(""):(jsCountryCodeStr.toString().replace("\"", "")), 
						(jsLocationTypeStr==null)?(""):(jsLocationTypeStr.toString().replace("\"", "")),
						jsLatitudeNum.doubleValue(), jsLongitudeNum.doubleValue(), 
						JsonParserUtils.getNamesMap (jsNamesObj)) ;
		
	}
	
	
	public static String getAircraftCode (JSONValue jsAircraftCodeVal) {
		if (jsAircraftCodeVal == null){
			return null ;
		}
		JSONString jsAircraftCodeStr = jsAircraftCodeVal.isString() ;
		JSONNumber jsAircraftCodeNum = null ;
		if (jsAircraftCodeStr == null) {
			jsAircraftCodeNum = jsAircraftCodeVal.isNumber() ;
		}
		return (jsAircraftCodeStr!=null)?(jsAircraftCodeStr.toString().replace("\"", ""))
				:((jsAircraftCodeNum!=null)?(String.valueOf(jsAircraftCodeNum.doubleValue())):("")) ;
	}
}
