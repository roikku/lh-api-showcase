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

package lh.api.showcase.client.offers;

import java.util.logging.Logger;

import lh.api.showcase.client.BasicAbstractPresenter;
import lh.api.showcase.client.BasicDisplay;
import lh.api.showcase.client.menu.event.MenuEvent;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;

public class OffersPresenter extends BasicAbstractPresenter {
	
	public interface Display extends BasicDisplay {
		HasClickHandlers getSeatMapsHandler();
	}
	
	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger("OffersPresenter");

	private final Display display ;

	public OffersPresenter(Display display, HandlerManager eventBus) {
		super(eventBus, display);
		this.display = display;
	}


	@Override
	public void go(HasWidgets container) {
		bind();
		container.add(display.asWidget());
	}
	
	public void bind() {
		if (display.getSeatMapsHandler() != null) {
			display.getSeatMapsHandler().addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
	
					eventBus.fireEvent(new MenuEvent (MenuEvent.MenuSelection.OFFERS_SEAT_MAPS)) ;
				}
			});
		}
	}
}
