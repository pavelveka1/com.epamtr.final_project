package by.epamtr.airline.dao;

public class SQLConstant {

	public static final String CONSTRAINT_DISABLE = "SET FOREIGN_KEY_CHECKS=0;";
	public static final String CONSTRAINT_ENABLE = "SET FOREIGN_KEY_CHECKS=1;";
	public static final String SAFE_UPDATE_DISABLE = "SET SQL_SAFE_UPDATES = 0;";
	public static final String SAFE_UPDATE_ENABLE = "SET SQL_SAFE_UPDATES = 1;";

	public static class UserConstant {
		public static final String SIGN_IN_GET_ID = "SELECT user_id FROM users_info where (login ='%s') and (password = '%s');";
		public static final String SIGN_IN_GET_USER = "SELECT * FROM users join user_roles on users.user_role = user_roles.id_user_role where id_user= %d;";

		public static final String ADD_USER_GET_MAX_ID = "select max(id_user) AS id_user from users";
		public static final String ADD_USER_INSERT_IN_USERS = "INSERT INTO `users` (`name`,`surname`,`patronimic`,`e_mail`,`user_role`) VALUES (?,?,?,?,?)";
		public static final String ADD_USER_INSERT_IN_USERS_INFO = "INSERT INTO `users_info` (`user_id`,`login`,`password`) VALUES (?,?,?)";
		public static final String ADD_USER_CHECK_LOGIN = "select login from users_info where login ='%s' ;";

		public static final String DELETE_USER_FROM_USERS_INFO = "DELETE FROM users WHERE id_user = (select users_info.user_id from users_info where login ='%s')";
		public static final String DELETE_USER_FROM_USERS = "DELETE FROM users_info WHERE login ='%s';";

		public static final String FIND_USER_INFO_BY_LOGIN = "select * from users_info where login ='%s' ;";
		public static final String FIND_USER_BY_ID = "select * from users where id_user = %d ;";

		public static final String UPDATE_USER_INFO = "UPDATE users_info SET login='%s', password='%s' WHERE user_id = %d;";
		public static final String UPDATE_USER = "UPDATE users SET name='%s', surname='%s', patronimic='%s',e_mail='%s', user_role = %d WHERE id_user = %d;";

		public static final String GET_USERS_BY_ROLE = " SELECT users.id_user, users.name, users.surname, users.patronimic, users.e_mail, user_roles.user_role  FROM users \r\n"
				+ "       JOIN user_roles ON user_roles.id_user_role = users.user_role ;";
		
		public static final String GET_USERS_BY_FLIGHT_ID=" SELECT users.id_user, users.name, users.surname, users.patronimic, users.e_mail, user_roles.user_role, crews.flight_id, crew_positions.crew_position  FROM users \r\n"
				+ "       JOIN user_roles ON user_roles.id_user_role = users.user_role"
				+ "       JOIN crews ON crews.crew_user_id = users.id_user"
				+ "       JOIN crew_positions ON crew_positions.id_crew_position = crews.crew_position"
				+ "       WHERE crews.flight_id = %d;";
		
		public static final String DELETE_CREW_FROM_FLIGHT="DELETE FROM crews WHERE flight_id ='%s' and crew_user_id = '%s';";
	}

	public static class FlightConstant {
		public static final String ADD_FLIGHT = " INSERT INTO flights (`current_city`, `destination_city`, `flight_range`, `flight_time`, `day_and_time_departure`, `aircraft`, `flight_status`) VALUES (?,?,?,?,?,?,?) ;";
		public static final String GET_FLIGHTS_BY_STATUS = "SELECT id_flight, current_city, destination_city, flight_range, flight_time, day_and_time_departure, aircraft_types.aircraft_type, aircrafts.registration_number, flight_status FROM flights\r\n"
				+ "   JOIN aircrafts ON aircrafts.id_aircraft = flights.aircraft\r\n"
				+ "   JOIN aircraft_types ON aircraft_types.id_aircraft_type = aircrafts.type_aircraft;";
public static final String GET_FLIGHTS_BY_ID = "SELECT id_flight, current_city, destination_city, flight_range, flight_time, day_and_time_departure, aircraft_types.aircraft_type, aircrafts.registration_number, flight_status FROM flights\r\n"
		+ "   JOIN aircrafts ON aircrafts.id_aircraft = flights.aircraft\r\n"
		+ "   JOIN aircraft_types ON aircraft_types.id_aircraft_type = aircrafts.type_aircraft WHERE id_flight= %d ;";
		public static final String GET_USERS_BY_ROLE = " SELECT users.id_user, users.name, users.surname, users.patronimic, users.e_mail, user_roles.user_role  FROM users \r\n"
				+ "       JOIN user_roles ON user_roles.id_user_role = users.user_role ;";

	}

	public static class AircraftConstant {
		public static final String GET_AIRCRAFT_TYPES = " SELECT * FROM aircraft_types ;";
		public static final String ADD_AIRCRAFT_TYPES = " INSERT INTO aircraft_types (`aircraft_type`, `range_flight`, `number_passengers`) VALUES (?,?,?) ;";
		public static final String DELETE_AIRCRAFT_TYPE = "DELETE FROM aircraft_types WHERE id_aircraft_type =%d AND id_aircraft_type NOT IN (SELECT type_aircraft FROM aircrafts);";
		public static final String ADD_AIRCRAFT = " INSERT INTO aircrafts (`type_aircraft`, `registration_number`, `status`) VALUES (?,?,?) ;";
		public static final String GET_REGISTER_NUMBERS = " SELECT registration_number FROM aircrafts ;";
		public static final String DELETE_AIRCRAFT = " DELETE FROM aircrafts WHERE registration_number='%s' AND id_aircraft NOT IN (SELECT aircraft FROM flights);";
		public static final String UPDATE_AIRCRAFT = "UPDATE aircrafts SET aircrafts.registration_number='%s' WHERE id_aircraft = (select id_aircraft where aircrafts.registration_number = '%s');";
		public static final String CHANGE_STATUS_AIRCRAFT = "UPDATE aircrafts SET status='%s' WHERE id_aircraft = (select id_aircraft where aircrafts.registration_number = '%s');";
		public static final String GET_AIRCRAFTS = "SELECT id_aircraft, registration_number, status, id_aircraft_type, aircraft_type, range_flight, number_passengers FROM aircrafts\r\n"
				+ "     JOIN aircraft_types ON aircraft_types.id_aircraft_type = aircrafts.id_aircraft;";
	}

}
