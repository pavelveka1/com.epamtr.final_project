package by.epamtr.airline.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Flight implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String currentCity;
	private String destinationCity;
	private int flightRange;
	private int flightTime;
	private LocalDateTime timeDeparture;
	private String aircraftType;
	private String aircraftNumber;
	private String status;
	private Crew crew;
	
	public Flight(String currentCity, String destinationCity, int flightRange, int flightTime,
			LocalDateTime timeDeparture, String aircraftType, String aircraftNumber, String status, Crew crew) {
		super();
		this.currentCity = currentCity;
		this.destinationCity = destinationCity;
		this.flightRange = flightRange;
		this.flightTime = flightTime;
		this.timeDeparture = timeDeparture;
		this.aircraftType = aircraftType;
		this.aircraftNumber = aircraftNumber;
		this.status = status;
		this.crew = crew;
	}

	public Flight() {

	}

	public String getCurrentCity() {
		return currentCity;
	}

	public void setCurrentCity(String currentCity) {
		this.currentCity = currentCity;
	}

	public String getDestinationCity() {
		return destinationCity;
	}

	public void setDestinationCity(String destinationCity) {
		this.destinationCity = destinationCity;
	}

	public int getFlightRange() {
		return flightRange;
	}

	public void setFlightRange(int flightRange) {
		this.flightRange = flightRange;
	}

	public int getFlightTime() {
		return flightTime;
	}

	public void setFlightTime(int flightTime) {
		this.flightTime = flightTime;
	}

	public LocalDateTime getTimeDeparture() {
		return timeDeparture;
	}

	public void setTimeDeparture(LocalDateTime timeDeparture) {
		this.timeDeparture = timeDeparture;
	}

	public String getAircraftType() {
		return aircraftType;
	}

	public void setAircraftType(String aircraftType) {
		this.aircraftType = aircraftType;
	}

	public String getAircraftNumber() {
		return aircraftNumber;
	}

	public void setAircraftNumber(String aircraftNumber) {
		this.aircraftNumber = aircraftNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Crew getCrews() {
		return crew;
	}

	public void setCrews(Crew crew) {
		this.crew = crew;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aircraftNumber == null) ? 0 : aircraftNumber.hashCode());
		result = prime * result + ((aircraftType == null) ? 0 : aircraftType.hashCode());
		result = prime * result + ((crew == null) ? 0 : crew.hashCode());
		result = prime * result + ((currentCity == null) ? 0 : currentCity.hashCode());
		result = prime * result + ((destinationCity == null) ? 0 : destinationCity.hashCode());
		result = prime * result + flightRange;
		result = prime * result + flightTime;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((timeDeparture == null) ? 0 : timeDeparture.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flight other = (Flight) obj;
		if (aircraftNumber == null) {
			if (other.aircraftNumber != null)
				return false;
		} else if (!aircraftNumber.equals(other.aircraftNumber))
			return false;
		if (aircraftType == null) {
			if (other.aircraftType != null)
				return false;
		} else if (!aircraftType.equals(other.aircraftType))
			return false;
		if (crew == null) {
			if (other.crew != null)
				return false;
		} else if (!crew.equals(other.crew))
			return false;
		if (currentCity == null) {
			if (other.currentCity != null)
				return false;
		} else if (!currentCity.equals(other.currentCity))
			return false;
		if (destinationCity == null) {
			if (other.destinationCity != null)
				return false;
		} else if (!destinationCity.equals(other.destinationCity))
			return false;
		if (flightRange != other.flightRange)
			return false;
		if (flightTime != other.flightTime)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (timeDeparture == null) {
			if (other.timeDeparture != null)
				return false;
		} else if (!timeDeparture.equals(other.timeDeparture))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Flight [currentCity=" + currentCity + ", destinationCity=" + destinationCity + ", flightRange="
				+ flightRange + ", flightTime=" + flightTime + ", timeDeparture=" + timeDeparture + ", aircraftType="
				+ aircraftType + ", aircraftNumber=" + aircraftNumber + ", status=" + status + ", crew=" + crew + "]";
	}

	
	
	
	

}
