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

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;

public class FlightUtils {

	private FlightUtils () { super () ; throw new IllegalStateException () ; } ;
	
	private static DateTimeFormat dateRowFormat = DateTimeFormat.getFormat("yyyy-MM-ddTHH:mm") ;
	private static DateTimeFormat dateFormat = DateTimeFormat.getFormat("yyyy MMM d 'at' HH:mm") ;
	
	public static String getDepartureLocalDate (Flight flight) {
		if (flight == null) {
			return "" ;
		}
		String dateStr = null ;
		if (flight.getDeparture().getEstimatedOrActualTimeLocal() == null 
				|| flight.getDeparture().getEstimatedOrActualTimeLocal().isEmpty()) {

			dateStr = flight.getDeparture().getScheduledTimeLocal() ;
		} else {
			dateStr = flight.getDeparture().getEstimatedOrActualTimeLocal() ;
		}
		return formatDate (dateStr) ;
	}
	
	public static String getArrivalLocalDate (Flight flight) {
		if (flight == null) {
			return "" ;
		}
		String dateStr = null ;
		if (flight.getArrival().getEstimatedOrActualTimeLocal() == null 
				|| flight.getArrival().getEstimatedOrActualTimeLocal().isEmpty()) {
			dateStr = flight.getArrival().getScheduledTimeLocal() ;
		} else {
			dateStr = flight.getArrival().getEstimatedOrActualTimeLocal() ;
		}
		return formatDate (dateStr) ;
	}
	
	private static String formatDate (String dateStr) {
		Date date = null ;
		if (dateStr == null || dateStr.isEmpty()) {
			return "" ;
		}
		try {
			date = dateRowFormat.parse(dateStr) ;
		} catch (IllegalArgumentException e) {
			return dateStr ;
		}
		
		//return DateTimeFormat.getFormat(PredefinedFormat.DATE_TIME_MEDIUM).format(date).toString() ;
		return dateFormat.format(date).toString() ;
	}
}
