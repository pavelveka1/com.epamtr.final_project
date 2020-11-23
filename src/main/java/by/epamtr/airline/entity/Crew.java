package by.epamtr.airline.entity;

import java.io.Serializable;

/**
 * Entity class Crew
 * 
 * @author Pavel Veka
 *
 */
public class Crew implements Serializable {
	/**
	 * Serial fersion UID for serialization
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Unique identifier of the Flight object to which this crew member belongs
	 */
	private int idFlight;
	/**
	 * User object which belongs to Flight object with id idFlight
	 */
	private User user;
	/**
	 * The position of the crew occupied by the user
	 */
	private String crewPosition;

	/**
	 * Constructor of Crew object without parameters
	 */
	public Crew() {

	}

	/**
	 * Constructor of Crew object with all parameters
	 * 
	 * @param idFlight     - id of flight
	 * @param user         - user object
	 * @param crewPosition - crew position for user on flight with id equals
	 *                     idFlifgt
	 */
	public Crew(int idFlight, User user, String crewPosition) {
		super();
		this.idFlight = idFlight;
		this.user = user;
		this.crewPosition = crewPosition;
	}

	/**
	 * 
	 * @retur id of flight
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
	 * @return User
	 */
	public User getUser() {
		return user;
	}

	/**
	 * 
	 * @param user new value
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * 
	 * @return name of crew position
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
		result = prime * result + idFlight;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Crew other = (Crew) obj;
		if (crewPosition == null) {
			if (other.crewPosition != null)
				return false;
		} else if (!crewPosition.equals(other.crewPosition))
			return false;
		if (idFlight != other.idFlight)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	/**
	 * @return presentation of object as string
	 */
	@Override
	public String toString() {
		return "Crew [idFlight=" + idFlight + ", user=" + user + ", crewPosition=" + crewPosition + "]";
	}

}
