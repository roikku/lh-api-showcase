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

import java.util.logging.Logger;

import lh.api.showcase.client.about.AboutPresenter;
import lh.api.showcase.client.about.AboutView;
import lh.api.showcase.client.header.HeaderPresenter;
import lh.api.showcase.client.header.HeaderView;
import lh.api.showcase.client.home.HomePresenter;
import lh.api.showcase.client.home.HomeView;
import lh.api.showcase.client.menu.event.MenuEvent;
import lh.api.showcase.client.menu.event.MenuEventHandler;
import lh.api.showcase.client.offers.OffersPresenter;
import lh.api.showcase.client.offers.OffersView;
import lh.api.showcase.client.offers.seatmaps.SeatMapsPresenter;
import lh.api.showcase.client.offers.seatmaps.SeatMapsView;
import lh.api.showcase.client.offers.service.OffersServiceAsync;
import lh.api.showcase.client.operations.OperationsPresenter;
import lh.api.showcase.client.operations.OperationsView;
import lh.api.showcase.client.operations.flightstatus.FlightStatusPresenter;
import lh.api.showcase.client.operations.flightstatus.FlightStatusView;
import lh.api.showcase.client.operations.flightstatusbyroute.FlightStatusByRoutePresenter;
import lh.api.showcase.client.operations.flightstatusbyroute.FlightStatusByRouteView;
import lh.api.showcase.client.operations.schedules.SchedulesPresenter;
import lh.api.showcase.client.operations.schedules.SchedulesView;
import lh.api.showcase.client.operations.service.OperationsServiceAsync;
import lh.api.showcase.client.operations.status.arrivalsstatus.ArrivalsStatusPresenter;
import lh.api.showcase.client.operations.status.arrivalsstatus.ArrivalsStatusView;
import lh.api.showcase.client.operations.status.departuresstatus.DeparturesStatusPresenter;
import lh.api.showcase.client.operations.status.departuresstatus.DeparturesStatusView;
import lh.api.showcase.client.referencedata.ReferenceDataPresenter;
import lh.api.showcase.client.referencedata.ReferenceDataView;
import lh.api.showcase.client.referencedata.aircraft.AircraftPresenter;
import lh.api.showcase.client.referencedata.aircraft.AircraftView;
import lh.api.showcase.client.referencedata.airlines.AirlinesPresenter;
import lh.api.showcase.client.referencedata.airlines.AirlinesView;
import lh.api.showcase.client.referencedata.airports.AirportsPresenter;
import lh.api.showcase.client.referencedata.airports.AirportsView;
import lh.api.showcase.client.referencedata.cities.CitiesPresenter;
import lh.api.showcase.client.referencedata.cities.CitiesView;
import lh.api.showcase.client.referencedata.countries.CountriesPresenter;
import lh.api.showcase.client.referencedata.countries.CountriesView;
import lh.api.showcase.client.referencedata.nearestairports.NearestAirportsPresenter;
import lh.api.showcase.client.referencedata.nearestairports.NearestAirportsView;
import lh.api.showcase.client.referencedata.service.ReferenceDataServiceAsync;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

public class AppController implements Presenter, ValueChangeHandler<String> {

	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger("AppController");
	
	private final HandlerManager eventBus;
	private final ReferenceDataServiceAsync referenceDataService ;
	private final OffersServiceAsync offersService ;
	private final OperationsServiceAsync operationsService ;
	private HasWidgets container;

	private final String home = MenuEvent.MenuSelection.HOME.toString().toLowerCase() ;
	private final String about = "about" ;
	private final String referencedata = "referencedata" ;
	private final String countries = "countries" ;
	private final String cities = "cities" ;
	private final String airports = "airports" ;
	private final String nearestAirports = "nearestairports" ;
	private final String airlines = "airlines" ;
	private final String aircraft = "aircraft" ;
	private final String offers = "offers" ;
	private final String seatMaps = "seatmaps" ;
	private final String operations = "operations" ;
	private final String schedules = "schedules" ;
	private final String flightStatus = "flightstatus" ;
	private final String flightStatusByRoute = "_flightstatusbyroute" ;
	private final String arrivalsStatus = "arrivalsstatus" ;
	private final String departuresStatus = "departuresstatus" ;
	
