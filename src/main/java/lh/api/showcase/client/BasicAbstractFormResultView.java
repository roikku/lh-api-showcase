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

package lh.api.showcase.client;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.widget.grid.datasources.ListDataSource;
import com.vaadin.client.widgets.Grid;

public abstract class BasicAbstractFormResultView<T> extends Composite implements BasicFormResultDisplay {

	protected Logger logger = Logger.getLogger("View");
	
	protected final TextArea textArea = new TextArea () ;
	protected final Button goButton = new Button (Messages.Util.INSTANCE.get().go()) ;
	protected Anchor backLink = new Anchor (Messages.Util.INSTANCE.get().back()) ;

	private Grid<T> grid = null ;
	
	private Panel formPanel = new FlowPanel ();
	private Panel resultPanel = new FlowPanel ();
	private Panel bottomNavigationPanel = new FlowPanel ();
	
	protected final Panel busyIndicatorPanel = new FlowPanel() ;
	protected final Panel messagePanel = new FlowPanel() ;
	
	public BasicAbstractFormResultView (String title, final Grid<T> grid) {
		this (title, grid, false) ;
	}
	
	public BasicAbstractFormResultView (String title, final Grid<T> grid, boolean fullWidth) {
		super () ;
		this.grid = grid ;
		Panel main = new VerticalPanel () ;
		main.setWidth("100%");
		if (title != null) {
			main.add (new HTML("<h1>" + title + "</h1>")) ;
		}
		((HasHorizontalAlignment) main).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		
		backLink.setStyleDependentName("page", true);
		
		busyIndicatorPanel.getElement().getStyle().setPadding(10.0, Unit.PX);
		busyIndicatorPanel.add(new Image("images/busy.gif"));
		busyIndicatorPanel.setVisible(false);
		
		main.add(formPanel) ;
		main.add(messagePanel);
		main.add(busyIndicatorPanel);
		main.add(resultPanel) ;
		main.add(bottomNavigationPanel) ;
	
		initWidget(main);
		
		if (grid != null) {

			if (fullWidth) {
				grid.setWidth("100%");
				setResizeHandler () ;
			}
			Scheduler.get().scheduleDeferred(new ScheduledCommand() {
				@Override
				public void execute() {
					if (grid.getOffsetWidth() > Window.getClientWidth()) {
						grid.setWidth("100%");
						setResizeHandler () ;
					}			
				}
			});
		}
	}
	
	private void setResizeHandler () {
		Window.addResizeHandler(new ResizeHandler() {
			@Override
			public void onResize(ResizeEvent event) {
				Scheduler.get().scheduleDeferred(
						new ScheduledCommand() {
							@Override
							public void execute() {
								if (grid != null) {
									grid.onResize();
								}
							}
						});
			}
		});
	}
	
	protected final void init () {
		formPanel.add(getForm ()) ;
		resultPanel.add(getResultWidgets ()) ;
		bottomNavigationPanel.add(getBottomNavigationPanel ());
	}
	
	protected abstract Widget getForm () ;
	
	protected Widget getBottomNavigationPanel () {
		Panel p = new FlowPanel () ;
		p.getElement().getStyle().setMarginTop(30.0, Unit.PX);
		p.add(backLink);
		return p ;
	}

	protected Widget getResultWidgets () {
		VerticalPanel panel = new VerticalPanel () ;
		panel.setWidth("100%");
		((HasHorizontalAlignment) panel).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		
		panel.setSpacing(10);
		panel.add(new HTML ("<br>"));
		
		if (grid != null) {
			panel.add(new Label (Messages.Util.INSTANCE.get().results()));
			panel.add(grid);
		}
	
		DisclosurePanel advancedDisclosure = new DisclosurePanel (Messages.Util.INSTANCE.get().rawResults()) ;
		
		StringBuilder textAreaWidth = new StringBuilder () ;
		textAreaWidth.append(Math.min(500, Window.getClientWidth())) ;
		textAreaWidth.append ("px") ;
		textArea.setSize(textAreaWidth.toString(), "300px");
		//textArea.getElement().getStyle().setProperty("maxWidth", "500px");
		//textArea.setHeight("300px");
		advancedDisclosure.add(textArea);
		
		panel.add (advancedDisclosure) ;
		
		return panel ;
	}
	
	@Override
	public HasClickHandlers getGoHandler() {
		return goButton;
	}
	
	@Override
	public HasClickHandlers getBackHandler() {
		return backLink;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setJson(String json, JsonParser<?> parser) {
		textArea.setText(json);
		if (parser != null) {
			try {
				grid.setDataSource(new ListDataSource<T>((List<T>) parser.parse(json)));
			} catch (NullPointerException /*| com.google.gwt.core.client.JavaScriptException*/ e) {
				logger.log(Level.SEVERE, "Error occurred while parsing the json string", e);
			}
		}
	}
	
	@Override
	public void setMessage (String message, UiUtils.MessageType type) {
		messagePanel.setVisible(true);
		messagePanel.clear();
		UiUtils.setMessage (messagePanel, message, type, null) ;
	}
	
	@Override
	public void clearMessage () {
		messagePanel.clear();
		messagePanel.setVisible(false);
	}

	@Override
	public void setBusy(boolean busy) {
		busyIndicatorPanel.setVisible(busy);
	}

	@Override
	public Widget asWidget() {
		return this;
	}
}
