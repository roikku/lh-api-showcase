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

package lh.api.showcase.client.operations.status.departuresstatus;

import lh.api.showcase.client.operations.Flight;
import lh.api.showcase.client.operations.Flight.Departure;
import lh.api.showcase.client.operations.status.Status;

public class DepartureStatus implements Status {
	
	private final Flight fligth ;
	
	public DepartureStatus(Flight fligth) {
		super();
		if (fligth == null) {
			throw new NullPointerException () ;
		}
		if (fligth.getDeparture() == null) {
			throw new IllegalArgumentException () ;
		}
		this.fligth = fligth ;
	}

	public Departure getDeparture() {
		return fligth.getDeparture();
	}

	@Override
	public Flight getFligth() {
		return fligth;
	}
}
