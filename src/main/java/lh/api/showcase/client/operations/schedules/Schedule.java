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

package lh.api.showcase.client.operations.schedules;

import java.util.ArrayList;
import java.util.List;

import lh.api.showcase.client.operations.Flight;

public class Schedule {

	private final String duration ;
	private final List<Flight> fligths ;
	
	public Schedule(String duration, List<Flight> fligths) {
		super();
		this.duration = duration;
		this.fligths = new ArrayList<Flight> ();
		if (fligths != null) {
			this.fligths.addAll(fligths) ;
		}
	}

	public String getDuration() {
		return duration;
	}

	public List<Flight> getFligths() {
		return fligths;
	}	
}
