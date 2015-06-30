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

package lh.api.showcase.client.operations;

import lh.api.showcase.client.JsonParserUtils;
import lh.api.showcase.shared.FlightStatusCode;
import lh.api.showcase.shared.TimeStatusCode;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

public class JsonParserFlightUtils {

	private JsonParserFlightUtils () { super () ; throw new IllegalStateException () ; }
	
	public static Flight getFlightInfo (JSONObject  jsFlightObj) {
		
		if (jsFlightObj == null) {
			return null ;
		}
		
		// departure
		JSONObject  jsDepartureObj = jsFlightObj.get("Departure").isObject() ;
		Flight.FlightNode flightNodeDeparture = getFlightNodeInfo (jsDepartureObj) ;
		Flight.Departure departure = null ;
		if (flightNodeDeparture != null) {
			departure = new Flight.Departure (flightNodeDeparture) ;
		}
		
		// arrival
		JSONObject  jsArrivalObj = jsFlightObj.get("Arrival").isObject() ;
		Flight.FlightNode flightNodeArrival = getFlightNodeInfo (jsArrivalObj) ;
		Flight.Arrival arrival = null ;
		if (flightNodeDeparture != null) {
			arrival = new Flight.Arrival (flightNodeArrival) ;
		}				
		
		// aircraft code
		String aircraftCode = JsonParserUtils.getAircraftCode (jsFlightObj.get("Equipment").isObject().get("AircraftCode")) ;
		
		//MarketingCarrier
		JSONObject jsMarketingCarrierObj = jsFlightObj.get("MarketingCarrier").isObject() ;
		JSONString jsAirlineIdStr = jsMarketingCarrierObj.get("AirlineID").isString() ;
		
		JSONString jsFlightNumberStr = jsMarketingCarrierObj.get("FlightNumber").isString() ;
		JSONNumber jsFlightNumberNum = jsMarketingCarrierObj.get("FlightNumber").isNumber() ;

		String flightNumber = (jsFlightNumberStr!=null)?(jsFlightNumberStr.toString().replace("\"", ""))
				:((jsFlightNumberNum!=null)?(String.valueOf(jsFlightNumberNum.doubleValue())):("")) ;
		
		// details
		JSONValue jsDetailsVal = jsFlightObj.get("Details") ;
		JSONObject jsDetailsObj = null ;
		if (jsDetailsVal != null) {
			jsDetailsObj = jsDetailsVal.isObject() ;
		}
		
		// flight status
		JSONString jsFlightStatusCodeStr = getStringIfExist (jsFlightObj, "FlightStatus", "Code") ;
		JSONString jsFlightStatusDefinitionStr = getStringIfExist (jsFlightObj, "FlightStatus", "Definition") ;
		
		return new Flight (
				departure, 
				arrival, 
				aircraftCode, 
				(jsAirlineIdStr==null)?(""):(jsAirlineIdStr.toString().replace("\"", "")),
				flightNumber, 
				getDetailsInfo(jsDetailsObj),
				new Flight.StatusCodeDefinition(
						(jsFlightStatusCodeStr==null)?(FlightStatusCode.NA.toString()):(jsFlightStatusCodeStr.toString().replace("\"", "")),
						(jsFlightStatusDefinitionStr==null)?(""):(jsFlightStatusDefinitionStr.toString().replace("\"", "")))) ;
	}
	
	
	
