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

package lh.api.showcase.client.referencedata.cities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import lh.api.showcase.client.JsonParser;
import lh.api.showcase.client.JsonParserUtils;

public class CitiesResponseJsonParser implements JsonParser<ArrayList<City>> {

	@Override
	public ArrayList<City> parse(String json) {

		ArrayList<City> ret = new ArrayList<City> () ;
		
		JSONValue jsv = JSONParser.parseStrict(json);
		JSONObject  jobj = jsv.isObject() ;
		
		JSONValue jsCityResourceVal = jobj.get("CityResource") ;
		JSONObject  jsCityResourceObj = jsCityResourceVal.isObject() ;
		
		JSONValue jsCitiesVal = jsCityResourceObj.get("Cities") ;
		JSONObject  jsCitiesObj = jsCitiesVal.isObject() ;

		JSONValue jsCityVal = jsCitiesObj.get("City") ;
		JSONObject  jsCityObj = jsCityVal.isObject() ;
				
		if (jsCityObj == null) {
			JSONArray jarrayCities = jsCityVal.isArray() ;
			if (jarrayCities != null) {
				for (int i = 0 ; i < jarrayCities.size() ; ++i) {
					JSONObject jsCityObject =jarrayCities.get(i).isObject() ;
					if (jsCityObject == null) {
						continue ;
					}
					City city = getCityInfo (jsCityObject) ;
					if (city != null) {
						ret.add(city) ;
					}
				}
			}
		} else {
			City city = getCityInfo (jsCityObj) ;
			if (city != null) {
				ret.add(city) ;
			}
		}
		return ret ;
	}
	
	
	private City getCityInfo (JSONObject  jsCityObj) {
		JSONValue jsCityCodeVal = jsCityObj.get("CityCode") ;
		JSONValue jsCountryCodeVal = jsCityObj.get("CountryCode") ;
				
		if (jsCityCodeVal == null || jsCountryCodeVal == null){
			return null ;
		}
		JSONString  jsCityCodeStr = jsCityCodeVal.isString() ;
		JSONString  jsCountryCodeStr = jsCountryCodeVal.isString() ;
			
		JSONNumber jsLatitudeNum = null ;
		JSONNumber jsLongitudeNum = null ;
		JSONValue jsCoordinateVal = jsCityObj.get("Position") ;
		if (jsCoordinateVal != null) {
			JSONObject jsCoordinateObj = jsCoordinateVal.isObject().get("Coordinate").isObject() ;
			jsLatitudeNum = jsCoordinateObj.get("Latitude").isNumber() ;
			jsLongitudeNum = jsCoordinateObj.get("Longitude").isNumber() ;
		}
		
		JSONObject jsNamesObj = jsCityObj.get("Names").isObject() ; 
		
		JSONValue jsAirportsVal = jsCityObj.get("Airports") ;
		JSONObject jsAirportsObj = (jsAirportsVal==null)?(null):(jsAirportsVal.isObject()) ; 
		
		return new City((jsCityCodeStr==null)?(""):(jsCityCodeStr.toString().replace("\"", "")), 
				(jsCountryCodeStr==null)?(""):(jsCountryCodeStr.toString().replace("\"", "")), 
				(jsLatitudeNum==null)?(null):(jsLatitudeNum.doubleValue()), 
				(jsLongitudeNum==null)?(null):(jsLongitudeNum.doubleValue()), 
				getAirportInfo(jsAirportsObj),
				JsonParserUtils.getNamesMap (jsNamesObj)) ;
	}

	
	public static Set<String> getAirportInfo (JSONObject jsAirportsObj) {
		if (jsAirportsObj == null) {
			return null ;
		}
		Set<String> ret = new HashSet<String> () ;
		JSONValue jsAirportCodeVal = jsAirportsObj.get("AirportCode") ;
		JSONString jsjsAirportCodeValStr = jsAirportCodeVal.isString();

		if (jsjsAirportCodeValStr == null) {

			JSONArray  jsAirportCodeArray = jsAirportCodeVal.isArray() ;
			if (jsAirportCodeArray != null) {
				for (int i = 0 ; i < jsAirportCodeArray.size() ; ++i) {
	
					JSONString jsCodeStr =jsAirportCodeArray.get(i).isString() ;
					if (jsCodeStr == null){
						continue ;
					}	
					ret.add(jsCodeStr.toString().replace("\"", "")) ;
				}
			}
		} else {
			ret.add(jsjsAirportCodeValStr.toString().replace("\"", "")) ;
		}			
		return ret ;
	}
}
