package by.epamtr.airline.dao;

public enum FlightStatusDAO {
	STAFF_RECRUITMENT("STAFF RECRUITMENT"),
	CREATED("CREATED"),
	COMPLETED("COMPLETED");
	
	
	
	private String status;
	
	FlightStatusDAO(String status){
		this.status=status;
	}

	public String getStatus() {
		return status;
	}
}
