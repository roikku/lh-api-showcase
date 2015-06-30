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

import java.util.logging.Logger;

import lh.api.showcase.client.Presenter;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class AboutPresenter implements Presenter {

	public interface Display {
		Widget asWidget();
	}
	
	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger("AboutPresenter");

	private final Display display ;
	@SuppressWarnings("unused")
	private final HandlerManager eventBus;
	

	public AboutPresenter(Display display, HandlerManager eventBus) {
		super();
		this.display = display;
		this.eventBus = eventBus;
	}


	@Override
	public void go(HasWidgets container) {
		bind();
		container.add(display.asWidget());
	}
	
	public void bind() {
	}
}

