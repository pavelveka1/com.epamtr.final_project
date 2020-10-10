package by.epamtr.airline.dao;

public enum AircraftStatusDAO {
	MAINTENANCE("MAINTENANCE"),
	SERVICEABLE("SERVICEABLE"),
	UNSERVICEABLE("UNSERVICEABLE");
	
private String status;
	
	AircraftStatusDAO(String status){
		this.status=status;
	}

	public String getStatus() {
		return status;
	}
}
