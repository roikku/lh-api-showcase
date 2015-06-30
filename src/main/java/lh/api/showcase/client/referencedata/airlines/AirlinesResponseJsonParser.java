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

package lh.api.showcase.client.referencedata.airlines;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

import lh.api.showcase.client.JsonParser;
import lh.api.showcase.client.JsonParserUtils;

public class AirlinesResponseJsonParser implements JsonParser<List<Airline>> {

	@Override
	public List<Airline> parse(String json) {

		List<Airline> ret = new ArrayList<Airline> () ;
		
		JSONValue jsv = JSONParser.parseStrict(json);
		JSONObject  jobj = jsv.isObject() ;
		
		JSONValue jsAirlineResourceVal = jobj.get("AirlineResource") ;
		JSONObject  jsAirlineResourceObj = jsAirlineResourceVal.isObject() ;
		
		JSONValue jsAirlinesVal = jsAirlineResourceObj.get("Airlines") ;
		JSONObject  jsAirlinesObj = jsAirlinesVal.isObject() ;

		JSONValue jsAirlineVal = jsAirlinesObj.get("Airline") ;
		JSONObject  jsAirlineObj = jsAirlineVal.isObject() ;
				
		if (jsAirlineObj == null) {
			JSONArray jarrayAirlines = jsAirlineVal.isArray() ;
			if (jarrayAirlines != null) {
				for (int i = 0 ; i < jarrayAirlines.size() ; ++i) {
					JSONObject jsAirlineObject =jarrayAirlines.get(i).isObject() ;
					if (jsAirlineObject == null) {
						continue ;
					}
					Airline airline = getAirlineInfo (jsAirlineObject) ;
					if (airline != null) {
						ret.add(airline) ;
					}
				}
			}
		} else {
			Airline airline = getAirlineInfo (jsAirlineObj) ;
			if (airline != null) {
				ret.add(airline) ;
			}
		}
		return ret ;
	}
	
	private Airline getAirlineInfo (JSONObject  jsAirlineObj) {
		
		JSONValue jsAirlineIdVal = jsAirlineObj.get("AirlineID") ;
		JSONValue jsAirlineIdIcaoVal = jsAirlineObj.get("AirlineID_ICAO") ;
				
		if (jsAirlineIdVal == null || jsAirlineIdIcaoVal == null){
			return null ;
		}
		JSONString  jsAirlineIdStr = jsAirlineIdVal.isString() ;
		JSONString  jsAirlineIdIcaoStr = jsAirlineIdIcaoVal.isString() ;
			
		JSONObject jsNamesObj = jsAirlineObj.get("Names").isObject() ; 
		
		JSONValue jsOtherIdsVal = jsAirlineObj.get("OtherIDs") ; 
		JSONObject jsOtherIdsObj = null ; 
		if (jsOtherIdsVal != null) {
			jsOtherIdsObj = jsOtherIdsVal.isObject() ; 
		}
			
		return new Airline((jsAirlineIdStr==null)?(""):(jsAirlineIdStr.toString().replace("\"", "")),
						(jsAirlineIdIcaoStr==null)?(""):(jsAirlineIdIcaoStr.toString().replace("\"", "")),
						getOtherIDsInfo(jsOtherIdsObj),
						JsonParserUtils.getNamesMap (jsNamesObj)) ;
		
	}
	
	
	public Set<String> getOtherIDsInfo(JSONObject jsOtherIdsObj) {
		
		if (jsOtherIdsObj == null) {
			return null ;
		}
		Set<String> res = new HashSet<String> () ;
		
		JSONValue jsOtherIdVal = jsOtherIdsObj.get("OtherID") ;
		JSONObject jsOtherIdObj = jsOtherIdVal.isObject();
		
		if (jsOtherIdObj == null) {

			JSONArray  jsOtherIdArray = jsOtherIdVal.isArray() ;
			if (jsOtherIdArray != null) {
				for (int i = 0 ; i < jsOtherIdArray.size() ; ++i) {
	
					JSONObject jsTmpOtherIdObj =jsOtherIdArray.get(i).isObject() ;
					if (jsTmpOtherIdObj == null){
						continue ;
					}
					String oid = getOtherIDInfo (jsTmpOtherIdObj) ;
					if (oid != null) {
						res.add(oid) ;
					}		}
			}
		} else {
			String oid = getOtherIDInfo (jsOtherIdObj) ;
			if (oid != null) {
				res.add(oid) ;
			}
		}
		return res ;
	}
	
	
	public String getOtherIDInfo (JSONObject jsOtherIdObj) {
		JSONValue jsDescriptionVal = jsOtherIdObj.get("@Description") ;
		
		@SuppressWarnings("unused")
		JSONString  jsDescriptionStr = jsDescriptionVal.isString() ;
		
		JSONValue jsNameVal = jsOtherIdObj.get("$") ;
		JSONString  jsNameStr = jsNameVal.isString() ;

		return (jsNameStr==null)?(null):(jsNameStr.toString().replace("\"", "")) ;
	}
}
