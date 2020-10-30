package by.epamtr.airline.dao.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;

import by.epamtr.airline.dao.SQLQueryConstant;
import by.epamtr.airline.dao.SQLTableConstant;
import by.epamtr.airline.dao.UserDAO;
import by.epamtr.airline.dao.connection_pool.ConnectionPool;
import by.epamtr.airline.dao.connection_pool.exception.ConnectionPoolException;
import by.epamtr.airline.dao.connection_pool.impl.ConnectionPoolImpl;
import by.epamtr.airline.dao.exception.DAOException;
import by.epamtr.airline.entity.Crew;
import by.epamtr.airline.entity.User;
import by.epamtr.airline.entity.UserInfo;
import by.epamtr.airline.entity.UserRole;

public class SQLUserDAO implements UserDAO {

	private static final String PATH_TO_ADMIN_PAGE="/WEB-INF/jsp/administrator_page.jsp";
	private static final String PATH_TO_UPDATE_USER="/WEB-INF/jsp/administrator_action/update_user.jsp";
	
	private final String FLIGHT_ID_COLUMN = "flight_id";
	private final String CREW_POSITION_COLUMN = "crew_position";
	private final String LOGIN_PARAM = "login";
	
	private final String USER_ATTRIBUTE = "selected_user";
	private final String USER_INFO_ATTRIBUTE = "user_info";
	private final int ZERO_USER_OPERATION = 0;
	private final String CURRENT_PAGE="current_page";

	private ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

	@Override
	public User signIn(String login, String password) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		int idUser = 0;
		User user=null;
		String pass=criptPassword(password);
		try {
			connection = connectionPool.getConnection();
			try {
				statement = connection.prepareStatement(
						String.format(SQLQueryConstant.UserConstant.SIGN_IN_GET_ID, login, criptPassword(password)));
				rs = statement.executeQuery();
				password = null;
				if (rs.next()) {
					idUser = rs.getInt(SQLTableConstant.UserInfo.USER_ID);
					rs.close();
					rs = statement.executeQuery(String.format(SQLQueryConstant.UserConstant.SIGN_IN_GET_USER, idUser));
					rs.next();
				    user = createUser(rs);
				} 
			} catch (SQLException e) {
				throw new DAOException("Error while getting data from DB", e);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("error while getting connection", e);
		} finally {
			releaseResourses( statement,  rs, connection);
		}
		return user;
	}

