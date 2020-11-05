package by.epamtr.airline.dao;

/**
 * Enum statuses of aircraft
 * 
 * @author Pavel Veka
 *
 */
public enum AircraftStatusDAO {
	/**
	 * Status means that the aircraft is under scheduled maintenance
	 */
	MAINTENANCE("MAINTENANCE"),
	/**
	 * Status means that aircraft is able to use for flight
	 */
	SERVICEABLE("SERVICEABLE"),
	/**
	 * Status means that aircraft is inoperative
	 */
	UNSERVICEABLE("UNSERVICEABLE");

	/**
	 * Name of status
	 */
	private String status;

	/**
	 * Constructor
	 * 
	 * @param status
	 */
	AircraftStatusDAO(String status) {
		this.status = status;
	}

	/**
	 * 
	 * @return name of status
	 */
	public String getStatus() {
		return status;
	}
}
