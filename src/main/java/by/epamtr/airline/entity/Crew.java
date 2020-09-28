package by.epamtr.airline.entity;

import java.io.Serializable;

public class Crew implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User FirstPilot;
	private User SecondPilot;
	private User TraineePilot;
	private User FirstFlightAttendant;
	private User SecondFlightAttendant;
	private User ThirdFlightAttendant;
	private User FourthFlightAttendant;
	private User FifthFlightAttendant;
	private User EngineerAvionic;
	private User EngineerMechanic;

	public Crew(User firstPilot, User secondPilot, User traineePilot, User firstFlightAttendant,
			User secondFlightAttendant, User thirdFlightAttendant, User fourthFlightAttendant,
			User fifthFlightAttendant, User engineerAvionic, User engineerMechanic) {
		super();
		FirstPilot = firstPilot;
		SecondPilot = secondPilot;
		TraineePilot = traineePilot;
		FirstFlightAttendant = firstFlightAttendant;
		SecondFlightAttendant = secondFlightAttendant;
		ThirdFlightAttendant = thirdFlightAttendant;
		FourthFlightAttendant = fourthFlightAttendant;
		FifthFlightAttendant = fifthFlightAttendant;
		EngineerAvionic=engineerAvionic;
		EngineerMechanic=engineerMechanic;
	}

	public User getFirstPilot() {
		return FirstPilot;
	}

	public void setFirstPilot(User firstPilot) {
		FirstPilot = firstPilot;
	}

	public User getSecondPilot() {
		return SecondPilot;
	}

	public void setSecondPilot(User secondPilot) {
		SecondPilot = secondPilot;
	}

	public User getTraineePilot() {
		return TraineePilot;
	}

	public void setTraineePilot(User traineePilot) {
		TraineePilot = traineePilot;
	}

	public User getFirstFlightAttendant() {
		return FirstFlightAttendant;
	}

	public void setFirstFlightAttendant(User firstFlightAttendant) {
		FirstFlightAttendant = firstFlightAttendant;
	}

	public User getSecondFlightAttendant() {
		return SecondFlightAttendant;
	}

	public void setSecondFlightAttendant(User secondFlightAttendant) {
		SecondFlightAttendant = secondFlightAttendant;
	}

	public User getThirdFlightAttendant() {
		return ThirdFlightAttendant;
	}

	public void setThirdFlightAttendant(User thirdFlightAttendant) {
		ThirdFlightAttendant = thirdFlightAttendant;
	}

	public User getFourthFlightAttendant() {
		return FourthFlightAttendant;
	}

	public void setFourthFlightAttendant(User fourthFlightAttendant) {
		FourthFlightAttendant = fourthFlightAttendant;
	}

	public User getFifthFlightAttendant() {
		return FifthFlightAttendant;
	}

	public void setFifthFlightAttendant(User fifthFlightAttendant) {
		FifthFlightAttendant = fifthFlightAttendant;
	}

	public User getEngineerAvionic() {
		return EngineerAvionic;
	}

	public void setEngineerAvionic(User engineerAvionic) {
		EngineerAvionic = engineerAvionic;
	}

	public User getEngineerMechanic() {
		return EngineerMechanic;
	}

	public void setEngineerMechanic(User engineerMechanic) {
		EngineerMechanic = engineerMechanic;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((EngineerAvionic == null) ? 0 : EngineerAvionic.hashCode());
		result = prime * result + ((EngineerMechanic == null) ? 0 : EngineerMechanic.hashCode());
		result = prime * result + ((FifthFlightAttendant == null) ? 0 : FifthFlightAttendant.hashCode());
		result = prime * result + ((FirstFlightAttendant == null) ? 0 : FirstFlightAttendant.hashCode());
		result = prime * result + ((FirstPilot == null) ? 0 : FirstPilot.hashCode());
		result = prime * result + ((FourthFlightAttendant == null) ? 0 : FourthFlightAttendant.hashCode());
		result = prime * result + ((SecondFlightAttendant == null) ? 0 : SecondFlightAttendant.hashCode());
		result = prime * result + ((SecondPilot == null) ? 0 : SecondPilot.hashCode());
		result = prime * result + ((ThirdFlightAttendant == null) ? 0 : ThirdFlightAttendant.hashCode());
		result = prime * result + ((TraineePilot == null) ? 0 : TraineePilot.hashCode());
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
		if (EngineerAvionic == null) {
			if (other.EngineerAvionic != null)
				return false;
		} else if (!EngineerAvionic.equals(other.EngineerAvionic))
			return false;
		if (EngineerMechanic == null) {
			if (other.EngineerMechanic != null)
				return false;
		} else if (!EngineerMechanic.equals(other.EngineerMechanic))
			return false;
		if (FifthFlightAttendant == null) {
			if (other.FifthFlightAttendant != null)
				return false;
		} else if (!FifthFlightAttendant.equals(other.FifthFlightAttendant))
			return false;
		if (FirstFlightAttendant == null) {
			if (other.FirstFlightAttendant != null)
				return false;
		} else if (!FirstFlightAttendant.equals(other.FirstFlightAttendant))
			return false;
		if (FirstPilot == null) {
			if (other.FirstPilot != null)
				return false;
		} else if (!FirstPilot.equals(other.FirstPilot))
			return false;
		if (FourthFlightAttendant == null) {
			if (other.FourthFlightAttendant != null)
				return false;
		} else if (!FourthFlightAttendant.equals(other.FourthFlightAttendant))
			return false;
		if (SecondFlightAttendant == null) {
			if (other.SecondFlightAttendant != null)
				return false;
		} else if (!SecondFlightAttendant.equals(other.SecondFlightAttendant))
			return false;
		if (SecondPilot == null) {
			if (other.SecondPilot != null)
				return false;
		} else if (!SecondPilot.equals(other.SecondPilot))
			return false;
		if (ThirdFlightAttendant == null) {
			if (other.ThirdFlightAttendant != null)
				return false;
		} else if (!ThirdFlightAttendant.equals(other.ThirdFlightAttendant))
			return false;
		if (TraineePilot == null) {
			if (other.TraineePilot != null)
				return false;
		} else if (!TraineePilot.equals(other.TraineePilot))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Crew [FirstPilot=" + FirstPilot + ", SecondPilot=" + SecondPilot + ", TraineePilot=" + TraineePilot
				+ ", FirstFlightAttendant=" + FirstFlightAttendant + ", SecondFlightAttendant=" + SecondFlightAttendant
				+ ", ThirdFlightAttendant=" + ThirdFlightAttendant + ", FourthFlightAttendant=" + FourthFlightAttendant
				+ ", FifthFlightAttendant=" + FifthFlightAttendant + ", EngineerAvionic=" + EngineerAvionic
				+ ", EngineerMechanic=" + EngineerMechanic + "]";
	}

	
}