	private static JSONString getStringIfExist (JSONObject jsObj, String object, String property) {
		JSONValue jsObjectVal = jsObj.get(object) ;
		JSONString jsStr = null ;
		if (jsObjectVal != null) {
			JSONValue jsObjectPropVal = jsObjectVal.isObject().get(property) ;
			if (jsObjectPropVal != null) {
				jsStr = jsObjectPropVal.isString () ;
			}
		}
		return jsStr ;
	}	
	
	
	private static JSONNumber getNumberIfExist (JSONObject jsObj, String object, String property) {
		JSONValue jsObjectVal = jsObj.get(object) ;
		JSONNumber jsNum = null ;
		if (jsObjectVal != null) {
			JSONValue jsObjectPropVal = jsObjectVal.isObject().get(property) ;
			if (jsObjectPropVal != null) {
				jsNum = jsObjectPropVal.isNumber () ;
			}
		}
		return jsNum ;
	}	
	
	
	private static JSONString getStringIfExist (JSONObject jsObj, String object, String alternativeObject, String property) {
		JSONValue jsVal = jsObj.get(object) ;
		if (jsVal == null) {
			jsVal = jsObj.get(alternativeObject) ;
		}
		JSONString jsStr = null ;
		if (jsVal != null) {
			JSONValue jsObjectPropVal = jsVal.isObject().get(property) ;
			if (jsObjectPropVal != null) {
				jsStr = jsObjectPropVal.isString () ;
			}
		}
		return jsStr ;
	}	
	
	
	private static Flight.Details getDetailsInfo (JSONObject jsDetailsObj) {
		if (jsDetailsObj == null) {
			return null ;
		}
		JSONNumber jsStopsNumber = jsDetailsObj.get("Stops").isObject().get("StopQuantity").isNumber() ;
		JSONNumber jsDaysOfOperationNumber = jsDetailsObj.get("DaysOfOperation").isNumber() ;		

		JSONObject jsDatePeriodObj = jsDetailsObj.get("DatePeriod").isObject() ;
		JSONString jsEffectiveStr =  jsDatePeriodObj.get("Effective").isString() ;
		JSONString jsExpirationStr =  jsDatePeriodObj.get("Expiration").isString() ;
		
		Flight.Details.Builder detailsBuilder = new Flight.Details.Builder() ;
		detailsBuilder.setStopQuantity(Long.valueOf((long) jsStopsNumber.doubleValue()))
					.setDaysOfOperation(String.valueOf(jsDaysOfOperationNumber.doubleValue()))
					.setEffectiveDateStr((jsEffectiveStr==null)?(""):(jsEffectiveStr.toString().replace("\"", "")))
					.setExpirationDateStr((jsExpirationStr==null)?(""):(jsExpirationStr.toString().replace("\"", ""))) ;
		
		return detailsBuilder.build() ;
	}
		
	
	private static Flight.FlightNode getFlightNodeInfo (JSONObject  jsNodeObj) {
		if (jsNodeObj == null) {
			return null ;
		}
		JSONString jsAirportCodeStr = jsNodeObj.get("AirportCode").isString() ;
		
		JSONString jsScheduledTimeLocalStr = jsNodeObj.get("ScheduledTimeLocal").isObject().get("DateTime").isString() ;
		JSONString jsScheduledTimeUtcStr = getStringIfExist (jsNodeObj, "ScheduledTimeUTC", "DateTime") ;
				
		JSONString jsEstimatedOrActualTimeLocalStr = getStringIfExist (jsNodeObj, "EstimatedTimeLocal", "ActualTimeLocal", "DateTime") ; 
		JSONString jsEstimatedOrActualTimeUtcStr = getStringIfExist (jsNodeObj, "EstimatedTimeUTC", "ActualTimeUTC", "DateTime") ; 

		JSONString jsTimeStatusCodeStr = getStringIfExist (jsNodeObj, "TimeStatus", "Code") ;
		JSONString jsTimeStatusDefinitionStr = getStringIfExist (jsNodeObj, "TimeStatus", "Definition") ;
		
		JSONString jsTerminalGateStr = getStringIfExist (jsNodeObj, "Terminal", "Gate") ;
		
		JSONString jsTerminalStr = getStringIfExist (jsNodeObj, "Terminal", "Name") ;
		JSONNumber jsTerminalNum = getNumberIfExist (jsNodeObj, "Terminal", "Name") ;
		
		String terminal = (jsTerminalStr!=null)?(jsTerminalStr.toString().replace("\"", ""))
				:((jsTerminalNum!=null)?(String.valueOf(jsTerminalNum.doubleValue())):("")) ;
		
		Flight.FlightNode.Builder flightNodeBuilder = new Flight.FlightNode.Builder();
		flightNodeBuilder.setAirportCode((jsAirportCodeStr==null)?(""):(jsAirportCodeStr.toString().replace("\"", "")))
						.setScheduledTimeLocal((jsScheduledTimeLocalStr==null)?(""):(jsScheduledTimeLocalStr.toString().replace("\"", "")))
						.setScheduledTimeUtc((jsScheduledTimeUtcStr==null)?(""):(jsScheduledTimeUtcStr.toString().replace("\"", "")))
						.setEstimatedOrActualTimeLocal((jsEstimatedOrActualTimeLocalStr==null)?(""):(jsEstimatedOrActualTimeLocalStr.toString().replace("\"", "")))
						.setEstimatedOrActualTimeUtc((jsEstimatedOrActualTimeUtcStr==null)?(""):(jsEstimatedOrActualTimeUtcStr.toString().replace("\"", "")))
						.setTimeStatus(new Flight.StatusCodeDefinition(
												(jsTimeStatusCodeStr==null)?(TimeStatusCode.NO.toString()):(jsTimeStatusCodeStr.toString().replace("\"", "")),
												(jsTimeStatusDefinitionStr==null)?(""):(jsTimeStatusDefinitionStr.toString().replace("\"", ""))))
						.setTerminal(terminal)
						.setTerminalGate((jsTerminalGateStr==null)?(""):(jsTerminalGateStr.toString().replace("\"", "")));
	
		return flightNodeBuilder.build();
	}
}
