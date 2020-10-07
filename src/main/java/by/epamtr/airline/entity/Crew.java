package by.epamtr.airline.entity;

import java.io.Serializable;

public class Crew implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idFlight;
	private User user;
	private String crewPosition;
	

	public Crew() {
		
	}
	

	public Crew(int idFlight, User user, String crewPosition) {
		super();
		this.idFlight = idFlight;
		this.user = user;
		this.crewPosition = crewPosition;
	}


	public int getIdFlight() {
		return idFlight;
	}


	public void setIdFlight(int idFlight) {
		this.idFlight = idFlight;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
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
		result = prime * result + idFlight;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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


	@Override
	public String toString() {
		return "Crew [idFlight=" + idFlight + ", user=" + user + ", crewPosition=" + crewPosition + "]";
	}


}