	public AppController(ReferenceDataServiceAsync referenceDataService, OperationsServiceAsync operationsService, OffersServiceAsync offersService, HandlerManager eventBus) {
		super();
		this.referenceDataService = referenceDataService ;
		this.operationsService = operationsService ;
		this.offersService = offersService ;
		this.eventBus = eventBus;
		bind();
	}

	private void bind() {
		History.addValueChangeHandler(this);

		eventBus.addHandler(MenuEvent.TYPE, new MenuEventHandler() {
			@Override
			public void onMenuSelection(MenuEvent event) {
				if (event.getMenuSelection() == MenuEvent.MenuSelection.HOME) {

					History.newItem(home);
					
					HomePresenter presenter = buildHomePresenter () ;
					presenter.go(container);

				} else if (event.getMenuSelection() == MenuEvent.MenuSelection.REFERENCE_DATA) {

					History.newItem(referencedata);
					
					ReferenceDataPresenter presenter = buildReferenceDataPresenter () ;
					presenter.go(container);
					
				} else if (event.getMenuSelection() == MenuEvent.MenuSelection.REFERENCE_DATA_COUNTRIES) {

					History.newItem(countries);
					
					CountriesPresenter presenter = buildCountriesPresenter () ;
					presenter.go(container);
				} else if (event.getMenuSelection() == MenuEvent.MenuSelection.REFERENCE_DATA_CITIES) {

					History.newItem(cities);
					
					CitiesPresenter presenter = buildCitiesPresenter () ;
					presenter.go(container);
					
				} else if (event.getMenuSelection() == MenuEvent.MenuSelection.REFERENCE_DATA_AIRPORTS) {

					History.newItem(airports);

					AirportsPresenter presenter = buildAirportsPresenter () ;
					presenter.go(container);
					
				} else if (event.getMenuSelection() == MenuEvent.MenuSelection.REFERENCE_DATA_NEAREST_AIRPORTS) {

					History.newItem(nearestAirports);

					NearestAirportsPresenter presenter = buildNearestAirportsPresenter () ;
					presenter.go(container);
					
				} else if (event.getMenuSelection() == MenuEvent.MenuSelection.REFERENCE_DATA_AIRLINES) {

					History.newItem(airlines);

					AirlinesPresenter presenter = buildAirlinesPresenter () ;
					presenter.go(container);
					
				} else if (event.getMenuSelection() == MenuEvent.MenuSelection.REFERENCE_DATA_AIRCRAFT) {

					History.newItem(aircraft);

					AircraftPresenter presenter = buildAircraftPresenter () ;
					presenter.go(container);
					
				} else if (event.getMenuSelection() == MenuEvent.MenuSelection.OPERATIONS) {

					History.newItem(operations);

					OperationsPresenter presenter = buildOperationsPresenter () ;
					presenter.go(container);

				} else if (event.getMenuSelection() == MenuEvent.MenuSelection.OPERATIONS_SCHEDULES) {

					History.newItem(schedules);

					SchedulesPresenter presenter = buildSchedulesPresenter () ;
					presenter.go(container);

				} else if (event.getMenuSelection() == MenuEvent.MenuSelection.OPERATIONS_FLIGHT_STATUS) {

					History.newItem(flightStatus);

					FlightStatusPresenter presenter = buildFlightStatusPresenter () ;
					presenter.go(container);

				} else if (event.getMenuSelection() == MenuEvent.MenuSelection.OPERATIONS_FLIGHT_STATUS_BY_ROUTE) {

					History.newItem(flightStatusByRoute);

					FlightStatusByRoutePresenter presenter = buildFlightStatusByRoutePresenter () ;
					presenter.go(container);

				} else if (event.getMenuSelection() == MenuEvent.MenuSelection.OPERATIONS_ARRIVALS_STATUS) {

					History.newItem(arrivalsStatus);

					ArrivalsStatusPresenter presenter = buildArrivalsStatusPresenter () ;
					presenter.go(container);

				} else if (event.getMenuSelection() == MenuEvent.MenuSelection.OPERATIONS_DEPARTURES_STATUS) {

					History.newItem(departuresStatus);

					DeparturesStatusPresenter presenter = buildDeparturesStatusPresenter () ;
					presenter.go(container);

				} else if (event.getMenuSelection() == MenuEvent.MenuSelection.OFFERS) {

					History.newItem(offers);

					OffersPresenter presenter = buildOffersPresenter () ;
					presenter.go(container);

				} else if (event.getMenuSelection() == MenuEvent.MenuSelection.OFFERS_SEAT_MAPS) {

					History.newItem(seatMaps);

					SeatMapsPresenter presenter = buildSeatMapsPresenter () ;
					presenter.go(container);

				} else if (event.getMenuSelection() == MenuEvent.MenuSelection.ABOUT) {

					History.newItem(about);
					
					AboutPresenter presenter = buildAboutPresenter () ;
					presenter.go(container);
				} else if (event.getMenuSelection() == MenuEvent.MenuSelection.BACK) {

					History.back();
				}
			}
		});
	}
	

	
	private AboutPresenter buildAboutPresenter () {
		container.clear(); 
		setHeader (container) ;
		return new AboutPresenter (new AboutView (), eventBus) ;
	}
	
	
	private ReferenceDataPresenter buildReferenceDataPresenter () {
		container.clear(); 
		setHeader (container) ;
		return new ReferenceDataPresenter (new ReferenceDataView (), eventBus) ;
	}
	
	
	private HomePresenter buildHomePresenter () {
		container.clear(); 
		setHeader (container) ;
		return new HomePresenter (new HomeView (), eventBus) ;
	}
	
