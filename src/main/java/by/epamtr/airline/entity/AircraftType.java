package by.epamtr.airline.entity;

import java.io.Serializable;
/**
 * Class entity of AircraftType 
 *
 * @author Pavel Veka
 */
public class AircraftType implements Serializable {
	/**
	 * Serial version UID for serialization
	 */
	private static final long serialVersionUID = 1L;
	/**
     * Unique identifier of type of aircraft
     */
	private int idAircraftType;
	/**
     * Name of type of aircraft
     */
	private String aircraftType;
	/**
     * Range of flight for type of aircraft
     */
	private int rangeFlight;
	/**
     * number passenger for type of aircraft
     */
	private int numberPassenger;

	/**
     * Constructor of AircraftType with all parameters
     * @param idAircraftType - identifier of type of aircraft
     * @param aircraftType - name of type of aircraft
     * @paramrangeFlight - range of flight of type of aircraft
     * @param numberPassenger - number of passenger for type of aircraft
     */
	public AircraftType(int idAircraftType, String aircraftType, int rangeFlight, int numberPassenger) {
		super();
		this.idAircraftType=idAircraftType;
		this.aircraftType = aircraftType;
		this.rangeFlight = rangeFlight;
		this.numberPassenger = numberPassenger;
	}
	/**
     * Constructor of AircraftType without parameters
     */
	public AircraftType() {
		
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
 * @return range of flight
 */
	public int getRangeFlight() {
		return rangeFlight;
	}
/**
 * 
 * @param rangeFlight new value
 */
	public void setRangeFlight(int rangeFlight) {
		this.rangeFlight = rangeFlight;
	}
/**
 * 
 * @return number of passengers
 */
	public int getNumberPassenger() {
		return numberPassenger;
	}
/**
 * 
 * @param numberPassenger new value
 */
	public void setNumberPassenger(int numberPassenger) {
		this.numberPassenger = numberPassenger;
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
 * @return unique identifier oftype of aircraft
 */
	public int getIdAircraftType() {
		return idAircraftType;
	}
/**
 * 
 * @param idAircraftType new value
 */
	public void setIdAircraftType(int idAircraftType) {
		this.idAircraftType = idAircraftType;
	}
/**
 * @return hash code of object
 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aircraftType == null) ? 0 : aircraftType.hashCode());
		result = prime * result + idAircraftType;
		result = prime * result + numberPassenger;
		result = prime * result + rangeFlight;
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
		AircraftType other = (AircraftType) obj;
		if (aircraftType == null) {
			if (other.aircraftType != null)
				return false;
		} else if (!aircraftType.equals(other.aircraftType))
			return false;
		if (idAircraftType != other.idAircraftType)
			return false;
		if (numberPassenger != other.numberPassenger)
			return false;
		if (rangeFlight != other.rangeFlight)
			return false;
		return true;
	}
	/**
	 * @return presentation of object as string
	 */
	@Override
	public String toString() {
		return "AircraftType [idAircraftType=" + idAircraftType + ", aircraftType=" + aircraftType + ", rangeFlight="
				+ rangeFlight + ", numberPassenger=" + numberPassenger + "]";
	}

	

}
