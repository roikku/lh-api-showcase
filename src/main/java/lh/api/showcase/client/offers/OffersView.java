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

public class OffersView extends Composite implements OffersPresenter.Display {
	
	private final Panel mainPanel = new FlowPanel() ;
	
	final private Button offersSeatMapsButton = new Button (Messages.Util.INSTANCE.get().seatMaps()) ;
	
	final protected Anchor backLink = new Anchor (Messages.Util.INSTANCE.get().back()) ;
	
	public OffersView () {
		super () ;
		
		mainPanel.setWidth("100%");
		mainPanel.add(new HTML ("<h1>" + Messages.Util.INSTANCE.get().offers() + "</h1>"));

		VerticalPanel panelV = new VerticalPanel () ; 
		panelV.setWidth("100%");		
		((HasHorizontalAlignment) panelV).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		
		Panel panel = new FlowPanel() ;
		panel.setStyleName("flowPanelMainMenuPage");
		
		Panel offersSeatMapsPanel = new FlowPanel();
		offersSeatMapsPanel.setStyleName("flowPanelVerRowMenuPage");
		offersSeatMapsPanel.add(UiUtils.setWidth(offersSeatMapsButton, 100.0, Unit.PCT));
		
		panel.add(offersSeatMapsPanel);
		
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
	public HasClickHandlers getSeatMapsHandler() {
		return offersSeatMapsButton;
	}

	@Override
	public HasClickHandlers getBackHandler() {
		return backLink;
	}
}
