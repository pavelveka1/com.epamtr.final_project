package by.epamtr.airline.entity;

import java.io.Serializable;
/**
 * Class entity of Aircraft
 *
 * @author Pavel Veka
 */
public class Aircraft implements Serializable {
	/**
	 * Serial fersion UID for serialization
	 */
	private static final long serialVersionUID = 1L;
	/**
     * Unique identifier of aircraft
     */
	private int idAircraft;
	/**
     * Type of aircraft
     */
	private AircraftType type;
	/**
     * Registration number of aircraft
     */
	private String registerNumber;
	/**
     * Status of aircraft
     */
	private String status;

	/**
     * Constructor of Aircraft with all parameters
     * @param idAircraft - unique identifier of aircraft
     * @param type - type of aircraft
     * @param registerNumber -  registration number of aircraft
     * @param status - status of aircraft
     */
	public Aircraft(int idAircraft, AircraftType type, String registerNumber, String status) {
		super();
		this.idAircraft=idAircraft;
		this.type = type;
		this.registerNumber = registerNumber;
		this.status = status;
	}
	/**
     * Constructor of Aircraft without parameters
     */
	public Aircraft() {

	}
	/**
     * @return id aircraft
     */
	public int getIdAircraft() {
		return idAircraft;
	}
	/**
     * @param new value of idAircraft
     */
	public void setIdAircraft(int idAircraft) {
		this.idAircraft = idAircraft;
	}
	/**
     * @return type of aircraft
     */
	public AircraftType getType() {
		return type;
	}
	/**
     * @param new value of type of aircraft
     */
	public void setType(AircraftType type) {
		this.type = type;
	}
	/**
     * @return registration number of aircraft
     */
	public String getRegisterNumber() {
		return registerNumber;
	}
	/**
     * @param new value of registration number
     */
	public void setRegisterNumber(String registerNumber) {
		this.registerNumber = registerNumber;
	}
	/**
     * @return status of aircraft
     */
	public String getStatus() {
		return status;
	}
	/**
     * @param new value of status of aircraft
     */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
     * @return hash code of object Aircraft
     */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idAircraft;
		result = prime * result + ((registerNumber == null) ? 0 : registerNumber.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Aircraft other = (Aircraft) obj;
		if (idAircraft != other.idAircraft)
			return false;
		if (registerNumber == null) {
			if (other.registerNumber != null)
				return false;
		} else if (!registerNumber.equals(other.registerNumber))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	/**
	 * @return presentation of object as string
	 */
	@Override
	public String toString() {
		return "Aircraft [idAircraft=" + idAircraft + ", type=" + type + ", registerNumber=" + registerNumber
				+ ", status=" + status + "]";
	}

}
