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

package lh.api.showcase.client.operations;

import lh.api.showcase.client.Messages;
import lh.api.showcase.client.UiUtils;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class OperationsView extends Composite implements OperationsPresenter.Display {
	
	private final Panel mainPanel = new FlowPanel() ;
	
	final private Button operationsScheduleButton = new Button (Messages.Util.INSTANCE.get().schedule()) ;
	final private Button operationsFlighStatusButton = new Button (Messages.Util.INSTANCE.get().flightStatus()) ;
	final private Button operationsFlightStatusByRouteButton = new Button (Messages.Util.INSTANCE.get().flightStatusByRoute()) ;
	final private Button operationsArrivalsStatusButton = new Button (Messages.Util.INSTANCE.get().arrivalsStatus()) ;
	final private Button operationsDeparturesStatusButton = new Button (Messages.Util.INSTANCE.get().departureStatus()) ;
	
	final protected Anchor backLink = new Anchor (Messages.Util.INSTANCE.get().back()) ;
	
	public OperationsView () {
		super () ;
		
		mainPanel.setWidth("100%");
		mainPanel.add(new HTML ("<h1>" + Messages.Util.INSTANCE.get().operations() + "</h1>"));
		
		VerticalPanel panelV = new VerticalPanel () ; 
		panelV.setWidth("100%");
		((HasHorizontalAlignment) panelV).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		
		Panel panel = new FlowPanel() ;
		panel.setStyleName("flowPanelMainMenuPage");
		
		Panel operationsSchedulePanel = new FlowPanel();
		operationsSchedulePanel.setStyleName("flowPanelVerRowMenuPage");
		operationsSchedulePanel.add(UiUtils.setWidth(operationsScheduleButton, 100.0, Unit.PCT));
		
		Panel operationsFlighStatusPanel = new FlowPanel();
		operationsFlighStatusPanel.setStyleName("flowPanelVerRowMenuPage");
		operationsFlighStatusPanel.add(UiUtils.setWidth(operationsFlighStatusButton, 100.0, Unit.PCT));
		
		Panel operationsFlightStatusByRoutePanel = new FlowPanel();
		operationsFlightStatusByRoutePanel.setStyleName("flowPanelVerRowMenuPage");
		operationsFlightStatusByRoutePanel.add(UiUtils.setWidth(operationsFlightStatusByRouteButton, 100.0, Unit.PCT));
	
		Panel operationsArrivalsStatusPanel = new FlowPanel();
		operationsArrivalsStatusPanel.setStyleName("flowPanelVerRowMenuPage");
		operationsArrivalsStatusPanel.add(UiUtils.setWidth(operationsArrivalsStatusButton, 100.0, Unit.PCT));
		
		Panel operationsDeparturesStatusPanel = new FlowPanel();
		operationsDeparturesStatusPanel.setStyleName("flowPanelVerRowMenuPage");
		operationsDeparturesStatusPanel.add(UiUtils.setWidth(operationsDeparturesStatusButton, 100.0, Unit.PCT));

		panel.add(operationsSchedulePanel);
		panel.add(operationsFlighStatusPanel);
		panel.add(operationsFlightStatusByRoutePanel);	
		panel.add(operationsArrivalsStatusPanel);	
		panel.add(operationsDeparturesStatusPanel);	
		
		backLink.setStyleDependentName("page", true);
		
		panelV.add(panel);
		mainPanel.add(panelV) ;
		mainPanel.add(getBottomNavigationPanel ()) ;
		
		initWidget(mainPanel);
	}
	
	protected Widget getBottomNavigationPanel () {
		VerticalPanel p = new VerticalPanel () ; 
		p.setWidth("100%");
		((HasHorizontalAlignment) p).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		p.getElement().getStyle().setMarginTop(30.0, Unit.PX);
		p.add(backLink);
		return p ;
	}

	@Override
	public Widget asWidget() {
		return this;
	}
	
	@Override
	public HasClickHandlers getSchedulesHandler() {
		return operationsScheduleButton;
	}

	@Override
	public HasClickHandlers getFlightStatusHandler() {
		return operationsFlighStatusButton;
	}

	@Override
	public HasClickHandlers getFlightStatusByRouteHandler() {
		return operationsFlightStatusByRouteButton;
	}

	@Override
	public HasClickHandlers getArrivalsStatusHandler() {
		return operationsArrivalsStatusButton;
	}

	@Override
	public HasClickHandlers getDeparturesStatusHandler() {
		return operationsDeparturesStatusButton;
	}

	@Override
	public HasClickHandlers getBackHandler() {
		return backLink;
	}
}
