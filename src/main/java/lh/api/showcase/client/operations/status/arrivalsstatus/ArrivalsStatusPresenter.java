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

package lh.api.showcase.client.operations.status.arrivalsstatus;

import java.util.logging.Level;
import java.util.logging.Logger;

import lh.api.showcase.client.HttpErrorUtils;
import lh.api.showcase.client.operations.service.OperationsServiceAsync;
import lh.api.showcase.client.operations.status.AbstractStatusPresenter;
import lh.api.showcase.shared.AirportCode;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class ArrivalsStatusPresenter extends AbstractStatusPresenter {

	private Logger logger = Logger.getLogger("ArrivalsStatusPresenter");

	
	public ArrivalsStatusPresenter (OperationsServiceAsync operationsService, HandlerManager eventBus, AbstractStatusPresenter.Display view) {
		super (operationsService, eventBus, view) ;
	}
	

	@Override
	protected void fetchStatusData(AirportCode airport, String startDate, String endDate) {
		
		display.setBusy(true);
		operationsService.getArrivalsStatus(airport, startDate, endDate, new AsyncCallback<String>() {
			@Override
			public void onFailure(Throwable err) {
				logger.log(Level.SEVERE, err.getMessage());
				display.setBusy(false);
				HttpErrorUtils.handleHttpErrorException (err, display) ;
			}

			@Override
			public void onSuccess(final String result) {
				display.setBusy(false);
				display.setJson(result, new ArrivalsStatusResponseJsonParser());
			}
		});
	}
}