	@Override
	public boolean addUser(User user, UserInfo userInfo) throws DAOException {
		Connection connection = null;
		PreparedStatement statementUser = null;
		PreparedStatement statementUserInfo = null;
		ResultSet rs = null;
		int idAddedUser=0;
		try {
			connection = connectionPool.getConnection();
			try {
				statementUser = connection.prepareStatement(String.format(SQLQueryConstant.UserConstant.ADD_USER_CHECK_LOGIN,
						userInfo.getLogin()));
				rs = statementUser.executeQuery();
				if (rs.next()) {
					statementUser.close();
					rs.close();
					return false;	
				} else {
					connection.setAutoCommit(false);
					statementUser = connection.prepareStatement(SQLQueryConstant.CONSTRAINT_DISABLE);
					statementUser.executeQuery();
					
					statementUser = connection.prepareStatement(SQLQueryConstant.UserConstant.ADD_USER_INSERT_IN_USERS, Statement.RETURN_GENERATED_KEYS);
					statementUser.setString(1, user.getName());
					statementUser.setString(2, user.getSurname());
					statementUser.setString(3, user.getPatronimic());
					statementUser.setString(4, user.getEmail());
					statementUser.setInt(5, defineRoleID(user.getRole()));
					int addedUsers = statementUser.executeUpdate();
					if(addedUsers!=0) {
						ResultSet generatedKeys = statementUser.getGeneratedKeys();
						generatedKeys.next();
						idAddedUser=generatedKeys.getInt(1);
					}

					statementUserInfo = connection.prepareStatement(SQLQueryConstant.UserConstant.ADD_USER_INSERT_IN_USERS_INFO);
					statementUserInfo.setInt(1, idAddedUser);
					statementUserInfo.setString(2, userInfo.getLogin());
					statementUserInfo.setString(3, criptPassword(userInfo.getPassword()));
					int addedInfo = statementUserInfo.executeUpdate();
					statementUserInfo = connection.prepareStatement(SQLQueryConstant.CONSTRAINT_ENABLE);
					connection.commit();
					connection.setAutoCommit(true);
					if (addedInfo != ZERO_USER_OPERATION && addedUsers != ZERO_USER_OPERATION) {
						return true;
					} else {
						return false;
					}
				}
			} catch (SQLException e) {
				throw new DAOException("error while adding user", e);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("error while getting connection from ConnectionPool", e);
		} finally {
			releaseResourses( statementUser, statementUserInfo,  rs, connection);
		}

	}

	@Override
	public boolean deliteUser(int idUser) throws DAOException {
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			connection = connectionPool.getConnection();
			try {
				statement = connection.createStatement();
				connection.setAutoCommit(false);
				statement.executeUpdate(SQLQueryConstant.CONSTRAINT_DISABLE);
				int deletedUsers = statement
						.executeUpdate(String.format(SQLQueryConstant.UserConstant.DELETE_USER_FROM_USERS_INFO, idUser));
				int deletedInfo = statement
						.executeUpdate(String.format(SQLQueryConstant.UserConstant.DELETE_USER_FROM_USERS, idUser));
				statement.executeUpdate(SQLQueryConstant.CONSTRAINT_ENABLE);
				connection.commit();
				connection.setAutoCommit(true);
				if (deletedUsers != ZERO_USER_OPERATION && deletedInfo != ZERO_USER_OPERATION) {
					return true;
				}
				return false;
			} catch (SQLException e) {
				throw new DAOException("erroe while deliting user", e);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("error while getting connection from ConnectionPool", e);
		} finally {
			releaseResourses( statement,  rs, connection);
		}
	}

	@Override
	public boolean updateUser(User user, UserInfo userInfo) throws DAOException {
		String name = user.getName();
		String surname = user.getSurname();
		String patronimic = user.getPatronimic();
		String email = user.getEmail();
		String login = userInfo.getLogin();
		String password = userInfo.getPassword();
		int role = defineRoleID(user.getRole());

		Connection connection = null;
		PreparedStatement statementUser = null;
		PreparedStatement statementUserInfo = null;

		try {
			connection = connectionPool.getConnection();
			
			try {
				connection.setAutoCommit(false);
				statementUser = connection.prepareStatement(String.format(SQLQueryConstant.UserConstant.UPDATE_USER, name,
						surname, patronimic, email, role, user.getIdUser()));
				int userRows = statementUser.executeUpdate();
				statementUserInfo = connection.prepareStatement(
						String.format(SQLQueryConstant.UserConstant.UPDATE_USER_INFO, login, criptPassword(password), user.getIdUser()));
				int userInfoRows=statementUserInfo.executeUpdate();
				connection.commit();
				connection.setAutoCommit(true);
				if (userRows != ZERO_USER_OPERATION && userInfoRows != ZERO_USER_OPERATION) {
					return true;
				} else {
					return false;
				}
			} catch (SQLException e) {
				throw new DAOException("error while updating user", e);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("error while getting connection from ConnectionPool", e);
		} finally {
			//releaseResourses( statement,  rs, connection);
			
			connectionPool.releaseConnection(connection);

			if (statementUser != null) {
				try {
					statementUser.close();
				} catch (SQLException e) {
					throw new DAOException("erroe while closing statement", e);
				}
			}
			if (statementUserInfo != null) {
				try {
					statementUserInfo.close();
				} catch (SQLException e) {
					throw new DAOException("erroe while closing statement", e);
				}
			}

		}
	}

	@Override
	public List<User> getUsers(UserRole role) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<User> users = new ArrayList<User>();
		try {
			connection = connectionPool.getConnection();
			try {
				statement = connection.prepareStatement(SQLQueryConstant.UserConstant.GET_USERS_BY_ROLE);
				rs = statement.executeQuery();
				while (rs.next()) {
					if (role == UserRole.valueOf(rs.getString("user_roles.user_role"))) {
						users.add(createUser(rs));
					}

				}

			} catch (SQLException e) {
				throw new DAOException("error while getting users by UserRole", e);
			}

		} catch (ConnectionPoolException e) {
			throw new DAOException("error while getting connection from ConnectionPool", e);
		} finally {
			releaseResourses( statement,  rs, connection);
		}

		return users;
	}

	@Override
	public List<Crew> getUsers(int idFlight) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<Crew> team = new ArrayList<Crew>();
		try {
			connection = connectionPool.getConnection();
			try {
				statement = connection
						.prepareStatement(String.format(SQLQueryConstant.UserConstant.GET_USERS_BY_FLIGHT_ID, idFlight));
				rs = statement.executeQuery();
				while (rs.next()) {
					team.add(createCrew(rs));
				}

			} catch (SQLException e) {
				throw new DAOException("error while getting users by UserRole", e);
			}

		} catch (ConnectionPoolException e) {
			throw new DAOException("error while getting connection from ConnectionPool", e);
		} finally {
			releaseResourses( statement,  rs, connection);
		}
		return team;
	}

