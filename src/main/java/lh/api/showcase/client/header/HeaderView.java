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

package lh.api.showcase.client.header;


import lh.api.showcase.client.Messages;

import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.event.dom.client.HasClickHandlers;

public class HeaderView extends Composite implements HeaderPresenter.Display {

	private final Panel mainPanel = new FlowPanel();
	
	final private Anchor linkHome = new Anchor (Messages.Util.INSTANCE.get().home()) ;
	final private Anchor linkAbout = new Anchor (Messages.Util.INSTANCE.get().about()) ;
	
	public HeaderView () {
		super () ;
		mainPanel.setStyleName("menuBar");

		mainPanel.add(linkHome);
		mainPanel.add(linkAbout) ;

		initWidget(mainPanel);
	}
	
	@Override
	public Widget asWidget() {
		return this;
	}
	

	@Override
	public HasClickHandlers getHomeLink() {
		return linkHome;
	}


	@Override
	public HasClickHandlers getAboutLink() {
		return linkAbout;
	}
}