	private CountriesPresenter buildCountriesPresenter () {
		container.clear(); 
		setHeader (container) ;
		CountriesPresenter.Display view = new CountriesView () ;
		return new CountriesPresenter (referenceDataService, eventBus, view) ;
	}
	
	private CitiesPresenter buildCitiesPresenter () {
		container.clear(); 
		setHeader (container) ;
		CitiesPresenter.Display view = new CitiesView () ;
		return new CitiesPresenter (referenceDataService, eventBus, view) ;
	}
	
	private AirportsPresenter buildAirportsPresenter () {
		container.clear(); 
		setHeader (container) ;
		AirportsPresenter.Display view = new AirportsView () ;
		return new AirportsPresenter (referenceDataService, eventBus, view) ;
	}
	
	private NearestAirportsPresenter buildNearestAirportsPresenter () {
		container.clear(); 
		setHeader (container) ;
		NearestAirportsPresenter.Display view = new NearestAirportsView () ;
		return new NearestAirportsPresenter (referenceDataService, eventBus, view) ;
	}
	
	private AirlinesPresenter buildAirlinesPresenter () {
		container.clear(); 
		setHeader (container) ;
		AirlinesPresenter.Display view = new AirlinesView () ;
		return new AirlinesPresenter (referenceDataService, eventBus, view) ;
	}
	
	private AircraftPresenter buildAircraftPresenter () {
		container.clear(); 
		setHeader (container) ;
		AircraftPresenter.Display view = new AircraftView () ;
		return new AircraftPresenter (referenceDataService, eventBus, view) ;
	}
	
	private OperationsPresenter buildOperationsPresenter () {
		container.clear(); 
		setHeader (container) ;
		OperationsPresenter.Display view = new OperationsView () ;
		return new OperationsPresenter (view, eventBus) ;
	}
	
	private OffersPresenter buildOffersPresenter () {
		container.clear(); 
		setHeader (container) ;
		OffersPresenter.Display view = new OffersView () ;
		return new OffersPresenter (view, eventBus) ;
	}

