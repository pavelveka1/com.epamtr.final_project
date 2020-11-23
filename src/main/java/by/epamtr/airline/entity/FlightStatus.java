package by.epamtr.airline.entity;

/**
 * Enum flight's statuses
 * 
 * @author Pavel Veka
 *
 */
public enum FlightStatus {
	/**
	 * status for flight in which list of crew members is not completed
	 */
	RECRUITMENT("RECRUITMENT"),
	/**
	 * status for flight which ready for performing
	 */
	CREATED("CREATED"),
	/**
	 * status of flight which was performed
	 */
	COMPLITED("COMPLITED");

	/**
	 * name of status
	 */
	private String status;

	/**
	 * Constructor
	 * 
	 * @param status
	 */
	FlightStatus(String status) {
		this.status = status;
	}

	/**
	 * 
	 * @return name of status of flight
	 */
	public String getStatus() {
		return status;
	}
}
