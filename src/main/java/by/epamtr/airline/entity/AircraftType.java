package by.epamtr.airline.entity;

import java.io.Serializable;

public class AircraftType implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String aircraftType;
	private int rangeFlight;
	private int numberPassenger;

	public AircraftType(String aircraftType, int rangeFlight, int numberPassenger) {
		super();
		this.aircraftType = aircraftType;
		this.rangeFlight = rangeFlight;
		this.numberPassenger = numberPassenger;
	}

	public int getRangeFlight() {
		return rangeFlight;
	}

	public void setRangeFlight(int rangeFlight) {
		this.rangeFlight = rangeFlight;
	}

	public int getNumberPassenger() {
		return numberPassenger;
	}

	public void setNumberPassenger(int numberPassenger) {
		this.numberPassenger = numberPassenger;
	}

	public String getAircraftType() {
		return aircraftType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aircraftType == null) ? 0 : aircraftType.hashCode());
		result = prime * result + numberPassenger;
		result = prime * result + rangeFlight;
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
		AircraftType other = (AircraftType) obj;
		if (aircraftType == null) {
			if (other.aircraftType != null)
				return false;
		} else if (!aircraftType.equals(other.aircraftType))
			return false;
		if (numberPassenger != other.numberPassenger)
			return false;
		if (rangeFlight != other.rangeFlight)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AircraftType [aircraftType=" + aircraftType + ", rangeFlight=" + rangeFlight + ", numberPassenger="
				+ numberPassenger + "]";
	}

}