	private SeatMapsPresenter buildSeatMapsPresenter () {
		container.clear(); 
		setHeader (container) ;
		SeatMapsPresenter.Display view = new SeatMapsView () ;
		return new SeatMapsPresenter (offersService, eventBus, view) ;
	}
	
	private SchedulesPresenter buildSchedulesPresenter () {
		container.clear(); 
		setHeader (container) ;
		SchedulesPresenter.Display view = new SchedulesView () ;
		return new SchedulesPresenter (operationsService, eventBus, view) ;
	}
	
	private FlightStatusPresenter buildFlightStatusPresenter () {
		container.clear(); 
		setHeader (container) ;
		FlightStatusPresenter.Display view = new FlightStatusView () ;
		return new FlightStatusPresenter (operationsService, eventBus, view) ;
	}
	
	private FlightStatusByRoutePresenter buildFlightStatusByRoutePresenter () {
		container.clear(); 
		setHeader (container) ;
		FlightStatusByRoutePresenter.Display view = new FlightStatusByRouteView () ;
		return new FlightStatusByRoutePresenter (operationsService, eventBus, view) ;
	}
	
	private ArrivalsStatusPresenter buildArrivalsStatusPresenter () {
		container.clear(); 
		setHeader (container) ;
		ArrivalsStatusPresenter.Display view = new ArrivalsStatusView () ;
		return new ArrivalsStatusPresenter (operationsService, eventBus, view) ;
	}
	
	private DeparturesStatusPresenter buildDeparturesStatusPresenter () {
		container.clear(); 
		setHeader (container) ;
		DeparturesStatusPresenter.Display view = new DeparturesStatusView () ;
		return new DeparturesStatusPresenter (operationsService, eventBus, view) ;
	}
	
	private void setHeader (HasWidgets container) {
		
	    HeaderPresenter hp = new HeaderPresenter (eventBus, new HeaderView ()) ;
	    hp.go(container);
	}
	
	
	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		String token = event.getValue();
		if (token != null) {
			Presenter presenter = null;
			if (token.startsWith(home)) {
				presenter = buildHomePresenter () ;
			} else if (token.startsWith(about)) {
				presenter = buildAboutPresenter () ;
			} else if (token.startsWith(referencedata)) {
				presenter = buildReferenceDataPresenter () ;
			} else if (token.startsWith(operations)) {
				presenter = buildOperationsPresenter () ;
			} else if (token.startsWith(offers)) {
				presenter = buildOffersPresenter () ;
			} else if (token.startsWith(countries)) {
				presenter = buildCountriesPresenter () ;
			} else if (token.startsWith(cities)) {
				presenter = buildCitiesPresenter () ;
			} else if (token.startsWith(airports)) {
				presenter = buildAirportsPresenter () ;
			} else if (token.startsWith(nearestAirports)) {
				presenter = buildNearestAirportsPresenter () ;
			} else if (token.startsWith(airlines)) {
				presenter = buildAirlinesPresenter () ;
			} else if (token.startsWith(aircraft)) {
				presenter = buildAircraftPresenter () ;
			} else if (token.startsWith(seatMaps)) {
				presenter = buildSeatMapsPresenter () ;
			} else if (token.startsWith(schedules)) {
				presenter = buildSchedulesPresenter () ;
			} else if (token.startsWith(flightStatus)) {
				presenter = buildFlightStatusPresenter () ;
			} else if (token.startsWith(flightStatusByRoute)) {
				presenter = buildFlightStatusByRoutePresenter () ;
			} else if (token.startsWith(arrivalsStatus)) {
				presenter = buildArrivalsStatusPresenter () ;
			} else if (token.startsWith(departuresStatus)) {
				presenter = buildDeparturesStatusPresenter () ;
			}  
			
			if (presenter != null) {
				presenter.go(container);
			}
		}
	}

	
	@Override
	public void go(HasWidgets container) {
		this.container = container;
		if ("".equals(History.getToken())) {
			History.newItem(home) ;
		} else {
			History.fireCurrentHistoryState();
		}
	}
}
