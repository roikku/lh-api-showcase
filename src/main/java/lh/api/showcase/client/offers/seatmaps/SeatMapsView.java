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

import java.util.Date;

import lh.api.showcase.client.BasicAbstractFormResultView;
import lh.api.showcase.client.JsonParser;
import lh.api.showcase.client.Messages;
import lh.api.showcase.client.UiUtils;
import lh.api.showcase.shared.AirportCode;
import lh.api.showcase.shared.CabinClass;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.vaadin.client.widgets.Grid;
import com.vaadin.shared.ui.grid.HeightMode;

public class SeatMapsView extends BasicAbstractFormResultView<SeatMap> implements SeatMapsPresenter.Display {
	
	static class SeatMapsGrid extends Grid<SeatMap> {
		public SeatMapsGrid() {
			super();
			setSelectionMode(SelectionMode.SINGLE);
			this.setWidth("650px");
			this.setHeightMode(HeightMode.ROW);
			this.setHeightByRows(2);
			
			addColumn(new Column<String, SeatMap>("Info") {
				@Override
				public String getValue(SeatMap row) {
					return "JSON parsing not implemented yet...";
				}
			});
		}
	}

	private TextBox flightNumber ;
	private UiUtils.ConstraintSuggestBox airportCodesOrigin ;
	private UiUtils.ConstraintSuggestBox airportCodesDestination ;
	private DateBox departureDate ;
	private ListBox cabinClass ;
	
	private final DateTimeFormat fmt = DateTimeFormat.getFormat("yyyy-MM-dd");

	public SeatMapsView () {
		super (Messages.Util.INSTANCE.get().seatMaps(), new SeatMapsGrid ()) ;
		
		airportCodesOrigin = new UiUtils.ConstraintSuggestBox(AirportCode.toStringSet ());
		airportCodesDestination = new UiUtils.ConstraintSuggestBox(AirportCode.toStringSet ());
	
	    departureDate = new DateBox();
	    departureDate.setFormat(new DateBox.DefaultFormat(fmt));
	    departureDate.getDatePicker().setYearArrowsVisible(true);
	    departureDate.setValue(new Date());
	    
	    flightNumber = new TextBox () ;
	    
	    cabinClass = new ListBox() ;
		for (CabinClass cc : CabinClass.values ()){
			cabinClass.addItem(cc.getDescription());
		}
		cabinClass.setSelectedIndex(CabinClass.values().length-1);
	    
		init () ;
	}
	
	protected Widget getForm () {
		VerticalPanel panelV = new VerticalPanel () ;
		panelV.setWidth("100%");
		((HasHorizontalAlignment) panelV).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		
		Panel panel = new FlowPanel() ;
		panel.setStyleName("flowPanelMainForm");
		
		Panel flightNumberPanel = new FlowPanel();
		flightNumberPanel.setStyleName("flowPanelVerRowForm");
		flightNumberPanel.add(UiUtils.setLeftHorizontalAlignment(new Label (Messages.Util.INSTANCE.get().flightNumber())));
		flightNumberPanel.add(UiUtils.setWidth(flightNumber, 100.0, Unit.PCT));
		
		Panel airportCodesOriginPanel = new FlowPanel();
		airportCodesOriginPanel.setStyleName("flowPanelVerRowForm");
		airportCodesOriginPanel.add(UiUtils.setLeftHorizontalAlignment(new Label (Messages.Util.INSTANCE.get().airportCodeOrigin())));
		airportCodesOriginPanel.add(UiUtils.setWidth(airportCodesOrigin, 100.0, Unit.PCT));
		
		Panel airportCodesDestPanel = new FlowPanel();
		airportCodesDestPanel.setStyleName("flowPanelVerRowForm");
		airportCodesDestPanel.add(UiUtils.setLeftHorizontalAlignment(new Label (Messages.Util.INSTANCE.get().airportCodeDestination())));
		airportCodesDestPanel.add(UiUtils.setWidth(airportCodesDestination, 100.0, Unit.PCT));
	
		Panel departureDatePanel = new FlowPanel();
		departureDatePanel.setStyleName("flowPanelVerRowForm");
		departureDatePanel.add(UiUtils.setLeftHorizontalAlignment(new Label (Messages.Util.INSTANCE.get().departureDate())));
		departureDatePanel.add(UiUtils.setWidth(departureDate, 100.0, Unit.PCT));
		
		Panel cabinClassPanel = new FlowPanel();
		cabinClassPanel.setStyleName("flowPanelVerRowForm");
		cabinClassPanel.add(UiUtils.setLeftHorizontalAlignment(new Label (Messages.Util.INSTANCE.get().cabinClass())));
		cabinClassPanel.add(UiUtils.setWidth(cabinClass, 100.0, Unit.PCT));
		
		panel.add(flightNumberPanel) ;
		panel.add(airportCodesOriginPanel) ;
		panel.add(airportCodesDestPanel) ;
		panel.add(departureDatePanel) ;
		panel.add(cabinClassPanel) ;
		
		Panel goPanel = new FlowPanel();
		goPanel.setStyleName("flowPanelVerRowForm");
		goPanel.add (UiUtils.setWidth(goButton, 100.0, Unit.PCT)) ;
		
		panel.add(goPanel) ;
		
		panelV.add(panel);
		
		return panelV ;
	}

	@Override
	public String getFlightNumber() {
		return flightNumber.getValue();
	}

	@Override
	public AirportCode getOrigine() {
		try {
			if (airportCodesOrigin.getValue().isEmpty()) {
				return null ;
			} else {
				return AirportCode.valueOf(airportCodesOrigin.getValue().toUpperCase());
			}
		} catch (IllegalArgumentException | NullPointerException e) {
			// invalid input...
			// ...
			return null ;
		}
	}

	@Override
	public AirportCode getDestination() {
		try {
			if (airportCodesDestination.getValue().isEmpty()) {
				return null ;
			} else {
				return AirportCode.valueOf(airportCodesDestination.getValue().toUpperCase());
			}
		} catch (IllegalArgumentException | NullPointerException e) {
			// invalid input...
			// ...
			return null ;
		}
	}

	@Override
	public String getDepartureDate() {
		Date date = departureDate.getValue() ;
		return (date == null)?(null):(fmt.format(date)) ;
	}

	@Override
	public CabinClass getCabineClass() {
		return CabinClass.values()[cabinClass.getSelectedIndex()];
	}
	
	@Override
	public void setJson(String json, JsonParser<?> parser) {
		textArea.setText(json);
		Widget parent = textArea.getParent() ;
		while (parent != null && !(parent instanceof DisclosurePanel)) {
			parent = parent.getParent() ;
		}
		if (parent != null) {
			((DisclosurePanel)parent).setOpen(true);
		}
		super.setJson(json, parser);
	}
}