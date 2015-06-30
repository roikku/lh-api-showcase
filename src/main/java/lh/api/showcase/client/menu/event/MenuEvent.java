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

package lh.api.showcase.client.menu.event;

import com.google.gwt.event.shared.GwtEvent;

public class MenuEvent  extends GwtEvent<MenuEventHandler> {

	public static Type<MenuEventHandler> TYPE = new Type<MenuEventHandler>();
	
	static public enum MenuSelection {
		HOME,
		REFERENCE_DATA,
		REFERENCE_DATA_COUNTRIES,
		REFERENCE_DATA_CITIES,
		REFERENCE_DATA_AIRPORTS,
		REFERENCE_DATA_NEAREST_AIRPORTS,
		REFERENCE_DATA_AIRLINES,
		REFERENCE_DATA_AIRCRAFT,
		OFFERS,
		OFFERS_SEAT_MAPS,
		OPERATIONS,
		OPERATIONS_SCHEDULES,
		OPERATIONS_FLIGHT_STATUS,
		OPERATIONS_FLIGHT_STATUS_BY_ROUTE,
		OPERATIONS_ARRIVALS_STATUS,
		OPERATIONS_DEPARTURES_STATUS,
		ABOUT,
		BACK
	}
	
	private final MenuSelection menuSelection ;
	
	
	public MenuEvent(MenuSelection menuSelection) {
		super();
		this.menuSelection = menuSelection;
	}
	
	
	public MenuSelection getMenuSelection() {
		return menuSelection;
	}


	@Override
	protected void dispatch(MenuEventHandler handler) {
		handler.onMenuSelection(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<MenuEventHandler> getAssociatedType() {
		return TYPE;
	}
}
