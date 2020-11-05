package by.epamtr.airline.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
/**
 * Entity class Flight
 * @author Pavel Veka
 *
 */
public class Flight implements Serializable {
	/**
	 * Serial version uid for serialization
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Unique identifier of flight
	 */
	private int idFlight;
	/**
	 * Name of city (start point of flight)
	 */
	private String currentCity;
	/**
	 * Name of city (end point of flight)
	 */
	private String destinationCity;
	/**
	 * Distance between start and and point of flight
	 */
	private int flightRange;
	/**
	 * Time in flight
	 */
	private int flightTime;
	/**
	 * Date of departure
	 */
	private String timeDeparture;
	/**
	 * Name of type of aircraft
	 */
	private String aircraftType;
	/**
	 * Registration number of aircraft
	 */
	private String aircraftNumber;
	/*
	 * Status of flight
	 */
	private String status;
	/*
	 * List of crew member for flight
	 */
	private List<Crew> crews;
/**
 * Constructor of Flight object with all parameters
 * @param idFlight
 * @param currentCity
 * @param destinationCity
 * @param flightRange
 * @param flightTime
 * @param timeDeparture
 * @param aircraftType
 * @param aircraftNumber
 * @param status
 */
	public Flight(int idFlight, String currentCity, String destinationCity, int flightRange, int flightTime,
			String timeDeparture, String aircraftType, String aircraftNumber, String status) {
		super();
		this.idFlight = idFlight;
		this.currentCity = currentCity;
		this.destinationCity = destinationCity;
		this.flightRange = flightRange;
		this.flightTime = flightTime;
		this.timeDeparture = timeDeparture;
		this.aircraftType = aircraftType;
		this.aircraftNumber = aircraftNumber;
		this.status = status;
	}
/**
 * Constructor of Flight object with parameters (parameters aircraftType and idFlight have default value)
 * @param currentCity
 * @param destinationCity
 * @param flightRange
 * @param flightTime
 * @param timeDeparture
 * @param aircraftNumber
 * @param status
 */
	public Flight(String currentCity, String destinationCity, int flightRange, int flightTime,
			String timeDeparture,  String aircraftNumber, String status) {
		super();
		this.currentCity = currentCity;
		this.destinationCity = destinationCity;
		this.flightRange = flightRange;
		this.flightTime = flightTime;
		this.timeDeparture = timeDeparture;
		this.aircraftNumber = aircraftNumber;
		this.status = status;
	}
	/**
	 * Constructor of Flight object without parameters
	 */
	public Flight() {

	}
/**
 * 
 * @return current city
 */
	public String getCurrentCity() {
		return currentCity;
	}
/**
 * 
 * @param currentCity new value
 */
	public void setCurrentCity(String currentCity) {
		this.currentCity = currentCity;
	}
/**
 * 
 * @return destination sity
 */
	public String getDestinationCity() {
		return destinationCity;
	}
/**
 * 
 * @param destinationCity new value
 */
	public void setDestinationCity(String destinationCity) {
		this.destinationCity = destinationCity;
	}
/**
 * 
 * @return range of flight in km
 */
	public int getFlightRange() {
		return flightRange;
	}
/**
 * 
 * @param flightRange new value
 */
	public void setFlightRange(int flightRange) {
		this.flightRange = flightRange;
	}
/**
 * 
 * @return time in flight in minutes
 */
	public int getFlightTime() {
		return flightTime;
	}
/**
 * 
 * @param flightTime new value
 */
	public void setFlightTime(int flightTime) {
		this.flightTime = flightTime;
	}
/**
 * 
 * @return date of departure
 */
	public String getTimeDeparture() {
		return timeDeparture;
	}
/**
 * 
 * @param timeDeparture new value
 */
	public void setTimeDeparture(String timeDeparture) {
		this.timeDeparture = timeDeparture;
	}
/**
 * 
 * @return name of type of aircraft
 */
	public String getAircraftType() {
		return aircraftType;
	}
/**
 * 
 * @param aircraftType new value
 */
	public void setAircraftType(String aircraftType) {
		this.aircraftType = aircraftType;
	}
/**
 * 
 * @return registration number of aircraft
 */
	public String getAircraftNumber() {
		return aircraftNumber;
	}
/**
 * 
 * @param aircraftNumber new value
 */
	public void setAircraftNumber(String aircraftNumber) {
		this.aircraftNumber = aircraftNumber;
	}
/**
 * 
 * @return name of status of flight
 */
	public String getStatus() {
		return status;
	}
/**
 * 
 * @param status new value
 */
	public void setStatus(String status) {
		this.status = status;
	}
/**
 * 
 * @return id of flight
 */
	public int getIdFlight() {
		return idFlight;
	}
/**
 * 
 * @param idFlight new value
 */
	public void setIdFlight(int idFlight) {
		this.idFlight = idFlight;
	}
/**
 * 
 * @return list of crew members
 */
	public List<Crew> getCrews() {
		return crews;
	}
/**
 * 
 * @param crews new list of crew members
 */
	public void setCrews(List<Crew> crews) {
		this.crews = crews;
	}
	/**
	 * @return hash code of object
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aircraftNumber == null) ? 0 : aircraftNumber.hashCode());
		result = prime * result + ((aircraftType == null) ? 0 : aircraftType.hashCode());
		result = prime * result + ((crews == null) ? 0 : crews.hashCode());
		result = prime * result + ((currentCity == null) ? 0 : currentCity.hashCode());
		result = prime * result + ((destinationCity == null) ? 0 : destinationCity.hashCode());
		result = prime * result + flightRange;
		result = prime * result + flightTime;
		result = prime * result + idFlight;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((timeDeparture == null) ? 0 : timeDeparture.hashCode());
		return result;
	}
	/**
     * @param obj - object with which is compared object this
     * @return true - if objects are equals and false otherwise
     */
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
		if (crews == null) {
			if (other.crews != null)
				return false;
		} else if (!crews.equals(other.crews))
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
		if (idFlight != other.idFlight)
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
	/**
	 * @return presentation of object as string
	 */
	@Override
	public String toString() {
		return "Flight [idFlight=" + idFlight + ", currentCity=" + currentCity + ", destinationCity=" + destinationCity
				+ ", flightRange=" + flightRange + ", flightTime=" + flightTime + ", timeDeparture=" + timeDeparture
				+ ", aircraftType=" + aircraftType + ", aircraftNumber=" + aircraftNumber + ", status=" + status
				+ ", crews=" + crews + "]";
	}

	
}
