package by.epamtr.airline.entity;

/**
 * Enum roles of user
 * 
 * @author Pavel Veka
 *
 */
public enum UserRole {

	/**
	 * Role for manager of airline company
	 */
	MANAGER("MANAGER"),
	/**
	 * Role for dispatcher who form crews for flights
	 */
	DISPATCHER("DISPATCHER"),
	/**
	 * Role for administrator.He has all rights
	 */
	ADMINISTRATOR("ADMINISTRATOR"),
	/**
	 * Role for pilots of airline
	 */
	PILOT("PILOT"),
	/**
	 * Role for flight attendants
	 */
	ATTENDANT("ATTENDANT"),
	/**
	 * Role for engineers
	 */
	ENGINEER("ENGINEER");

	/**
	 * Name of role
	 */
	private String role;

	/**
	 * Constructor
	 * 
	 * @param role
	 */
	UserRole(String role) {
		this.role = role;
	}

	/**
	 * 
	 * @return name of role
	 */
	public String getRole() {
		return role;
	}

}
