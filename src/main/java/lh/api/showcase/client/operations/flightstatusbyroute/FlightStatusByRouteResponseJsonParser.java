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

package lh.api.showcase.client.operations.flightstatusbyroute;

import java.util.ArrayList;
import java.util.List;

import lh.api.showcase.client.JsonParser;
import lh.api.showcase.client.operations.flightstatus.FlightStatusImpl;
import lh.api.showcase.client.operations.flightstatus.FlightStatusResponseJsonParser;

public class FlightStatusByRouteResponseJsonParser implements JsonParser<List<FlightStatusByRoute>> {

	@Override
	public List<FlightStatusByRoute> parse(String json) {
		
		List<FlightStatusByRoute> ret = new ArrayList<FlightStatusByRoute> () ;
		List<FlightStatusImpl> flightStatusList =  new FlightStatusResponseJsonParser ().parse(json) ;
		for (FlightStatusImpl fs : flightStatusList) {
			ret.add(new FlightStatusByRoute (fs.getFligth())) ;
		}
		return ret ;
	}
}
