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

package lh.api.showcase.client.operations.status;

import java.util.logging.Logger;

import lh.api.showcase.client.BasicAbstractFormResultPresenter;
import lh.api.showcase.client.BasicFormResultDisplay;
import lh.api.showcase.client.Messages;
import lh.api.showcase.client.UiUtils;
import lh.api.showcase.client.operations.service.OperationsServiceAsync;
import lh.api.showcase.shared.AirportCode;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;

public abstract class AbstractStatusPresenter extends BasicAbstractFormResultPresenter {

	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger("StatusPresenter");

	public interface Display  extends BasicFormResultDisplay {
		AirportCode getAirport () ;
		String getStartDate () ;
		String getEndDate () ;
	}
	
	protected final Display display ;
	protected final OperationsServiceAsync operationsService ;
	
	public AbstractStatusPresenter (OperationsServiceAsync operationsService, HandlerManager eventBus, Display view) {
		super (eventBus, view) ;

		this.display = view ;
		this.operationsService = operationsService ;
	}
	
	
	public void bind() {
		if (display.getGoHandler() != null) {
			display.getGoHandler().addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
	
					display.clearMessage();
					
					// check parameters
					if (display.getAirport() == null 
							|| display.getStartDate() == null 
							|| display.getEndDate() == null) {
						display.setMessage(Messages.Util.INSTANCE.get().invalidParametersError(), UiUtils.MessageType.ERROR);
						return ;
					}
					
					fetchStatusData (display.getAirport(), display.getStartDate(), display.getEndDate()) ;
				}
			});
		}
	}
	
	@Override
	public void go(HasWidgets container) {
	    bind();
	    container.add(display.asWidget());
	}
	
	protected abstract void fetchStatusData (AirportCode airport, String startDate, String endDate) ;

}
