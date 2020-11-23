package by.epamtr.airline.entity;

import java.io.Serializable;

/**
 * Entity class CrewPosition
 * 
 * @author Pavel Veka
 *
 */
public class CrewPosition implements Serializable {
	/**
	 * Serial version UID for serialization
	 */
	private static final long serialVersionUID = 2499465428304686474L;
	/**
	 * unique identifier for position of crew
	 */
	private int idCrewPosition;
	/**
	 * name of position of crew
	 */
	private String crewPosition;

	/**
	 * Constructor of CrewPosition with all parameters
	 * 
	 * @param idCrewPosition
	 * @param crewPosition
	 */
	public CrewPosition(int idCrewPosition, String crewPosition) {
		super();
		this.idCrewPosition = idCrewPosition;
		this.crewPosition = crewPosition;
	}

	/**
	 * Constructor of CrewPosition without parameters
	 */
	public CrewPosition() {

	}

	/**
	 * 
	 * @return id of position of crew
	 */
	public int getIdCrewPosition() {
		return idCrewPosition;
	}

	/**
	 * 
	 * @param idCrewPosition new value
	 */
	public void setIdCrewPosition(int idCrewPosition) {
		this.idCrewPosition = idCrewPosition;
	}

	/**
	 * 
	 * @return name of position of crew
	 */
	public String getCrewPosition() {
		return crewPosition;
	}

	/**
	 * 
	 * @param crewPosition new value
	 */
	public void setCrewPosition(String crewPosition) {
		this.crewPosition = crewPosition;
	}

	/**
	 * @return hash code of object
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((crewPosition == null) ? 0 : crewPosition.hashCode());
		result = prime * result + idCrewPosition;
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

	/**
	 * @return presentation of object as string
	 */
	@Override
	public String toString() {
		return "CrewPosition [idCrewPosition=" + idCrewPosition + ", crewPosition=" + crewPosition + "]";
	}

}
