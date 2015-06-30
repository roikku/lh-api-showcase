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

package lh.api.showcase.client.operations.schedules;

import java.util.Date;
import java.util.List;

import lh.api.showcase.client.BasicAbstractFormResultView;
import lh.api.showcase.client.Messages;
import lh.api.showcase.client.UiUtils;
import lh.api.showcase.client.operations.Flight;
import lh.api.showcase.client.operations.FlightUtils;
import lh.api.showcase.shared.AirportCode;
import lh.api.showcase.shared.TimeStatusCode;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.vaadin.client.widgets.Grid;
import com.vaadin.shared.ui.grid.HeightMode;

public class SchedulesView extends BasicAbstractFormResultView<Schedule> implements SchedulesPresenter.Display {
	
	static class SchedulesGrid extends Grid<Schedule> {
		public SchedulesGrid() {
			super();
			setSelectionMode(SelectionMode.SINGLE);
			this.setWidth("850px");
			this.setHeightMode(HeightMode.ROW);
			this.setHeightByRows(8);
			
			addColumn(new Column<String, Schedule>(Messages.Util.INSTANCE.get().flight()) {
				@Override
				public String getValue(Schedule row) {
					StringBuilder sb = new StringBuilder () ;
					List<Flight> lf = row.getFligths() ;
					if (lf.isEmpty()) {
						return "" ;
					}
					Flight f = lf.get(0) ;
					
					sb.append(f.getAirlineID()) ;
					sb.append(f.getFlightNumber()) ;
					
					sb.append(" - from ") ;
					sb.append(f.getDeparture().getAirportCode()) ;
					sb.append(" to ") ;
					sb.append(f.getArrival().getAirportCode()) ;
					return sb.toString();
				}
			}).setWidth(210);
			
			addColumn(new Column<String, Schedule>(Messages.Util.INSTANCE.get().departure()) {
				@Override
				public String getValue(Schedule row) {
					StringBuilder sb = new StringBuilder () ;
					List<Flight> lf = row.getFligths() ;
					if (lf.isEmpty()) {
						return "" ;
					}
					Flight f = lf.get(0) ;
					try {
						TimeStatusCode tsc = TimeStatusCode.valueOf(f.getDeparture().getTimeStatus().getCode()) ;
						sb.append(tsc.toString()) ;
						sb.append(" - ") ;
						sb.append(tsc.getDescription()) ;
					} catch (IllegalArgumentException | NullPointerException e) {}
					
					sb.append(" - ") ;
					sb.append(FlightUtils.getDepartureLocalDate(f)) ;
					return sb.toString();
				}
			}).setWidth(300);
			addColumn(new Column<String, Schedule>(Messages.Util.INSTANCE.get().arrival()) {
				@Override
				public String getValue(Schedule row) {
					StringBuilder sb = new StringBuilder () ;
					List<Flight> lf = row.getFligths() ;
					if (lf.isEmpty()) {
						return "" ;
					}
					Flight f = lf.get(0) ;
					sb.append(FlightUtils.getArrivalLocalDate(f)) ;
					return sb.toString();
				}
			}).setWidth(200);
			
			addColumn(new Column<String, Schedule>(Messages.Util.INSTANCE.get().duration()) {
				@Override
				public String getValue(Schedule row) {
					return row.getDuration();
				}
			}).setWidth(120);
		}
	}
	
	private UiUtils.ConstraintSuggestBox airportCodesOrigin ;
	private UiUtils.ConstraintSuggestBox airportCodesDestination ;
	private DateBox departureDate ;
	private CheckBox checkBoxDirectFlight = new CheckBox(Messages.Util.INSTANCE.get().isDirectFlight());

	private final DateTimeFormat fmt = DateTimeFormat.getFormat("yyyy-MM-dd");

	public SchedulesView () {
		super (Messages.Util.INSTANCE.get().schedule(), new SchedulesGrid ()) ;
		
		airportCodesOrigin = new UiUtils.ConstraintSuggestBox(AirportCode.toStringSet());
		airportCodesDestination = new UiUtils.ConstraintSuggestBox(AirportCode.toStringSet());
	
	    departureDate = new DateBox();
	    departureDate.setFormat(new DateBox.DefaultFormat(fmt));
	    departureDate.getDatePicker().setYearArrowsVisible(true);
	    departureDate.setValue(new Date());
	    
		init () ;
	}
	
	protected Widget getForm () {
		VerticalPanel panelV = new VerticalPanel () ;
		panelV.setWidth("100%");
		((HasHorizontalAlignment) panelV).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		
		Panel panel = new FlowPanel() ;
		panel.setStyleName("flowPanelMainForm");
		
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
		
		Panel isDirectPanel = new FlowPanel();
		isDirectPanel.setStyleName("flowPanelVerRowForm");
		isDirectPanel.add(UiUtils.setWidth(checkBoxDirectFlight, 100.0, Unit.PCT));
		
		panel.add(airportCodesOriginPanel) ;
		panel.add(airportCodesDestPanel) ;
		panel.add(departureDatePanel) ;
		panel.add(isDirectPanel) ;
		
		Panel goPanel = new FlowPanel();
		goPanel.setStyleName("flowPanelVerRowForm");
		goPanel.add (UiUtils.setWidth(goButton, 100.0, Unit.PCT)) ;
		
		panel.add(goPanel) ;
		
		panelV.add(panel);
		
		return panelV ;
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
	public Boolean isDirectFlight() {
		return checkBoxDirectFlight.getValue();
	}

}
