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

package lh.api.showcase.client.operations.flightstatus;

import java.util.Date;

import lh.api.showcase.client.BasicAbstractFormResultView;
import lh.api.showcase.client.Messages;
import lh.api.showcase.client.UiUtils;
import lh.api.showcase.client.operations.Flight;
import lh.api.showcase.client.operations.FlightUtils;
import lh.api.showcase.shared.FlightStatusCode;
import lh.api.showcase.shared.TimeStatusCode;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.vaadin.client.widgets.Grid;
import com.vaadin.shared.ui.grid.HeightMode;

public class FlightStatusView extends BasicAbstractFormResultView<FlightStatus> implements FlightStatusPresenter.Display {
	
	static class FlightStatusGrid extends Grid<FlightStatus> {
		public FlightStatusGrid() {
			super();
			setSelectionMode(SelectionMode.SINGLE);

			this.setWidth("940px");
			this.setHeightMode(HeightMode.ROW);
			this.setHeightByRows(2);
			
			addColumn(new Column<String, FlightStatus>(Messages.Util.INSTANCE.get().flight()) {
				@Override
				public String getValue(FlightStatus row) {
					StringBuilder sb = new StringBuilder () ;
					Flight f = row.getFligth() ;
					
					sb.append(f.getAirlineID()) ;
					sb.append(f.getFlightNumber()) ;
					
					sb.append(" - from ") ;
					sb.append(f.getDeparture().getAirportCode()) ;
					sb.append(" to ") ;
					sb.append(f.getArrival().getAirportCode()) ;
					return sb.toString();
				}
			}).setWidth(210);
			addColumn(new Column<String, FlightStatus>(Messages.Util.INSTANCE.get().status()) {
				@Override
				public String getValue(FlightStatus row) {
					StringBuilder sb = new StringBuilder () ;
					Flight f = row.getFligth() ;
					try {
						FlightStatusCode fsc = FlightStatusCode.valueOf(f.getFlightStatus().getCode()) ;
						sb.append(fsc.toString()) ;
						sb.append(" - ") ;
						sb.append(fsc.getDescription()) ;
					} catch (IllegalArgumentException | NullPointerException e) {}			
					return sb.toString();
				}
			}).setWidth(220);
			addColumn(new Column<String, FlightStatus>(Messages.Util.INSTANCE.get().departure()) {
				@Override
				public String getValue(FlightStatus row) {
					StringBuilder sb = new StringBuilder () ;
					Flight f = row.getFligth() ;
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
			addColumn(new Column<String, FlightStatus>(Messages.Util.INSTANCE.get().arrival()) {
				@Override
				public String getValue(FlightStatus row) {
					StringBuilder sb = new StringBuilder () ;
					Flight f = row.getFligth() ;
					sb.append(FlightUtils.getArrivalLocalDate(f)) ;
					return sb.toString();
				}
			}).setWidth(200);
		}
	}

	private TextBox flightNumber ;
	private DateBox departureDate ;
	
	private final DateTimeFormat fmt = DateTimeFormat.getFormat("yyyy-MM-dd");

	public FlightStatusView () {
		super (Messages.Util.INSTANCE.get().flightStatus(), new FlightStatusGrid ()) ;
		
	    departureDate = new DateBox();
	    departureDate.setFormat(new DateBox.DefaultFormat(fmt));
	    departureDate.getDatePicker().setYearArrowsVisible(true);
	    departureDate.setValue(new Date());
	    
	    flightNumber = new TextBox () ;

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
		
		Panel departureDatePanel = new FlowPanel();
		departureDatePanel.setStyleName("flowPanelVerRowForm");
		departureDatePanel.add(UiUtils.setLeftHorizontalAlignment(new Label (Messages.Util.INSTANCE.get().departureDate())));
		departureDatePanel.add(UiUtils.setWidth(departureDate, 100.0, Unit.PCT));

		panel.add(flightNumberPanel) ;
		panel.add(departureDatePanel) ;

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
	public String getDepartureDate() {
		Date date = departureDate.getValue() ;
		return (date == null)?(null):(fmt.format(date)) ;
	}
}
