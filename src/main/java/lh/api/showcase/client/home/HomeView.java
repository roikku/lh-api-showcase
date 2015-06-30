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

package lh.api.showcase.client.home;

import lh.api.showcase.client.Messages;
import lh.api.showcase.client.UiUtils;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class HomeView extends Composite implements HomePresenter.Display {
	
	private final Panel mainPanel = new FlowPanel() ;
	
	final private Button referenceDataButton = new Button (Messages.Util.INSTANCE.get().referenceData()) ;
	final private Button operationsButton = new Button (Messages.Util.INSTANCE.get().operations()) ;
	final private Button offersButton = new Button (Messages.Util.INSTANCE.get().offers()) ;
	
	public HomeView () {
		super () ;
		
		mainPanel.setWidth("100%");
		mainPanel.add(new HTML ("<h1>"+ Messages.Util.INSTANCE.get().home() +"</h1>"));

		VerticalPanel panelV = new VerticalPanel () ; 
		panelV.setWidth("100%");
		((HasHorizontalAlignment) panelV).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);

		Panel panel = new FlowPanel() ;
		panel.setStyleName("flowPanelMainMenuPage");

		Panel referenceDataPanel = new FlowPanel();
		referenceDataPanel.setStyleName("flowPanelVerRowMenuPage");
		referenceDataPanel.add(UiUtils.setWidth(referenceDataButton, 100.0, Unit.PCT));
		
		Panel operationsPanel = new FlowPanel();
		operationsPanel.setStyleName("flowPanelVerRowMenuPage");
		operationsPanel.add(UiUtils.setWidth(operationsButton, 100.0, Unit.PCT));
		
		Panel offersPanel = new FlowPanel();
		offersPanel.setStyleName("flowPanelVerRowMenuPage");
		offersPanel.add(UiUtils.setWidth(offersButton, 100.0, Unit.PCT));
		
		panel.add(referenceDataPanel) ;
		panel.add(operationsPanel) ;
		panel.add(offersPanel) ;
		
		panelV.add (panel) ;
		
		mainPanel.add(panelV) ;
		
		initWidget(mainPanel);
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public HasClickHandlers getReferenceDataHandler() {
		return referenceDataButton;
	}

	@Override
	public HasClickHandlers getOperationsHandler() {
		return operationsButton;
	}

	@Override
	public HasClickHandlers getOffersHandler() {
		return offersButton;
	}
}
