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

package lh.api.showcase.server.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;

public class ConfigurationUtils {

	private static Logger logger = Logger.getLogger(ConfigurationUtils.class.getName());
	
	private ConfigurationUtils () { super () ; throw new IllegalStateException () ; }
	
	public static synchronized XMLConfiguration getConfig () {
		
		XMLConfiguration config = null ;
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("lh/api/showcase/Showcase-settings.xml") ;
		if (in != null) {
			logger.info("Load configuration");
			config = new XMLConfiguration();
			try {
				config.load(in);
			} catch (ConfigurationException e) {
				logger.severe("Error occurred while opeing config file");
			} catch (Exception e) {
				logger.severe("Error occurred while opeing config file: " + e.getMessage());
				throw e ;
			}finally {
				try {
					in.close();
				} catch (IOException e) {}
			}
		} else {
			logger.info("NO configuration file found");
		}
		return config ;
	}
}
