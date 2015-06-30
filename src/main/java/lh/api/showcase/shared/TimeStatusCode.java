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

package lh.api.showcase.shared;

public enum TimeStatusCode {
	FE, NI, OT, DL, NO ;

	public String getDescription () {
		StringBuilder description = new StringBuilder () ;
		switch (this) {
		case FE:
			description.append("Flight Early") ;
			break;
		case NI:
			description.append("Next Information") ;
			break;
		case OT:
			description.append("Flight On Time") ;
			break;
		case DL:
			description.append("Flight Delayed") ;
			break;
		case NO:
			description.append("No status") ;
			break;
		default:
			description.append(this.name()) ;
			break;
		}
		return description.toString() ;
	}
}
