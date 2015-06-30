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

package lh.api.showcase.client.operations;

public class Flight {
	
	
	public static class StatusCodeDefinition {
		private final String code ;
		private final String definition ;
		public StatusCodeDefinition(String code, String definition) {
			super();
			this.code = code;
			this.definition = definition;
		}
		public String getCode() {
			return code;
		}
		public String getDefinition() {
			return definition;
		}
		@Override
		public String toString() {
			return "code=" + code + ", definition=" + definition;
		}
	}
	

	public static class FlightNode {
		private final String airportCode ;
		private final String scheduledTimeLocal ;
		private final String scheduledTimeUtc ;
		private final String estimatedOrActualTimeLocal ;
		private final String estimatedOrActualTimeUtc ;
		private final String terminal ;
		private final String terminalGate ;
		private final StatusCodeDefinition timeStatus ;
		
		protected FlightNode(Builder builder) {
			super();
			this.airportCode = builder.airportCode;
			this.scheduledTimeLocal = builder.scheduledTimeLocal;
			this.scheduledTimeUtc = builder.scheduledTimeUtc;
			this.estimatedOrActualTimeLocal = builder.estimatedOrActualTimeLocal;
			this.estimatedOrActualTimeUtc = builder.estimatedOrActualTimeUtc;
			this.timeStatus = builder.timeStatus;
			this.terminalGate = builder.terminalGate;
			this.terminal = builder.terminal ;
		}

		@Override
		public String toString() {
			return "FlightNode [airportCode=" + airportCode
					+ ", scheduledTimeLocal=" + scheduledTimeLocal
					+ ", terminal=" + terminal + ", scheduledTimeUtc="
					+ scheduledTimeUtc + ", estimatedOrActualTimeLocal="
					+ estimatedOrActualTimeLocal
					+ ", estimatedOrActualTimeUtc=" + estimatedOrActualTimeUtc
					+ ", terminalGate=" + terminalGate + ", timeStatus="
					+ timeStatus.toString() + "]";
		}


		public String getAirportCode() {
			return airportCode;
		}

		public String getScheduledTimeLocal() {
			return scheduledTimeLocal;
		}

		public String getTerminal() {
			return terminal;
		}

		public String getScheduledTimeUtc() {
			return scheduledTimeUtc;
		}

		public String getEstimatedOrActualTimeLocal() {
			return estimatedOrActualTimeLocal;
		}

		public String getEstimatedOrActualTimeUtc() {
			return estimatedOrActualTimeUtc;
		}

		public StatusCodeDefinition getTimeStatus() {
			return timeStatus;
		}

		public String getTerminalGate() {
			return terminalGate;
		}
		
		public static class Builder
		{
			private String airportCode = "" ;
			private String scheduledTimeLocal = "" ;
			private String terminal = "" ;
			private String scheduledTimeUtc = "" ;
			private String estimatedOrActualTimeLocal = "" ;
			private String estimatedOrActualTimeUtc = "" ;
			private String terminalGate = "" ;
			private StatusCodeDefinition timeStatus = null ;
			
			public Builder() {
		        super();
	        }
			
			public FlightNode build () {
				return new FlightNode (this) ;
			}
			
			public Builder setAirportCode(String airportCode) {
				this.airportCode = airportCode;
				return this ;
			}

			public Builder setScheduledTimeLocal(String scheduledTimeLocal) {
				this.scheduledTimeLocal = scheduledTimeLocal;
				return this ;
			}

			public Builder setTerminal(String terminal) {
				this.terminal = terminal;
				return this ;
			}

			public Builder setScheduledTimeUtc(String scheduledTimeUtc) {
				this.scheduledTimeUtc = scheduledTimeUtc;
				return this ;
			}

			public Builder setEstimatedOrActualTimeLocal(String estimatedOrActualTimeLocal) {
				this.estimatedOrActualTimeLocal = estimatedOrActualTimeLocal;
				return this ;
			}

			public Builder setEstimatedOrActualTimeUtc(String estimatedOrActualTimeUtc) {
				this.estimatedOrActualTimeUtc = estimatedOrActualTimeUtc;
				return this ;
			}

			public Builder setTimeStatus(StatusCodeDefinition timeStatus) {
				this.timeStatus = timeStatus;
				return this ;
			}

			public Builder setTerminalGate(String terminalGate) {
				this.terminalGate = terminalGate;
				return this ;
			}
		}
	}
	
	
	public static class Departure extends FlightNode {

