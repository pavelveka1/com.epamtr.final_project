package by.epamtr.airline.dao;

public class SQLConstant {
	 
	public static class UserConstant{
		public static final String SIGN_IN_GET_ID="SELECT user_id FROM users_info where (login ='%s') and (password = '%s');";
		public static final String SIGN_IN_GET_USER="SELECT * FROM users join user_roles on users.user_role = user_roles.id_user_role where id_user= %d;";
		public static final String ADD_USER_GET_MAX_ID="select max(id_user) AS id_user from users";
		public static final String ADD_USER_INSERT_IN_USERS="INSERT INTO `users` (`name`,`surname`,`patronimic`,`e_mail`,`user_role`) VALUES (?,?,?,?,?)";
		public static final String ADD_USER_INSERT_IN_USERS_INFO="INSERT INTO `users_info` (`user_id`,`login`,`password`) VALUES (?,?,?)";
		public static final String DELETE_USER_FROM_USERS_INFO="DELETE FROM users WHERE id_user = (select users_info.user_id from users_info where login ='%s')";
		public static final String DELETE_USER_FROM_USERS="DELETE FROM users_info WHERE login ='%s'";
	
	}

}
