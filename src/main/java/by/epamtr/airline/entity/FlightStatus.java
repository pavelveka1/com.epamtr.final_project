package by.epamtr.airline.entity;

public enum FlightStatus {
	STAFF_RECRUITMENT("STAFF RECRUITMENT"),
	CREATED("CREATED"),
	COMPLETED("COMPLETED");
	
	
	
	private String status;
	
	FlightStatus(String status){
		this.status=status;
	}

	public String getStatus() {
		return status;
	}
}
