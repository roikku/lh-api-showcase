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

import java.util.Date;

import lh.api.showcase.client.BasicAbstractFormResultView;
import lh.api.showcase.client.Messages;
import lh.api.showcase.client.UiUtils;
import lh.api.showcase.client.operations.Flight;
import lh.api.showcase.client.operations.flightstatus.FlightStatus;
import lh.api.showcase.shared.AirportCode;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.vaadin.client.widgets.Grid;

public class AbstractStatusView<T extends Status> extends BasicAbstractFormResultView<T> implements AbstractStatusPresenter.Display {
	
	static protected class StatusGrid<S extends FlightStatus> extends Grid<S> {
		public StatusGrid() {
			super();
			setSelectionMode(SelectionMode.SINGLE);

			addColumn(new Column<String, S>(Messages.Util.INSTANCE.get().flight()) {
				@Override
				public String getValue(S row) {
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
		}
	}

	private UiUtils.ConstraintSuggestBox airportCodes ;
	private DateBox arrivalDate ;
	private ListBox from ;
	private ListBox to ;
	
	private final DateTimeFormat fmt = DateTimeFormat.getFormat("yyyy-MM-dd");

	public AbstractStatusView (String title, Grid<T> grid) {
		super (title, grid) ;
		
		airportCodes = new UiUtils.ConstraintSuggestBox(AirportCode.toStringSet());

		arrivalDate = new DateBox();
		arrivalDate.setFormat(new DateBox.DefaultFormat(fmt));
		arrivalDate.getDatePicker().setYearArrowsVisible(true);
		arrivalDate.setValue(new Date());
		
		from = UiUtils.newTimeListBox () ;
		to = UiUtils.newTimeListBox () ;
		
		from.addChangeHandler(new ChangeHandler(){

			@Override
			public void onChange(ChangeEvent event) {
				String f = from.getSelectedItemText() ;
				String t = to.getSelectedItemText() ;
				if (t.compareTo(f) < 0) {
					to.setItemSelected(from.getSelectedIndex(), true);
				}
			}}) ;
		to.addChangeHandler(new ChangeHandler(){

			@Override
			public void onChange(ChangeEvent event) {
				String f = from.getSelectedItemText() ;
				String t = to.getSelectedItemText() ;
				if (t.compareTo(f) < 0) {
					from.setItemSelected(to.getSelectedIndex(), true);
				}
			}}) ;
	    
		init () ;
	}
	
	protected Widget getForm () {
		VerticalPanel panelV = new VerticalPanel () ;
		panelV.setWidth("100%");
		((HasHorizontalAlignment) panelV).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		
		Panel panel = new FlowPanel() ;
		panel.setStyleName("flowPanelMainForm");
		
		Panel airportCodesPanel = new FlowPanel();
		airportCodesPanel.setStyleName("flowPanelVerRowForm");
		airportCodesPanel.add(UiUtils.setLeftHorizontalAlignment(new Label (Messages.Util.INSTANCE.get().airportCode())));
		airportCodesPanel.add(UiUtils.setWidth(airportCodes, 100.0, Unit.PCT));
	
		Panel datePanel = new FlowPanel();
		datePanel.setStyleName("flowPanelVerRowForm");
		datePanel.add(UiUtils.setLeftHorizontalAlignment(new Label (Messages.Util.INSTANCE.get().date())));
		datePanel.add(UiUtils.setWidth(arrivalDate, 100.0, Unit.PCT));
		
		Panel timeSelection = new FlowPanel();
		timeSelection.setStyleName("flowPanelVerRowForm");
		timeSelection.add(UiUtils.setLeftHorizontalAlignment(new Label (Messages.Util.INSTANCE.get().timeRange())));
		Panel range = new FlowPanel () ;
		range.setStyleName("flowPanelHorSubRowForm");
		range.add(UiUtils.inlineBlock(UiUtils.setWidth(from, 45.0, Unit.PCT)));
		range.add(UiUtils.inlineBlock(UiUtils.setWidth(new Label("  -  "), 10.0, Unit.PCT)));
		range.add(UiUtils.inlineBlock(UiUtils.setWidth(to, 45.0, Unit.PCT)));
		timeSelection.add(UiUtils.setWidth(range, 100.0, Unit.PCT));
		
		panel.add(airportCodesPanel) ;
		panel.add(datePanel) ;
		panel.add(timeSelection) ;

		Panel goPanel = new FlowPanel();
		goPanel.setStyleName("flowPanelVerRowForm");
		goPanel.add (UiUtils.setWidth(goButton, 100.0, Unit.PCT)) ;
		
		panel.add(goPanel) ;
		
		panelV.add(panel);
		
		return panelV ;
	}

	@Override
	public AirportCode getAirport() {
		try {
			if (airportCodes.getValue().isEmpty()) {
				return null ;
			} else {
				return AirportCode.valueOf(airportCodes.getValue().toUpperCase());
			}
		} catch (IllegalArgumentException | NullPointerException e) {
			// invalid input...
			// ...
			return null ;
		}
	}

	@Override
	public String getStartDate() {
		Date date = arrivalDate.getValue() ;
		return (date == null)?(null):(fmt.format(date)+"T"+from.getSelectedItemText()) ;
	}

	@Override
	public String getEndDate() {
		Date date = arrivalDate.getValue() ;
		return (date == null)?(null):(fmt.format(date)+"T"+to.getSelectedItemText()) ;
	}
}
