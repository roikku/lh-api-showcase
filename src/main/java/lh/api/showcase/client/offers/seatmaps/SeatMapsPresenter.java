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

import java.util.logging.Level;
import java.util.logging.Logger;

import lh.api.showcase.client.BasicAbstractFormResultPresenter;
import lh.api.showcase.client.BasicFormResultDisplay;
import lh.api.showcase.client.HttpErrorUtils;
import lh.api.showcase.client.UiUtils;
import lh.api.showcase.client.offers.service.OffersServiceAsync;
import lh.api.showcase.shared.AirportCode;
import lh.api.showcase.shared.CabinClass;
import lh.api.showcase.client.Messages;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

public class SeatMapsPresenter extends BasicAbstractFormResultPresenter {

	private Logger logger = Logger.getLogger("SeatMapsPresenter");

	public interface Display  extends BasicFormResultDisplay {
		String getFlightNumber () ;
		AirportCode getOrigine () ;
		AirportCode getDestination () ;
		String getDepartureDate () ;
		CabinClass getCabineClass () ;
	}
	
	private final Display display ;
	private final OffersServiceAsync offersService ;
	
	public SeatMapsPresenter (OffersServiceAsync offersService, HandlerManager eventBus, Display view) {
		super (eventBus, view) ;

		this.display = view ;
		this.offersService = offersService ;
	}
	
	
	public void bind() {
		if (display.getGoHandler() != null) {
			display.getGoHandler().addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
	
					display.clearMessage();
					
					// check parameters
					if (display.getFlightNumber().isEmpty() 
							|| display.getOrigine() == null 
							|| display.getDestination() == null
							|| display.getDepartureDate() == null) {
						display.setMessage(Messages.Util.INSTANCE.get().invalidParametersError(), UiUtils.MessageType.ERROR);
						return ;
					}
					
					fetchSeatMapsData (display.getFlightNumber().toUpperCase(), display.getOrigine(), display.getDestination(), display.getDepartureDate(), display.getCabineClass()) ;
				}
			});
		}
	}
	
	@Override
	public void go(HasWidgets container) {
	    bind();
	    container.add(display.asWidget());
	}
	
	private void fetchSeatMapsData (String flightNumber, AirportCode origin,
			AirportCode destination, String departureDate, CabinClass cabinClass) {
		
		display.setBusy(true);
		offersService.getSeatMaps(flightNumber, origin, destination, departureDate, cabinClass, new AsyncCallback<String>() {
			@Override
			public void onFailure(Throwable err) {
				logger.log(Level.SEVERE, err.getMessage());
				display.setBusy(false);
				HttpErrorUtils.handleHttpErrorException (err, display) ;
			}

			@Override
			public void onSuccess(final String result) {
				display.setBusy(false);
				display.setJson(result, new SeatMapsResponseJsonParser());
			}
		});
	}
}
