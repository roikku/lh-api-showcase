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

package lh.api.showcase.client.referencedata.countries;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import lh.api.showcase.client.JsonParser;
import lh.api.showcase.client.JsonParserUtils;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

public class CountriesResponseJsonParser implements JsonParser<List<Country>> {
	
	private static Logger logger = Logger.getLogger("CountriesResponseJsonParser");
	
	
	@Override
	public List<Country> parse (String json) {
		List<Country> countries = new ArrayList<Country>();
		
		logger.log(Level.INFO, "JSON parsing");
		JSONValue jsv = JSONParser.parseStrict(json);
		JSONObject  jobj = jsv.isObject() ;
		
		JSONValue jsCountriesResourceVal = jobj.get("CountryResource") ;
		JSONObject  jsCountriesResourceObj = jsCountriesResourceVal.isObject() ;
		
		JSONValue jsCountriesVal = jsCountriesResourceObj.get("Countries") ;
		JSONObject  jsCountriesObj = jsCountriesVal.isObject() ;

		JSONValue jsCountryVal = jsCountriesObj.get("Country") ;
		JSONObject  jsCountryObj = jsCountryVal.isObject() ;
	
		if (jsCountryObj == null) {
			JSONArray jarrayCountries = jsCountryVal.isArray() ;
			if (jarrayCountries != null) {
				for (int i = 0 ; i < jarrayCountries.size() ; ++i) {

					JSONObject jsCountryObject =jarrayCountries.get(i).isObject() ;
					if (jsCountryObject == null){
						continue ;
					}
					getCountryInfo (jsCountryObject, countries) ;
				}
			}
		} else {
			getCountryInfo (jsCountryObj, countries) ;
		}
		return countries ;
	}
	
	
	private void getCountryInfo (JSONObject  jsCountryObj, List<Country> countries) {
		JSONValue jsCountryCodeVal = jsCountryObj.get("CountryCode") ;
		JSONString  jsCountryCodeStr = (jsCountryCodeVal==null)?(null):(jsCountryCodeVal.isString()) ;
		
		JSONValue jsCountryZoneVal = jsCountryObj.get("ZoneCode") ;
		JSONString  jsZoneCodeStr = (jsCountryZoneVal==null)?(null):(jsCountryZoneVal.isString()) ;
		
		JSONValue jsNamesVal = jsCountryObj.get("Names") ;
		JSONObject  jsNamesObj = jsNamesVal.isObject() ;
		
		countries.add(new Country((jsCountryCodeStr==null)?(""):(jsCountryCodeStr.toString().replace("\"", "")), 
				(jsZoneCodeStr==null)?(""):(jsZoneCodeStr.toString().replace("\"", "")), JsonParserUtils.getNamesMap (jsNamesObj))) ;
	}
}