		public Departure(FlightNode flightNode) {
			super(new FlightNode.Builder().setAirportCode(flightNode.airportCode)
					.setScheduledTimeLocal(flightNode.scheduledTimeLocal)
					.setScheduledTimeUtc(flightNode.scheduledTimeUtc)
					.setEstimatedOrActualTimeLocal(flightNode.estimatedOrActualTimeLocal)
					.setEstimatedOrActualTimeUtc(flightNode.estimatedOrActualTimeUtc)
					.setTimeStatus(flightNode.timeStatus)
					.setTerminal(flightNode.terminal)
					.setTerminalGate(flightNode.terminalGate));
		}
	}
	

	public static class Arrival extends FlightNode  {
				
		public Arrival(FlightNode flightNode) {
			super(new FlightNode.Builder().setAirportCode(flightNode.airportCode)
					.setScheduledTimeLocal(flightNode.scheduledTimeLocal)
					.setScheduledTimeUtc(flightNode.scheduledTimeUtc)
					.setEstimatedOrActualTimeLocal(flightNode.estimatedOrActualTimeLocal)
					.setEstimatedOrActualTimeUtc(flightNode.estimatedOrActualTimeUtc)
					.setTimeStatus(flightNode.timeStatus)
					.setTerminal(flightNode.terminal)
					.setTerminalGate(flightNode.terminalGate));
		}
	}
	
	
	public static class Details {
		private final Long stopQuantity ;
		private final String daysOfOperation ;
		private final String effectiveDateStr ;
		private final String expirationDateStr ;	
		
		public Details(Builder builder) {
			super();
			this.stopQuantity = builder.stopQuantity;
			this.daysOfOperation = builder.daysOfOperation;
			this.effectiveDateStr = builder.effectiveDateStr;
			this.expirationDateStr = builder.expirationDateStr;
		}

		@Override
		public String toString() {
			return "Details [stopQuantity=" + stopQuantity
					+ ", daysOfOperation=" + daysOfOperation
					+ ", effectiveDateStr=" + effectiveDateStr
					+ ", expirationDateStr=" + expirationDateStr + "]";
		}

		public Long getStopQuantity() {
			return stopQuantity;
		}

		public String getDaysOfOperation() {
			return daysOfOperation;
		}

		public String getEffectiveDateStr() {
			return effectiveDateStr;
		}

		public String getExpirationDateStr() {
			return expirationDateStr;
		}
		
		public static class Builder
		{
			private Long stopQuantity = Long.valueOf(0);
			private String daysOfOperation = "" ;
			private String effectiveDateStr = "" ;
			private String expirationDateStr = "" ;
			
			public Builder () {
				super () ;
			}
			
			public Details build () {
				return new Details (this) ;
			}
			
			public Builder setStopQuantity(Long stopQuantity) {
				this.stopQuantity = stopQuantity;
				return this ;
			}

			public Builder setDaysOfOperation(String daysOfOperation) {
				this.daysOfOperation = daysOfOperation;
				return this ;
			}

			public Builder setEffectiveDateStr(String effectiveDateStr) {
				this.effectiveDateStr = effectiveDateStr;
				return this ;
			}

			public Builder setExpirationDateStr(String expirationDateStr) {
				this.expirationDateStr = expirationDateStr;
				return this ;
			}			
		}
	}
	
	
	private final Departure departure ;
	private final Arrival arrival ;
	private final String aircraftCode ;
	private final String airlineID ;
	private final String flightNumber ;
	private final Details scheduleDetails ;
	private final StatusCodeDefinition flightStatus ;
	
	
	public Flight(Departure departure, Arrival arrival, String aircraftCode,  String airlineID, String flightNumber,
			Details details, StatusCodeDefinition flightStatus) {
		super();
		this.arrival = arrival;
		this.departure = departure;
		this.aircraftCode = aircraftCode;
		this.scheduleDetails = details;
		this.airlineID = airlineID ;
		this.flightNumber = flightNumber ;
		this.flightStatus = flightStatus ;
	}
	
	public Departure getDeparture() {
		return departure;
	}

	public Arrival getArrival() {
		return arrival;
	}

	public String getAircraftCode() {
		return aircraftCode;
	}

	public Details getScheduleDetails() {
		return scheduleDetails;
	}
	
	public String getAirlineID() {
		return airlineID;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public StatusCodeDefinition getFlightStatus() {
		return flightStatus;
	}
}
