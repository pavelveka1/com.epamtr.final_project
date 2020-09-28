package by.epamtr.airline.entity;

import java.io.Serializable;

public class Aircraft implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AircraftType type;
	private String registerNumber;
	private String status;

	public Aircraft(AircraftType type, String registerNumber, String status) {
		super();
		this.type = type;
		this.registerNumber = registerNumber;
		this.status = status;
	}

	public Aircraft() {

	}

	public AircraftType getType() {
		return type;
	}

	public void setType(AircraftType type) {
		this.type = type;
	}

	public String getRegisterNumber() {
		return registerNumber;
	}

	public void setRegisterNumber(String registerNumber) {
		this.registerNumber = registerNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getRangeFlight() {
		return type.getRangeFlight();
	}

	public void setRangeFlight(int rangeFlight) {
		this.type.setRangeFlight(rangeFlight);
	}

	public int getNumberPassenger() {
		return type.getNumberPassenger();
	}

	public void setNumberPassenger(int numberPassenger) {
		this.type.setNumberPassenger(numberPassenger);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((registerNumber == null) ? 0 : registerNumber.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Aircraft other = (Aircraft) obj;
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

	@Override
	public String toString() {
		return "Aircraft [type=" + type.getAircraftType() + " rangeFlight = " + type.getRangeFlight()
				+ " numberPassenger = " + type.getNumberPassenger() + ", registerNumber=" + registerNumber + ", status="
				+ status + "]";
	}

}
