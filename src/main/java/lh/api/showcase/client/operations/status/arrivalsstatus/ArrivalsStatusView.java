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

package lh.api.showcase.client.operations.status.arrivalsstatus;

import com.vaadin.shared.ui.grid.HeightMode;

import lh.api.showcase.client.Messages;
import lh.api.showcase.client.operations.FlightUtils;
import lh.api.showcase.client.operations.status.AbstractStatusView;
import lh.api.showcase.client.operations.status.arrivalsstatus.ArrivalStatus;
import lh.api.showcase.shared.TimeStatusCode;

public class ArrivalsStatusView extends AbstractStatusView<ArrivalStatus> {
	
	static class ArrivalsStatusGrid extends StatusGrid<ArrivalStatus> {
				
		public ArrivalsStatusGrid() {
			super();
			setSelectionMode(SelectionMode.SINGLE);
			this.setWidth("650px");
			this.setHeightMode(HeightMode.ROW);
			this.setHeightByRows(8);
			
			addColumn(new Column<String, ArrivalStatus>(Messages.Util.INSTANCE.get().arrival()) {
				@Override
				public String getValue(ArrivalStatus row) {
	
					StringBuilder sb = new StringBuilder () ;	
					try {
						TimeStatusCode tsc = TimeStatusCode.valueOf(row.getArrival().getTimeStatus().getCode()) ;
						sb.append(tsc.toString()) ;
						sb.append(" - ") ;
						sb.append(tsc.getDescription()) ;
						sb.append(" - ") ;
					} catch (IllegalArgumentException | NullPointerException e) {}
					sb.append(FlightUtils.getArrivalLocalDate(row.getFligth())) ;
					return sb.toString();
				}
			}).setWidth(440);
		}
	}
	
	public ArrivalsStatusView () {
		super (Messages.Util.INSTANCE.get().arrivalsStatus(), new ArrivalsStatusGrid ()) ;
	}
}
