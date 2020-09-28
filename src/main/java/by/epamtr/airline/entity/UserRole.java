package by.epamtr.airline.entity;

public enum UserRole {

	ADMINISTRATOR("Администратор"),
	DISPATCHER("Диспетчер"),
	MANAGER("Менеджер"),
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
