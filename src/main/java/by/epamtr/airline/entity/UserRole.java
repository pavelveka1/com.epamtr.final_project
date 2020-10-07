package by.epamtr.airline.entity;

public enum UserRole {

	
	MANAGER("Менеджер"),
	DISPATCHER("Диспетчер"),
	ADMINISTRATOR("Администратор"),
	PILOT("Пилот"),
	FLIGHT_ATTENDANT("Бортпроводник"),
	ENGINEER("Инженер");
	
	private String role;
	
	UserRole(String role){
		this.role=role;
	}

	public String getRole() {
		return role;
	}
	
	
}
