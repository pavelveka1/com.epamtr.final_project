package by.epamtr.airline.entity;

import java.io.Serializable;

public class CrewPosition implements Serializable {

	private static final long serialVersionUID = 2499465428304686474L;

	private int idCrewPosition;
	private String crewPosition;
	
	public CrewPosition(int idCrewPosition, String crewPosition) {
		super();
		this.idCrewPosition = idCrewPosition;
		this.crewPosition = crewPosition;
	}
	
	public CrewPosition() {
		
	}

	public int getIdCrewPosition() {
		return idCrewPosition;
	}

	public void setIdCrewPosition(int idCrewPosition) {
		this.idCrewPosition = idCrewPosition;
	}

	public String getCrewPosition() {
		return crewPosition;
	}

	public void setCrewPosition(String crewPosition) {
		this.crewPosition = crewPosition;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((crewPosition == null) ? 0 : crewPosition.hashCode());
		result = prime * result + idCrewPosition;
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
		CrewPosition other = (CrewPosition) obj;
		if (crewPosition == null) {
			if (other.crewPosition != null)
				return false;
		} else if (!crewPosition.equals(other.crewPosition))
			return false;
		if (idCrewPosition != other.idCrewPosition)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CrewPosition [idCrewPosition=" + idCrewPosition + ", crewPosition=" + crewPosition + "]";
	}
	
	
	
}
