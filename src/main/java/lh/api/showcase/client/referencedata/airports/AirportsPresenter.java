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

package lh.api.showcase.client.referencedata.airports;

import java.util.logging.Level;
import java.util.logging.Logger;

import lh.api.showcase.client.BasicAbstractFormResultPresenter;
import lh.api.showcase.client.BasicFormResultDisplay;
import lh.api.showcase.client.HttpErrorUtils;
import lh.api.showcase.client.referencedata.service.ReferenceDataServiceAsync;
import lh.api.showcase.shared.AirportCode;
import lh.api.showcase.shared.LanguageCode;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

public class AirportsPresenter extends BasicAbstractFormResultPresenter {

	private Logger logger = Logger.getLogger("AirportsPresenter");

	public interface Display  extends BasicFormResultDisplay {
		LanguageCode getLang () ;
		AirportCode getAirportCode () ;
	}
	
	private final Display display ;
	private final ReferenceDataServiceAsync referenceDataService ;
	
	public AirportsPresenter (ReferenceDataServiceAsync referenceDataService, HandlerManager eventBus, Display view) {
		super (eventBus, view) ;

		this.display = view ;
		this.referenceDataService = referenceDataService ;
	}
	
	
	public void bind() {
		if (display.getGoHandler() != null) {
			display.getGoHandler().addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
	
					fetchAirportsData (display.getAirportCode(), display.getLang()) ;
				}
			});
		}
	}
	
	@Override
	public void go(HasWidgets container) {
	    bind();
	    container.add(display.asWidget());
	}
	
	private void fetchAirportsData (AirportCode airportCode, LanguageCode languageCode) {
		
		display.setBusy(true);
		referenceDataService.getAirports(airportCode, languageCode, new AsyncCallback<String>() {
			@Override
			public void onFailure(Throwable err) {
				logger.log(Level.SEVERE, err.getMessage());
				display.setBusy(false);
				HttpErrorUtils.handleHttpErrorException (err, display) ;
			}

			@Override
			public void onSuccess(final String result) {
				display.setBusy(false);
				display.setJson(result, new AirportsResponseJsonParser());
			}
		});
	}
}
