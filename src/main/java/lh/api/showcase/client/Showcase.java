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

import java.util.logging.Logger;

import lh.api.showcase.client.offers.service.OffersServiceAsync;
import lh.api.showcase.client.operations.service.OperationsServiceAsync;
import lh.api.showcase.client.referencedata.service.ReferenceDataServiceAsync;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.RootPanel;
import com.allen_sauer.gwt.log.client.Log;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Showcase implements EntryPoint {

	private Logger logger = Logger.getLogger("Showcase");
  
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		logger.info("Application laoding");
		Log.setUncaughtExceptionHandler();
		
		HandlerManager eventBus = new HandlerManager(null);
		AppController appController = new AppController(ReferenceDataServiceAsync.Util.getInstance(),
				OperationsServiceAsync.Util.getInstance(),
				OffersServiceAsync.Util.getInstance(),
				eventBus);
	
		appController.go(RootPanel.get());
	}
}
