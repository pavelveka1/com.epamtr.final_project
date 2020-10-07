package by.epamtr.airline.entity;

import java.io.Serializable;

public class Aircraft implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idAircraft;
	private AircraftType type;
	private String registerNumber;
	private String status;

	public Aircraft(int idAircraft, AircraftType type, String registerNumber, String status) {
		super();
		this.idAircraft=idAircraft;
		this.type = type;
		this.registerNumber = registerNumber;
		this.status = status;
	}

	public Aircraft() {

	}

	public int getIdAircraft() {
		return idAircraft;
	}

	public void setIdAircraft(int idAircraft) {
		this.idAircraft = idAircraft;
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

	@Override
	public String toString() {
		return "Aircraft [idAircraft=" + idAircraft + ", type=" + type + ", registerNumber=" + registerNumber
				+ ", status=" + status + "]";
	}

}
