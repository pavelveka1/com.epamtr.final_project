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

import by.epamtr.airline.dao.SQLConstant;
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

	private final String GO_TO_MANAGER_PAGE_COMMAND = "/Controller?command=GO_TO_MANAGER_PAGE";
	private final String GO_TO_DISPATCHER_PAGE_COMMAND = "/Controller?command=GO_TO_DISPATCHER_PAGE";
	private final String GO_TO_ADMINISTRATOR_PAGE_COMMAND = "/Controller?command=GO_TO_ADMINISTRATOR_PAGE";
	private final String GO_TO_CREW_PAGE_COMMAND = "/Controller?command=GO_TO_CREW_PAGE";
	private static final String PATH_TO_ADMIN_PAGE="/WEB-INF/jsp/administrator_page.jsp";
	private static final String PATH_TO_UPDATE_USER="/WEB-INF/jsp/administrator_action/update_user.jsp";

	private final String ADMINISTRATOR = "ADMINISTRATOR";
	private final String DISPATCHER = "DISPATCHER";
	private final String MANAGER = "MANAGER";
	private final String PILOT = "PILOT";
	private final String FLIGHT_ATTENDANT = "FLIGHT ATTENDANT";
	private final String ENGINEER = "ENGINEER";

	private final String LOGIN_COLUMN = "login";
	private final String PASSWORD_COLUMN = "password";
	private final String NAME_COLUMN = "name";
	private final String SURNAME_COLUMN = "surname";
	private final String PATRONIMIC_COLUMN = "patronimic";
	private final String EMAIL_COLUMN = "e_mail";
	private final String ROLE_COLUMN = "user_role";
	private final String USER_ID_COLUMN = "user_id";
	private final String ID_USER_COLUMN = "id_user";
	private final String FLIGHT_ID_COLUMN = "flight_id";
	private final String CREW_POSITION_COLUMN = "crew_position";
	

	private final String USER_ATTRIBUTE = "selected_user";
	private final String SIGNED_IN_USER_ATTRIBUTE = "user";
	private final String USER_INFO_ATTRIBUTE = "user_info";

	private final String PASSWORD_PARAM = "password";
	private final String LOGIN_PARAM = "login";
	private final String EMAIL_PARAM = "email";
	private final String NAME_PARAM = "name";
	private final String SURNAME_PARAM = "surname";
	private final String PATRONIMIC_PARAM = "patronimic";
	private final String ROLE_PARAM = "role";

	private final String RESULT_ATTR = "result_attr";
	private final String SUCCESSFUL_OPERATION = "success";
	private final String FAILED_OPERATION = "fail";
	private final String SIGN_IN_FAIL_ATTR="sign_in_fail_attr";
	private final String SIGN_IN_FAIL="sign_in_fail";
	private final int ZERO_USER_OPERATION = 0;
	
	private final String CURRENT_PAGE="current_page";
	
	private final String FLIGHTS_BY_STATUS_PAGE="/WEB-INF/jsp/user_action/get_flights_by_status.jsp";

	private final String PATH_TO_CONTROLLER = "/Controller?command=GO_TO_LOGIN_PAGE";
	private final String PATH_TO_ADD_USER_PAGE = "/WEB-INF/jsp/administrator_action/add_user.jsp";
	private final String PATH_TO_UPDATE_USER_PAGE = "/WEB-INF/jsp/administrator_action/update_user.jsp";

	private ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

	@Override
	public void signIn(HttpServletRequest request, HttpServletResponse response) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		int idUser = 0;
		String login = request.getParameter(LOGIN_COLUMN);
		String password = request.getParameter(PASSWORD_COLUMN);

		try {
			connection = connectionPool.getConnection();

			try {
				statement = connection.prepareStatement(
						String.format(SQLConstant.UserConstant.SIGN_IN_GET_ID, login, criptPassword(password)));
				rs = statement.executeQuery();
				password = null;
				if (rs.next()) {
					idUser = rs.getInt(USER_ID_COLUMN);
					rs.close();
					rs = statement.executeQuery(String.format(SQLConstant.UserConstant.SIGN_IN_GET_USER, idUser));
					rs.next();
					User user = createUser(rs);
					request.getSession().setAttribute(SIGNED_IN_USER_ATTRIBUTE, user);
					request.getSession().setAttribute(CURRENT_PAGE, FLIGHTS_BY_STATUS_PAGE);
					sendCommandToController(user.getRole(), request, response);
				} else {
					try {
						request.setAttribute(SIGN_IN_FAIL_ATTR, SIGN_IN_FAIL);
						request.getRequestDispatcher(PATH_TO_CONTROLLER).forward(request, response);
					} catch (ServletException | IOException e) {
						throw new DAOException("error while forward to mapping /Controller", e);
					}
				}
			} catch (SQLException e) {
				throw new DAOException("Error while getting data from DB", e);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("error while getting connection", e);
		} finally {
			releaseResourses( statement,  rs, connection);
		}
	}

	@Override
	public void signOut(HttpServletRequest request, HttpServletResponse response) throws DAOException {
		try {
			request.getSession().invalidate();
			request.getRequestDispatcher(PATH_TO_CONTROLLER).forward(request, response);
		} catch (ServletException | IOException e) {
			throw new DAOException("error while going to mapping /Controller in signOut method", e);
		}
	}

	@Override
	public void addUser(HttpServletRequest request, HttpServletResponse response) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			connection = connectionPool.getConnection();
			try {
				statement = connection.prepareStatement(String.format(SQLConstant.UserConstant.ADD_USER_CHECK_LOGIN,
						request.getParameter(LOGIN_COLUMN)));
				rs = statement.executeQuery();
				if (rs.next()) {
					try {
						request.getSession().setAttribute(RESULT_ATTR, FAILED_OPERATION);
						request.getRequestDispatcher(PATH_TO_ADD_USER_PAGE).forward(request, response);
						statement.close();
						rs.close();
					} catch (ServletException | IOException e) {
						throw new DAOException("Error while going to add_user.js");
					}
				} else {
					statement = connection.prepareStatement(SQLConstant.UserConstant.ADD_USER_GET_MAX_ID);
					rs = statement.executeQuery();
					rs.next();
					int maxIdUser = rs.getInt(ID_USER_COLUMN);
					statement.close();
					rs.close();
					connection.setAutoCommit(false);
					statement = connection.prepareStatement(SQLConstant.CONSTRAINT_DISABLE);
					statement.executeQuery();

					statement = connection.prepareStatement(SQLConstant.UserConstant.ADD_USER_INSERT_IN_USERS);
					statement.setString(1, request.getParameter(NAME_COLUMN));
					statement.setString(2, request.getParameter(SURNAME_COLUMN));
					statement.setString(3, request.getParameter(PATRONIMIC_COLUMN));
					statement.setString(4, request.getParameter(EMAIL_PARAM));
					statement.setInt(5, defineRole(request.getParameter(ROLE_PARAM)));
					int addedUsers = statement.executeUpdate();

					statement = connection.prepareStatement(SQLConstant.UserConstant.ADD_USER_INSERT_IN_USERS_INFO);
					statement.setInt(1, ++maxIdUser);
					statement.setString(2, request.getParameter(LOGIN_COLUMN));

					statement.setString(3, criptPassword(request.getParameter(PASSWORD_COLUMN)));
					int addedInfo = statement.executeUpdate();
					statement = connection.prepareStatement(SQLConstant.CONSTRAINT_ENABLE);
					connection.commit();
					connection.setAutoCommit(true);
					if (addedInfo != ZERO_USER_OPERATION && addedUsers != ZERO_USER_OPERATION) {
						request.setAttribute(RESULT_ATTR, SUCCESSFUL_OPERATION);
					} else {
						request.setAttribute(RESULT_ATTR, FAILED_OPERATION);
					}
				}
			} catch (SQLException e) {
				throw new DAOException("error while adding user", e);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("error while getting connection from ConnectionPool", e);
		} finally {
			releaseResourses( statement,  rs, connection);
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
				statement.executeUpdate(SQLConstant.CONSTRAINT_DISABLE);
				int deletedUsers = statement
						.executeUpdate(String.format(SQLConstant.UserConstant.DELETE_USER_FROM_USERS_INFO, idUser));
				int deletedInfo = statement
						.executeUpdate(String.format(SQLConstant.UserConstant.DELETE_USER_FROM_USERS, idUser));
				statement.executeUpdate(SQLConstant.CONSTRAINT_ENABLE);
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
	public void updateUser(HttpServletRequest request, HttpServletResponse response) throws DAOException {
		User user = (User) request.getSession().getAttribute(USER_ATTRIBUTE);
		int id = user.getIdUser();
		String name = request.getParameter(NAME_PARAM);
		String surname = request.getParameter(SURNAME_PARAM);
		String patronimic = request.getParameter(PATRONIMIC_PARAM);
		String email = request.getParameter(EMAIL_PARAM);
		String login = request.getParameter(LOGIN_PARAM);
		String password = request.getParameter(PASSWORD_PARAM);
		int role = defineRole(request.getParameter(ROLE_PARAM));

		Connection connection = null;
		PreparedStatement statementUser = null;
		PreparedStatement statementUserInfo = null;

		try {
			connection = connectionPool.getConnection();
			
			try {
				connection.setAutoCommit(false);
				statementUser = connection.prepareStatement(String.format(SQLConstant.UserConstant.UPDATE_USER, name,
						surname, patronimic, email, role, id));
				int userRows = statementUser.executeUpdate();
				statementUserInfo = connection.prepareStatement(
						String.format(SQLConstant.UserConstant.UPDATE_USER_INFO, login, criptPassword(password), id));
				int userInfoRows=statementUserInfo.executeUpdate();
				connection.commit();
				connection.setAutoCommit(true);
				if (userRows != ZERO_USER_OPERATION && userInfoRows != ZERO_USER_OPERATION) {
					request.setAttribute(RESULT_ATTR, SUCCESSFUL_OPERATION);
				} else {
					request.setAttribute(RESULT_ATTR, FAILED_OPERATION);
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
				statement = connection.prepareStatement(SQLConstant.UserConstant.GET_USERS_BY_ROLE);
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
						.prepareStatement(String.format(SQLConstant.UserConstant.GET_USERS_BY_FLIGHT_ID, idFlight));
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
						.prepareStatement(String.format(SQLConstant.UserConstant.GET_USERS_BY_USER_ID, idUser));
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
						.prepareStatement(String.format(SQLConstant.UserConstant.GET_USER_INFO_BY_ID, idUser));
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
	public void findUser(HttpServletRequest request, HttpServletResponse response) throws DAOException {
		String login = request.getParameter(LOGIN_PARAM);
		Connection connection = null;
		PreparedStatement statementUser = null;
		PreparedStatement statementUserInfo = null;
		ResultSet rsUser = null;
		ResultSet rsUserInfo = null;
		UserInfo userInfo;
		User user;
		request.setAttribute(CURRENT_PAGE, PATH_TO_UPDATE_USER);
		try {
			connection = connectionPool.getConnection();
			try {
				statementUserInfo = connection
						.prepareStatement(String.format(SQLConstant.UserConstant.GET_USER_INFO_BY_ID, login));
				rsUserInfo = statementUserInfo.executeQuery();
				if (rsUserInfo.next()) {
					userInfo = makeUserInfo(rsUserInfo);
					request.getSession().setAttribute(USER_INFO_ATTRIBUTE, userInfo);

					statementUser = connection.prepareStatement(
							String.format(SQLConstant.UserConstant.FIND_USER_BY_ID, userInfo.getIdUserInfo()));
					rsUser = statementUser.executeQuery();
					if (rsUser.next()) {
						user = makeUser(rsUser);
						request.getSession().setAttribute(USER_ATTRIBUTE, user);
						try {
							
							request.getRequestDispatcher(PATH_TO_ADMIN_PAGE).forward(request, response);
						} catch (ServletException | IOException e) {
							throw new DAOException("Error while going to update_user.jsp", e);
						}
					}

				} else {
					try {
						request.getRequestDispatcher(PATH_TO_ADMIN_PAGE).forward(request, response);
					} catch (ServletException | IOException e) {
						throw new DAOException("Error while going to update_user.jsp", e);
					}
				}

			} catch (SQLException e) {
				throw new DAOException("error while adding user", e);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("error while getting connection from ConnectionPool", e);
		} finally {
			//*******************************************
			connectionPool.releaseConnection(connection);
			if (rsUser != null) {
				try {
					rsUser.close();
				} catch (SQLException e) {
					throw new DAOException("erroe while closing resultSet", e);
				}
			}
			if (rsUserInfo != null) {
				try {
					rsUserInfo.close();
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
		}

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
						String.format(SQLConstant.UserConstant.DELETE_CREW_FROM_FLIGHT, flightId, userId));
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
				statement = connection.prepareStatement(SQLConstant.CONSTRAINT_DISABLE);
				statement.executeQuery();
				statement = connection.prepareStatement(SQLConstant.UserConstant.ADD_USER_TO_CREW_BY_FLIGHT_ID);
				statement.setInt(1, idCrewPosition);
				statement.setInt(2, flightId);
				statement.setInt(3, userId);
				int i = statement.executeUpdate();
				statement = connection.prepareStatement(SQLConstant.CONSTRAINT_ENABLE);
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
						String.format(SQLConstant.UserConstant.GET_FREE_USERS_BY_POSITION, flightId, selectedPosition));
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

	private void sendCommandToController(UserRole userRole, HttpServletRequest request, HttpServletResponse response)
			throws DAOException {
		try {
			switch (userRole) {
			case MANAGER:
				request.getRequestDispatcher(GO_TO_MANAGER_PAGE_COMMAND).forward(request, response);
				break;
			case DISPATCHER:
				request.getRequestDispatcher(GO_TO_DISPATCHER_PAGE_COMMAND).forward(request, response);
				break;
			case ADMINISTRATOR:
				request.getRequestDispatcher(GO_TO_ADMINISTRATOR_PAGE_COMMAND).forward(request, response);
				break;
			case PILOT:
			case ENGINEER:
			case FLIGHT_ATTENDANT:
				request.getRequestDispatcher(GO_TO_CREW_PAGE_COMMAND).forward(request, response);
				break;
			}
		} catch (ServletException | IOException e) {
			throw new DAOException("Error while performing command forward to mapping /Controller", e);
		}

	}

	private User createUser(ResultSet resultSet) throws SQLException {
		int idUser = resultSet.getInt(ID_USER_COLUMN);
		String name = resultSet.getString(NAME_COLUMN);
		String surname = resultSet.getString(SURNAME_COLUMN);
		String patronimic = resultSet.getString(PATRONIMIC_COLUMN);
		String email = resultSet.getString(EMAIL_COLUMN);
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

	private int defineRole(String role) {
		switch (role) {
		case ADMINISTRATOR:
			return 3;
		case DISPATCHER:
			return 2;
		case MANAGER:
			return 1;
		case PILOT:
			return 4;
		case FLIGHT_ATTENDANT:
			return 5;
		case ENGINEER:
			return 6;
		}
		return 0;
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
			id = resultSet.getInt(USER_ID_COLUMN);
			login = resultSet.getString(LOGIN_COLUMN);
			password = resultSet.getString(PASSWORD_COLUMN);
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
			id = resultSet.getInt(ID_USER_COLUMN);
			name = resultSet.getString(NAME_COLUMN);
			surname = resultSet.getString(SURNAME_COLUMN);
			patronimic = resultSet.getString(PATRONIMIC_COLUMN);
			email = resultSet.getString(EMAIL_COLUMN);
			int roleNumber = resultSet.getInt(ROLE_COLUMN);
			role = convertNumberToUserRole(roleNumber);

		} catch (SQLException e) {
			throw new DAOException("Error while getting data from ResultSet", e);
		}
		return new User(id, name, surname, patronimic, email, role);
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
			return UserRole.FLIGHT_ATTENDANT;
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

	

}