	@Override
	public User getUser(int idUser) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		User user;
		try {
			connection = connectionPool.getConnection();
			try {
				statement = connection
						.prepareStatement(String.format(SQLQueryConstant.UserConstant.GET_USERS_BY_USER_ID, idUser));
				rs = statement.executeQuery();
				rs.next();
				user = createUser(rs);
			} catch (SQLException e) {
				throw new DAOException("error while getting users by UserRole", e);
			}

		} catch (ConnectionPoolException e) {
			throw new DAOException("error while getting connection from ConnectionPool", e);
		} finally {
			releaseResourses( statement,  rs, connection);
		}

		return user;
	}
	
	@Override
	public UserInfo getUserInfo(int idUser) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		UserInfo userInfo;
		try {
			connection = connectionPool.getConnection();
			try {
				statement = connection
						.prepareStatement(String.format(SQLQueryConstant.UserConstant.GET_USER_INFO_BY_ID, idUser));
				rs = statement.executeQuery();
				rs.next();
				userInfo = makeUserInfo(rs);
			} catch (SQLException e) {
				throw new DAOException("error while getting users by UserRole", e);
			}

		} catch (ConnectionPoolException e) {
			throw new DAOException("error while getting connection from ConnectionPool", e);
		} finally {
			releaseResourses( statement,  rs, connection);
		}

		return userInfo;
	}


	@Override
	public void deliteCrewFromFlight(int flightId, int userId) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			connection = connectionPool.getConnection();
			try {
				statement = connection.prepareStatement(
						String.format(SQLQueryConstant.UserConstant.DELETE_CREW_FROM_FLIGHT, flightId, userId));
				// connection.setAutoCommit(false);
				statement.executeUpdate();
				/*
				 * statement.executeUpdate(SQLConstant.CONSTRAINT_ENABLE); connection.commit();
				 * connection.setAutoCommit(true);
				 */
			} catch (SQLException e) {
				throw new DAOException("erroe while deliting user", e);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("error while getting connection from ConnectionPool", e);
		} finally {
			releaseResourses( statement,  rs, connection);
		}
	}

	@Override
	public void addCrewToFlight(int idCrewPosition, int flightId, int userId) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = connectionPool.getConnection();
			try {
				statement = connection.prepareStatement(SQLQueryConstant.CONSTRAINT_DISABLE);
				statement.executeQuery();
				statement = connection.prepareStatement(SQLQueryConstant.UserConstant.ADD_USER_TO_CREW_BY_FLIGHT_ID);
				statement.setInt(1, idCrewPosition);
				statement.setInt(2, flightId);
				statement.setInt(3, userId);
				int i = statement.executeUpdate();
				statement = connection.prepareStatement(SQLQueryConstant.CONSTRAINT_ENABLE);
				statement.executeQuery();

			} catch (SQLException e) {
				throw new DAOException("error while adding user to crew", e);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("error while getting connection from ConnectionPool", e);
		} finally {
			//*********************************************
			connectionPool.releaseConnection(connection);
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					throw new DAOException("erroe while closing statement", e);
				}
			}
		}
	}

	@Override
	public List<User> getFreeUsers(int flightId, String selectedPosition) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<User> users = new ArrayList<User>();
		try {
			connection = connectionPool.getConnection();
			try {
				statement = connection.prepareStatement(
						String.format(SQLQueryConstant.UserConstant.GET_FREE_USERS_BY_POSITION, flightId, selectedPosition));
				rs = statement.executeQuery();
				while (rs.next()) {
					users.add(createUser(rs));
				}

			} catch (SQLException e) {
				throw new DAOException("error while getting free users by crew position", e);
			}

		} catch (ConnectionPoolException e) {
			throw new DAOException("error while getting connection from ConnectionPool", e);
		} finally {
			releaseResourses( statement,  rs, connection);
		}
		return users;
	}



	private User createUser(ResultSet resultSet) throws SQLException {
		int idUser = resultSet.getInt(SQLTableConstant.User.ID_USER);
		String name = resultSet.getString(SQLTableConstant.User.NAME);
		String surname = resultSet.getString(SQLTableConstant.User.SURNAME);
		String patronimic = resultSet.getString(SQLTableConstant.User.PATRONIMIC);
		String email = resultSet.getString(SQLTableConstant.User.E_MAIL);
		UserRole userRole = UserRole.valueOf(resultSet.getString("user_roles.user_role"));
		return new User(idUser, name, surname, patronimic, email, userRole);
	}

	private Crew createCrew(ResultSet resultSet) throws SQLException {
		int idFlight = resultSet.getInt(FLIGHT_ID_COLUMN);
		User user = createUser(resultSet);
		String crewPosition = resultSet.getString(CREW_POSITION_COLUMN);
		Crew crew = new Crew(idFlight, user, crewPosition);
		return crew;
	}



	private String criptPassword(String password) {
		String criptedPassword = DigestUtils.md5Hex(password);
		return criptedPassword;
	}

	private UserInfo makeUserInfo(ResultSet resultSet) throws DAOException {
		int id = 0;
		String login = null;
		String password = null;
		try {
			id = resultSet.getInt(SQLTableConstant.UserInfo.USER_ID);
			login = resultSet.getString(SQLTableConstant.UserInfo.LOGIN);
			password = resultSet.getString(SQLTableConstant.UserInfo.PASSWORD);
		} catch (SQLException e) {
			throw new DAOException("Error while getting data from ResultSet", e);
		}
		return new UserInfo(id, login, password);
	}

	private User makeUser(ResultSet resultSet) throws DAOException {
		int id = 0;
		String name = null;
		String surname = null;
		String patronimic = null;
		String email = null;
		UserRole role = UserRole.MANAGER;
		try {
			id = resultSet.getInt(SQLTableConstant.User.ID_USER);
			name = resultSet.getString(SQLTableConstant.User.NAME);
			surname = resultSet.getString(SQLTableConstant.User.SURNAME);
			patronimic = resultSet.getString(SQLTableConstant.User.PATRONIMIC);
			email = resultSet.getString(SQLTableConstant.User.E_MAIL);
			int roleNumber = resultSet.getInt(SQLTableConstant.User.USER_ROLE);
			role = convertNumberToUserRole(roleNumber);

		} catch (SQLException e) {
			throw new DAOException("Error while getting data from ResultSet", e);
		}
		return new User(id, name, surname, patronimic, email, role);
	}
	
	private int defineRoleID(UserRole role) {
		switch(role) {
		case MANAGER:
			return 1;
		case ADMINISTRATOR:
			return 3;
		case DISPATCHER:
			return 2;
		case PILOT:
			return 4;
		case ATTENDANT:
			return 5;
		case ENGINEER:
			return 6;
		}
		return 1;
	}

	private UserRole convertNumberToUserRole(int roleNumber) {
		switch (roleNumber) {
		case 1:
			return UserRole.MANAGER;
		case 2:
			return UserRole.DISPATCHER;
		case 3:
			return UserRole.ADMINISTRATOR;
		case 4:
			return UserRole.PILOT;
		case 5:
			return UserRole.ATTENDANT;
		case 6:
			return UserRole.ENGINEER;
		}
		return UserRole.MANAGER;
	}
	
	private void releaseResourses(Statement statement, ResultSet resultSet, Connection connection) throws DAOException {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				throw new DAOException("erroe while closing resultSet", e);
			}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				throw new DAOException("erroe while closing statement", e);
			}
		}
		connectionPool.releaseConnection(connection);
	}
	
	private void releaseResourses(Statement statementUser, Statement statementUserInfo, ResultSet resultSet, Connection connection) throws DAOException {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				throw new DAOException("erroe while closing resultSet", e);
			}
		}
		if (statementUser != null) {
			try {
				statementUser.close();
			} catch (SQLException e) {
				throw new DAOException("erroe while closing statement", e);
			}
		}
		if (statementUserInfo != null) {
			try {
				statementUserInfo.close();
			} catch (SQLException e) {
				throw new DAOException("erroe while closing statement", e);
			}
		}
		connectionPool.releaseConnection(connection);
	}

	

}
