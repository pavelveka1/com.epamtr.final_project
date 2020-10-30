package by.epamtr.airline.entity;

public enum UserRole {

	
	MANAGER("MANAGER"),
	DISPATCHER("DISPATCHER"),
	ADMINISTRATOR("ADMINISTRATOR"),
	PILOT("PILOT"),
	ATTENDANT("ATTENDANT"),
	ENGINEER("ENGINEER");
	
	private String role;
	
	UserRole(String role){
		this.role=role;
	}

	public String getRole() {
		return role;
	}
	
	
}
