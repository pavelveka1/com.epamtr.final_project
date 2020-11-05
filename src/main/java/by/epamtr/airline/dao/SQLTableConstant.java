package by.epamtr.airline.dao;

public class SQLTableConstant {
	
	public static class Aircraft{
		public static final String ID_AIRCRAFT="id_aircraft";
		public static final String TYPE_AIRCRAFT="type_aircraft";
		public static final String REGISTRATION_NUMBER="registration_number";
		public static final String STATUS="status";
		
	}
	public static class AircraftType{
		public static final String ID_AIRCRAFT_TYPE="id_aircraft_type";
		public static final String AIRCRAFT_TYPE="aircraft_type";
		public static final String RANGE_FLIGHT="range_flight";
		public static final String NUMBER_PASSENGERS="number_passengers";
		
	}
	public static class Flight{
		public static final String ID_FLIGHT="id_flight";
		public static final String CURRENT_CITY="current_city";
		public static final String DESTINATION_CITY="destination_city";
		public static final String FLIGHT_RANGE="flight_range";
		public static final String FLIGHT_TIME="flight_time";
		public static final String DAY_AND_TIME_DEPARTURE="day_and_time_departure";
		public static final String AIRCRAFT="aircraft";
		public static final String FLIGHT_STATUS="flight_status";
		public static final String FLIGHT_ID = "flight_id";
	}
	
	public static class Crew{
		public static final String CREW_POSITION="crew_position";
		public static final String FLIGHT_ID="flight_id";
		public static final String CREW_USER="crew_user";
	}
	
	public static class CrewPosition{
		public static final String ID_CREW_POSITION="id_crew_position";
		public static final String CREW_POSITION="crew_position";
	}
	
	public static class User{
		public static final String ID_USER="id_user";
		public static final String NAME="name";
		public static final String SURNAME="surname";
		public static final String PATRONIMIC="patronimic";
		public static final String E_MAIL="e_mail";
		public static final String USER_ROLE="user_role";
	}
	
	public static class UserInfo{
		public static final String USER_ID="user_id";
		public static final String LOGIN="login";
		public static final String PASSWORD="password";
	}
	
	public static class UserRole{
		public static final String ID_USER_ROLE="id_user_role";
		public static final String USER_ROLE="user_role";
	}
	
	public static class JTCrewPositionUserRole{
		public static final String ID_CREW_POSITION="crew_position_id_crew_position";
		public static final String ID_USER_ROLE="user_roles_id_user_role";
	}
	
}
