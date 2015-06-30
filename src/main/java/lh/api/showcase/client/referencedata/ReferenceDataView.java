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

package lh.api.showcase.client.referencedata;

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

public class ReferenceDataView extends Composite implements ReferenceDataPresenter.Display {
	
	private final Panel mainPanel = new FlowPanel() ;
	
	final private Button referenceDataCountriesButton = new Button (Messages.Util.INSTANCE.get().countries()) ;
	final private Button referenceDataCitiesButton = new Button (Messages.Util.INSTANCE.get().cities()) ;
	final private Button referenceDataAirportsButton = new Button (Messages.Util.INSTANCE.get().airports()) ;
	final private Button referenceDataNearestAirportsButton = new Button (Messages.Util.INSTANCE.get().nearestAirports()) ;
	final private Button referenceDataAirlinesButton = new Button (Messages.Util.INSTANCE.get().airlines()) ;
	final private Button referenceDataAicraftButton = new Button (Messages.Util.INSTANCE.get().aircraft()) ;
		
	final protected Anchor backLink = new Anchor (Messages.Util.INSTANCE.get().back()) ;
	
	public ReferenceDataView () {
		super () ;
		
		mainPanel.setWidth("100%");
		mainPanel.add(new HTML ("<h1>Reference Data</h1>"));

		VerticalPanel panelV = new VerticalPanel () ; 
		panelV.setWidth("100%");		
		((HasHorizontalAlignment) panelV).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		
		Panel panel = new FlowPanel() ;
		panel.setStyleName("flowPanelMainMenuPage");

		Panel referenceDataCountriesPanel = new FlowPanel();
		referenceDataCountriesPanel.setStyleName("flowPanelVerRowMenuPage");
		referenceDataCountriesPanel.add(UiUtils.setWidth(referenceDataCountriesButton, 100.0, Unit.PCT));
		
		Panel referenceDataCitiesPanel = new FlowPanel();
		referenceDataCitiesPanel.setStyleName("flowPanelVerRowMenuPage");
		referenceDataCitiesPanel.add(UiUtils.setWidth(referenceDataCitiesButton, 100.0, Unit.PCT));
		
		Panel referenceDataAirportsPanel = new FlowPanel();
		referenceDataAirportsPanel.setStyleName("flowPanelVerRowMenuPage");
		referenceDataAirportsPanel.add(UiUtils.setWidth(referenceDataAirportsButton, 100.0, Unit.PCT));
		
		Panel referenceDataNearestAirportsPanel = new FlowPanel();
		referenceDataNearestAirportsPanel.setStyleName("flowPanelVerRowMenuPage");
		referenceDataNearestAirportsPanel.add(UiUtils.setWidth(referenceDataNearestAirportsButton, 100.0, Unit.PCT));
		
		Panel referenceDataAirlinesPanel = new FlowPanel();
		referenceDataAirlinesPanel.setStyleName("flowPanelVerRowMenuPage");
		referenceDataAirlinesPanel.add(UiUtils.setWidth(referenceDataAirlinesButton, 100.0, Unit.PCT));
	
		Panel referenceDataAicraftPanel = new FlowPanel();
		referenceDataAicraftPanel.setStyleName("flowPanelVerRowMenuPage");
		referenceDataAicraftPanel.add(UiUtils.setWidth(referenceDataAicraftButton, 100.0, Unit.PCT));
	
		panel.add(referenceDataCountriesPanel);
		panel.add(referenceDataCitiesPanel);
		panel.add(referenceDataAirportsPanel);	
		panel.add(referenceDataNearestAirportsPanel);	
		panel.add(referenceDataAirlinesPanel);	
		panel.add(referenceDataAicraftPanel);	
		
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
	public HasClickHandlers getCountriesHandler() {
		return referenceDataCountriesButton;
	}

	@Override
	public HasClickHandlers getCitiesHandler() {
		return referenceDataCitiesButton;
	}

	@Override
	public HasClickHandlers getAirportsHandler() {
		return referenceDataAirportsButton;
	}

	@Override
	public HasClickHandlers getNearestAirportsHandler() {
		return referenceDataNearestAirportsButton;
	}

	@Override
	public HasClickHandlers getAirlinesHandler() {
		return referenceDataAirlinesButton;
	}

	@Override
	public HasClickHandlers getAircraftHandler() {
		return referenceDataAicraftButton;
	}

	@Override
	public HasClickHandlers getBackHandler() {
		return backLink;
	}
}
