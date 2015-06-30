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


package lh.api.showcase.server;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ShowcaseServletContextListener implements ServletContextListener {

	private Logger logger = Logger.getLogger(ShowcaseServletContextListener.class.getName());
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

		try {
			logger.info("Close http client");
			DefaultHttpClient.INSTANCE.get().close();
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Error occurred while closing http client", e);
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {

	}
}
