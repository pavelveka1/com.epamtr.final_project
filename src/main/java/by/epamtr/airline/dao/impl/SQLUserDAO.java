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
import by.epamtr.airline.entity.User;
import by.epamtr.airline.entity.UserInfo;
import by.epamtr.airline.entity.UserRole;

public class SQLUserDAO implements UserDAO {

	private final String GO_TO_MANAGER_PAGE_COMMAND = "/Controller?command=GO_TO_MANAGER_PAGE";
	private final String GO_TO_DISPATCHER_PAGE_COMMAND = "/Controller?command=GO_TO_DISPATCHER_PAGE";
	private final String GO_TO_ADMINISTRATOR_PAGE_COMMAND = "/Controller?command=GO_TO_ADMINISTRATOR_PAGE";
	private final String GO_TO_CREW_PAGE_COMMAND = "/Controller?command=GO_TO_CREW_PAGE";

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

	private final String USER_ATTRIBUTE = "user";
	private final String USER_INFO_ATTRIBUTE = "user_info";

	private final String PASSWORD_PARAM = "password";
	private final String LOGIN_PARAM = "login";
	private final String EMAIL_PARAM = "email";
	private final String NAME_PARAM = "name";
	private final String SURNAME_PARAM = "surname";
	private final String PATRONIMIC_PARAM = "patronimic";
	private final String ROLE_PARAM = "role";

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
				} else {
					try {
						request.getRequestDispatcher(PATH_TO_CONTROLLER).forward(request, response);
					} catch (ServletException | IOException e) {
						throw new DAOException("error while forward to mapping /Controller", e);
					}
				}
				rs.close();
				rs = statement.executeQuery(String.format(SQLConstant.UserConstant.SIGN_IN_GET_USER, idUser));
				rs.next();
				User user = createUser(rs);
				request.setAttribute(USER_ATTRIBUTE, user);
				sendCommandToController(user.getRole(), request, response);

			} catch (SQLException e) {
				throw new DAOException("Error while getting data from DB", e);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("error while getting connection", e);
		} finally {
			connectionPool.releaseConnection(connection);
			if (rs != null) {
				try {
					rs.close();
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
		}

	}

	@Override
	public void signOut(HttpServletRequest request, HttpServletResponse response) throws DAOException {
		try {
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
						request.getRequestDispatcher(PATH_TO_ADD_USER_PAGE).forward(request, response);
					} catch (ServletException | IOException e) {
						throw new DAOException("Error while going to add_user.js");
					}
				} else {

				}
				statement.close();
				rs.close();
				statement = connection.prepareStatement(SQLConstant.UserConstant.ADD_USER_GET_MAX_ID);
				rs = statement.executeQuery();
				rs.next();
				int maxIdUser = rs.getInt(ID_USER_COLUMN);
				statement.close();
				connection.setAutoCommit(false);
				statement = connection.prepareStatement(SQLConstant.CONSTRAINT_DISABLE);
				statement.executeQuery();

				statement = connection.prepareStatement(SQLConstant.UserConstant.ADD_USER_INSERT_IN_USERS);
				statement.setString(1, request.getParameter(NAME_COLUMN));
				statement.setString(2, request.getParameter(SURNAME_COLUMN));
				statement.setString(3, request.getParameter(PATRONIMIC_COLUMN));
				statement.setString(4, request.getParameter(EMAIL_PARAM));
				statement.setInt(5, defineRole(request.getParameter(ROLE_COLUMN)));
				statement.executeUpdate();

				statement = connection.prepareStatement(SQLConstant.UserConstant.ADD_USER_INSERT_IN_USERS_INFO);
				statement.setInt(1, ++maxIdUser);
				statement.setString(2, request.getParameter(LOGIN_COLUMN));

				statement.setString(3, criptPassword(request.getParameter(PASSWORD_COLUMN)));
				statement.executeUpdate();
				statement = connection.prepareStatement(SQLConstant.CONSTRAINT_ENABLE);
				statement.executeQuery();
				connection.commit();
				connection.setAutoCommit(true);

			} catch (SQLException e) {
				throw new DAOException("error while adding user", e);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("error while getting connection from ConnectionPool", e);
		} finally {
			connectionPool.releaseConnection(connection);
			if (rs != null) {
				try {
					rs.close();
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
		}

	}

	@Override
	public void deliteUser(String login) throws DAOException {
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			connection = connectionPool.getConnection();
			try {
				statement = connection.createStatement();
				connection.setAutoCommit(false);
				statement.executeUpdate(SQLConstant.CONSTRAINT_DISABLE);
				statement.executeUpdate(String.format(SQLConstant.UserConstant.DELETE_USER_FROM_USERS_INFO, login));
				statement.executeUpdate(String.format(SQLConstant.UserConstant.DELETE_USER_FROM_USERS, login));
				statement.executeUpdate(SQLConstant.CONSTRAINT_ENABLE);
				connection.commit();
				connection.setAutoCommit(true);

			} catch (SQLException e) {
				throw new DAOException("erroe while deliting user", e);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("error while getting connection from ConnectionPool", e);
		} finally {
			connectionPool.releaseConnection(connection);
			if (rs != null) {
				try {
					rs.close();
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
		}

	}

	@Override
	public void updateUser(HttpServletRequest request, HttpServletResponse response) throws DAOException {
		User user=(User)request.getSession().getAttribute("user");
		int id = user.getIdUser();
		String name = request.getParameter(NAME_PARAM);
		String surname = request.getParameter(SURNAME_PARAM);
		String patronimic = request.getParameter(PATRONIMIC_PARAM);
		String email = request.getParameter(EMAIL_PARAM);
		String login=request.getParameter(LOGIN_PARAM);
		String password=request.getParameter(PASSWORD_PARAM);
		int role =defineRole(request.getParameter(ROLE_PARAM));
		
		Connection connection = null;
		Statement statement=null;
		PreparedStatement statementUser = null;
		PreparedStatement statementUserInfo = null;
		ResultSet rsUser = null;
		ResultSet rsUserInfo = null;
		
		try {
			connection = connectionPool.getConnection();
			try {
				connection.setAutoCommit(false);
				statementUser = connection.prepareStatement(String.format(SQLConstant.UserConstant.UPDATE_USER, name,surname,patronimic,email, role,id));
				statementUser.executeUpdate();
				statementUserInfo = connection.prepareStatement(String.format(SQLConstant.UserConstant.UPDATE_USER_INFO, login,password, id));
				statementUserInfo.executeUpdate();
				connection.commit();
				connection.setAutoCommit(true);

			} catch (SQLException e) {
				throw new DAOException("error while updating user", e);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("error while getting connection from ConnectionPool", e);
		} finally {
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
	public List<User> getUsers(UserRole role) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<User> users=new ArrayList<User>();
		try {
			connection = connectionPool.getConnection();
			try {
				statement = connection.prepareStatement(SQLConstant.UserConstant.GET_USERS_BY_ROLE);
				rs = statement.executeQuery();
				while (rs.next()) {
					if(role==UserRole.valueOf(rs.getString("user_roles.user_role"))) {
						users.add(createUser(rs));
					}
				
				} 
				
			
			} catch (SQLException e) {
				throw new DAOException("error while getting users by UserRole", e);
			}
			
		} catch (ConnectionPoolException e) {
			throw new DAOException("error while getting connection from ConnectionPool", e);
		} finally {
			connectionPool.releaseConnection(connection);
			if (rs != null) {
				try {
					rs.close();
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
		}

		return users;
	}

	@Override
	public List<User> getUsers(int idFlight) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUser(int idUser) throws DAOException {
		// TODO Auto-generated method stub
		return null;
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
		try {
			connection = connectionPool.getConnection();
			try {
				statementUserInfo = connection
						.prepareStatement(String.format(SQLConstant.UserConstant.FIND_USER_INFO_BY_LOGIN, login));
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
							request.getRequestDispatcher(PATH_TO_UPDATE_USER_PAGE).forward(request, response);
						} catch (ServletException | IOException e) {
							throw new DAOException("Error while going to update_user.jsp", e);
						}
					}

				} else {
					try {
						request.getRequestDispatcher(PATH_TO_UPDATE_USER_PAGE).forward(request, response);
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
			int roleNumber=resultSet.getInt(ROLE_COLUMN);
			role = convertNumberToUserRole(roleNumber);

		} catch (SQLException e) {
			throw new DAOException("Error while getting data from ResultSet", e);
		}
		return new User(id, name, surname, patronimic, email, role);
	}
	private UserRole convertNumberToUserRole(int roleNumber) {
		switch(roleNumber) {
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
}
