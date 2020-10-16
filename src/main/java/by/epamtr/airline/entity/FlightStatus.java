package by.epamtr.airline.entity;

public enum FlightStatus {
	RECRUITMENT("RECRUITMENT"),
	CREATED("CREATED"),
	COMPLITED("COMPLITED");
	
	
	
	private String status;
	
	FlightStatus(String status){
		this.status=status;
	}

	public String getStatus() {
		return status;
	}
}
