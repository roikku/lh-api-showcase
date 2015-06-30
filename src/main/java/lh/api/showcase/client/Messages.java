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

import com.google.gwt.core.client.GWT;

public interface Messages extends com.google.gwt.i18n.client.Messages {

	public static enum Util {
		INSTANCE;

		private Messages instance;

		Util() {
			instance = (Messages) GWT.create(Messages.class);
		}

		public Messages get() {
			return instance;
		}
	}
	
	@DefaultMessage("Home")
	String home();
	
	@DefaultMessage("About")
	String about();
	
	@DefaultMessage("Reference Data")
	String referenceData();
	
	@DefaultMessage("Operations")
	String operations();
	
	@DefaultMessage("Offers")
	String offers();
	
	@DefaultMessage("Seat Maps")
	String seatMaps();
	
	@DefaultMessage("Go")
	String go();	
	
	@DefaultMessage("Countries")
	String countries();	
	
	@DefaultMessage("Cities")
	String cities();	
	
	@DefaultMessage("Airports")
	String airports();	
	
	@DefaultMessage("Nearest Airports")
	String nearestAirports();	
	
	@DefaultMessage("Airlines")
	String airlines();	
	
	@DefaultMessage("Aircraft")
	String aircraft();	
	
	@DefaultMessage("Schedule")
	String schedule();
	
	@DefaultMessage("Flight Status")
	String flightStatus();
	
	@DefaultMessage("Flight Status By Route")
	String flightStatusByRoute();
	
	@DefaultMessage("Arrivals Status")
	String arrivalsStatus();
	
	@DefaultMessage("Departures Status")
	String departureStatus();

	@DefaultMessage("Flight Number")
	String flightNumber();
	
	@DefaultMessage("Airport Code Origin")
	String airportCodeOrigin();
	
	@DefaultMessage("Airport Code Destination")
	String airportCodeDestination();
	
	@DefaultMessage("Departure Date")
	String departureDate();
	
	@DefaultMessage("Cabin Class")
	String cabinClass();

	@DefaultMessage("Results")
	String results();
	
	@DefaultMessage("Raw Results")
	String rawResults();

	@DefaultMessage("Some parameters are not valid")
	String invalidParametersError();
	
	@DefaultMessage("Name")
	String name () ;
	
	@DefaultMessage("Code")
	String code () ;
	
	@DefaultMessage("Zone")
	String zone () ;
	
	@DefaultMessage("Country Code (optional)")
	String countryCodeOptional () ;
	
	@DefaultMessage("Lang (optional)")
	String langOptional () ;
	
	@DefaultMessage("Aircraft Code (optional)")
	String aircraftCodeOptional () ;
	
	@DefaultMessage("Airline Code (optional)")
	String airlineCodeOptional () ;
	
	@DefaultMessage("Airport Code (optional)")
	String airportCodeOptional () ;
	
	@DefaultMessage("City Code (optional)")
	String cityCodeOptional () ;
	
	@DefaultMessage("Latitude")
	String latitude () ;
	
	@DefaultMessage("Longiture")
	String longiture () ;
	
	@DefaultMessage("Direct Flight?")
	String isDirectFlight () ;
	
	@DefaultMessage("Time Range")
	String timeRange () ;
	
	@DefaultMessage("Date")
	String date () ;
	
	@DefaultMessage("Airport Code Departure")
	String airportCodeDeparture();
	
	@DefaultMessage("Airport Code Arrival")
	String airportCodeArrival();
	
	@DefaultMessage("Airport Code")
	String airportCode();
	
	@DefaultMessage("City Code")
	String cityCode();
	
	@DefaultMessage("Country Code")
	String countryCode();
	
	@DefaultMessage("Location Type")
	String locationType();
	
	@DefaultMessage("Coordinate")
	String coordinate();
	
	@DefaultMessage("Distance")
	String distance();	
	
	@DefaultMessage("Type")
	String type();	
	
	@DefaultMessage("Unknown")
	String unknown();	
	
	@DefaultMessage("ID")
	String id();
	
	@DefaultMessage("ID ICAO")
	String idIcao();
	
	@DefaultMessage("Other IDs")
	String otherIds();
	
	@DefaultMessage("Aircraft Code")
	String aircraftCode();
	
	@DefaultMessage("Airline Equip Code")
	String airlineEquipCode();
	
	@DefaultMessage("Duration")
	String duration();
	
	@DefaultMessage("Flight")
	String flight();
	
	@DefaultMessage("Back")
	String back();
	
	@DefaultMessage("Departure")
	String departure();
	
	@DefaultMessage("Arrival")
	String arrival();
	
	@DefaultMessage("Status")
	String status();
	
	@DefaultMessage("Some parameters seem not to be valid.")
	String badRequest();
	
	@DefaultMessage("The server is experiencing some problems. Try again later.")
	String serverProblem();

	@DefaultMessage("No information currently available.")
	String resourceNotFound();
	
}
