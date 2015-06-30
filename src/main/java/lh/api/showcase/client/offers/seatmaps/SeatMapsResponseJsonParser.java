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


package lh.api.showcase.client.offers.seatmaps;

import java.util.ArrayList;
import java.util.List;

import lh.api.showcase.client.JsonParser;

public class SeatMapsResponseJsonParser implements JsonParser<List<SeatMap>> {

	@Override
	public List<SeatMap> parse(String json) {

		List<SeatMap> ret = new ArrayList<SeatMap> () ;
		ret.add(new SeatMap ()) ;
		
		return new ArrayList<SeatMap> (ret) ;
	}
}
