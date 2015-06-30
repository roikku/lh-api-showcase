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

package lh.api.showcase.client.operations.schedules;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

import lh.api.showcase.client.JsonParser;
import lh.api.showcase.client.operations.Flight;
import lh.api.showcase.client.operations.JsonParserFlightUtils;

public class SchedulesResponseJsonParser implements JsonParser<List<Schedule>> {

	@Override
	public List<Schedule> parse(String json) {

		List<Schedule> ret = new ArrayList<Schedule> () ;
		
		JSONValue jsv = JSONParser.parseStrict(json);
		JSONObject  jobj = jsv.isObject() ;
		
		JSONValue jsScheduleResourceVal = jobj.get("ScheduleResource") ;
		JSONObject  jsScheduleResourceObj = jsScheduleResourceVal.isObject() ;
		
		JSONValue jsScheduleVal = jsScheduleResourceObj.get("Schedule") ;
		JSONObject  jsScheduleObj = jsScheduleVal.isObject() ;

		if (jsScheduleObj == null) {
			JSONArray jarraySchedule = jsScheduleVal.isArray() ;
			if (jarraySchedule != null) {
				for (int i = 0 ; i < jarraySchedule.size() ; ++i) {
					JSONObject jsScheduleObject =jarraySchedule.get(i).isObject() ;
					Schedule schedule = getScheduleInfo (jsScheduleObject) ;
					if (schedule != null) {
						ret.add(schedule) ;
					}
				}
			}
		} else {
			Schedule schedule = getScheduleInfo (jsScheduleObj) ;
			if (schedule != null) {
				ret.add(schedule) ;
			}
		}
		return ret ;
	}
	
	private Schedule getScheduleInfo (JSONObject  jsScheduleObj) {
		
		if (jsScheduleObj == null) {
			return null ;
		}
		JSONValue jsTotalJourneyVal = jsScheduleObj.get("TotalJourney") ;
		JSONObject  jsTotalJourneyObj = jsTotalJourneyVal.isObject() ;
		
		JSONString jsDurationStr = jsTotalJourneyObj.get("Duration").isString() ;
		
		List<Flight> flightList = new ArrayList<Flight> () ;
		JSONValue jsFlightVal = jsScheduleObj.get("Flight") ;
		JSONObject  jsFlightObj = jsFlightVal.isObject() ;
		if (jsFlightObj == null) {
			JSONArray jarrayFlight = jsFlightVal.isArray() ;
			if (jarrayFlight != null) {
				for (int i = 0 ; i < jarrayFlight.size() ; ++i) {
					JSONObject jsFlightObject =jarrayFlight.get(i).isObject() ;
					Flight flight = JsonParserFlightUtils.getFlightInfo (jsFlightObject) ;
					if (flight != null) {
						flightList.add(flight) ;
					}
				}
			}
		} else {
			Flight flight = JsonParserFlightUtils.getFlightInfo (jsFlightObj) ;
			if (flight != null) {
				flightList.add(flight) ;
			}
		}
		
		return new Schedule ((jsDurationStr==null)?(""):(jsDurationStr.toString().replace("\"", "")),
				flightList) ;
	}
	

}