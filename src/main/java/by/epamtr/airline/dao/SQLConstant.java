package by.epamtr.airline.dao;

public class SQLConstant {
	
	public static final String CONSTRAINT_DISABLE="SET FOREIGN_KEY_CHECKS=0;";
	public static final String CONSTRAINT_ENABLE="SET FOREIGN_KEY_CHECKS=1;";
	 
	public static class UserConstant{
		public static final String SIGN_IN_GET_ID="SELECT user_id FROM users_info where (login ='%s') and (password = '%s');";
		public static final String SIGN_IN_GET_USER="SELECT * FROM users join user_roles on users.user_role = user_roles.id_user_role where id_user= %d;";
		
		public static final String ADD_USER_GET_MAX_ID="select max(id_user) AS id_user from users";
		public static final String ADD_USER_INSERT_IN_USERS="INSERT INTO `users` (`name`,`surname`,`patronimic`,`e_mail`,`user_role`) VALUES (?,?,?,?,?)";
		public static final String ADD_USER_INSERT_IN_USERS_INFO="INSERT INTO `users_info` (`user_id`,`login`,`password`) VALUES (?,?,?)";
		public static final String ADD_USER_CHECK_LOGIN="select login from users_info where login ='%s' ;";
		
		public static final String DELETE_USER_FROM_USERS_INFO="DELETE FROM users WHERE id_user = (select users_info.user_id from users_info where login ='%s')";
		public static final String DELETE_USER_FROM_USERS="DELETE FROM users_info WHERE login ='%s';";
		
		public static final String FIND_USER_INFO_BY_LOGIN="select * from users_info where login ='%s' ;";
		public static final String FIND_USER_BY_ID="select * from users where id_user = %d ;";
		
		public static final String UPDATE_USER_INFO="UPDATE users_info SET login='%s', password='%s' WHERE user_id = %d;";
		public static final String UPDATE_USER="UPDATE users SET name='%s', surname='%s', patronimic='%s',e_mail='%s', user_role = %d WHERE id_user = %d;";
	
		public static final String GET_USERS_BY_ROLE=" SELECT users.id_user, users.name, users.surname, users.patronimic, users.e_mail, user_roles.user_role  FROM users \r\n" + 
				"       JOIN user_roles ON user_roles.id_user_role = users.user_role ;";
	}
	
	public static class FlightConstant{
		public static final String GET_FLIGHTS_BY_ROLE=" SELECT users.id_user, users.name, users.surname, users.patronimic, users.e_mail, user_roles.user_role  FROM users \r\n" + 
				"       JOIN user_roles ON user_roles.id_user_role = users.user_role ;";
		
	}
	
	
	public static class AircraftConstant{
		public static final String GET_AIRCRAFT_TYPES=" SELECT * FROM aircraft_types ;";
		public static final String ADD_AIRCRAFT_TYPES=" INSERT INTO aircraft_types (`aircraft_type`, `range_flight`, `number_passengers`) VALUES (?,?,?) ;";
		public static final String DELETE_AIRCRAFT_TYPE="DELETE FROM aircraft_types WHERE id_aircraft_type =%d AND id_aircraft_type NOT IN (SELECT type_aircraft FROM aircrafts);";
		
		
		
	}

}
