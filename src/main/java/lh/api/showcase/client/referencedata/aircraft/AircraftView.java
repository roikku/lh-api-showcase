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

package lh.api.showcase.client.referencedata.aircraft;

import lh.api.showcase.client.BasicAbstractFormResultView;
import lh.api.showcase.client.Messages;
import lh.api.showcase.client.UiUtils;
import lh.api.showcase.shared.AircraftCode;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.widgets.Grid;
import com.vaadin.shared.ui.grid.HeightMode;

public class AircraftView extends BasicAbstractFormResultView<Aircraft> implements AircraftPresenter.Display {
	
	static class AircraftGrid extends Grid<Aircraft> {
		public AircraftGrid() {
			super();
			setSelectionMode(SelectionMode.SINGLE);
			this.setWidth("550px");
			this.setHeightMode(HeightMode.ROW);
			this.setHeightByRows(8);
			
			addColumn(new Column<String, Aircraft>(Messages.Util.INSTANCE.get().aircraftCode()) {
				@Override
				public String getValue(Aircraft row) {
					return row.getAircraftCode();
				}
			}).setWidth(110);
			addColumn(new Column<String, Aircraft>(Messages.Util.INSTANCE.get().airlineEquipCode()) {
				@Override
				public String getValue(Aircraft row) {
					return row.getAirlineEquipCode();
				}
			}).setWidth(150);
			addColumn(UiUtils.<Aircraft>getMultiLingualNameColumn(250));
		}
	}

	private SuggestBox aircraftCodes ;
	
	public AircraftView () {
		super (Messages.Util.INSTANCE.get().aircraft(), new AircraftGrid ()) ;
		
		final MultiWordSuggestOracle aircraftCodesList = new MultiWordSuggestOracle();
		for (AircraftCode cc : AircraftCode.values ()){
			aircraftCodesList.add(cc.toString());
		}
		aircraftCodes = new SuggestBox(aircraftCodesList);

		init () ;
	}
	
	protected Widget getForm () {
		VerticalPanel panelV = new VerticalPanel () ;
		panelV.setWidth("100%");
		((HasHorizontalAlignment) panelV).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		 
		Panel panel = new FlowPanel() ;
		panel.setStyleName("flowPanelMainForm");
		
		Panel airlineCodesPanel = new FlowPanel();
		airlineCodesPanel.setStyleName("flowPanelVerRowForm");
		airlineCodesPanel.add(new Label (Messages.Util.INSTANCE.get().aircraftCodeOptional()));
		airlineCodesPanel.add(UiUtils.setWidth(aircraftCodes, 100.0, Unit.PCT));
		
		panel.add(airlineCodesPanel);

		Panel goPanel = new FlowPanel();
		goPanel.setStyleName("flowPanelVerRowForm");
		goPanel.add (UiUtils.setWidth(goButton, 100.0, Unit.PCT)) ;
		
		panel.add(goPanel) ;
		
		panelV.add(panel);
		
		return panel ;
	}
	

	@Override
	public AircraftCode getAircraftCode() {
		try {
			if (aircraftCodes.getValue().isEmpty()) {
				return null ;
			} else {
				return AircraftCode.enumValueOf(aircraftCodes.getValue().toUpperCase());
			}
		} catch (IllegalArgumentException | NullPointerException e) {
			// invalid input...
			// ...
			return null ;
		}
	}
}
