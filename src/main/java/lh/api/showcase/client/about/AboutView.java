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

package lh.api.showcase.client.about;

import lh.api.showcase.client.Messages;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class AboutView extends Composite implements AboutPresenter.Display {
	
	private final Panel mainPanel = new FlowPanel() ;
	
	public AboutView () {
		super () ;

		mainPanel.setWidth("100%");
		mainPanel.add(new HTML ("<h1>"+ Messages.Util.INSTANCE.get().about() +"</h1>"));

		Panel panelV = new VerticalPanel() ;
		panelV.setWidth("100%");
		((HasHorizontalAlignment) panelV).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		
		Panel panel = new FlowPanel() ;
		panel.getElement().getStyle().setProperty("maxWidth", "400px");
		
		// might be judicious to use css instead...
		String pstyle = "style=\"text-align: justify; font-size: 125%;\"" ;
		
		StringBuilder htmlStr = new StringBuilder () ;
		
		htmlStr.append ("<h2>General Information</h2>") ;
		
		htmlStr.append ("<p ").append (pstyle).append(">") ;
		htmlStr.append ("This simple web app provides a showcase for the ") ;
		htmlStr.append ("<a href=\"https://developer.lufthansa.com/\">Lufthansa Open API</a>. "); 
		htmlStr.append ("The API and data are used in conformance with the <a href=\"https://developer.lufthansa.com/General_Terms_and_Conditions\">license</a>.") ;
		htmlStr.append ("</p>") ;
		
		htmlStr.append ("<h2>Some Technical Aspects</h2>") ;
		
		htmlStr.append ("<p ").append (pstyle).append(">") ;
		htmlStr.append ("This app is developped using <a href=\"http://www.gwtproject.org/\">GWT</a>. ") ;
		htmlStr.append ("It reposes on the MVP architecture (Model-View-Presenter) as described in the article <a href=\"http://www.gwtproject.org/articles/mvp-architecture.html\">Building MVP apps</a>.") ;
		htmlStr.append ("</p>") ;
		
		htmlStr.append ("<h2>Source Code</h2>") ;

		htmlStr.append ("<p ").append (pstyle).append(">") ;
		htmlStr.append ("The source code is available on <a href=\"https://github.com/roikku/lh-api-showcase\">GitHub</a>. ");
		htmlStr.append ("</p>") ;

		htmlStr.append ("<h2>Disclaimer</h2>") ;
		
		htmlStr.append ("<p ").append (pstyle).append(">") ;
		htmlStr.append ("The owner of this website cannot be held liable for damages of any kind arising out of use, reference to, or reliance on any information found on this site. It must be borne in mind that no guarantee is given that the information provided in this website is correct, complete, and up-to-date.") ;
		htmlStr.append ("</p>") ;
		
		panel.add(new HTML(htmlStr.toString()));
		panelV.add(panel);
		mainPanel.add(panelV);
		initWidget(mainPanel);
	}
	

	@Override
	public Widget asWidget() {
		return this;
	}
}