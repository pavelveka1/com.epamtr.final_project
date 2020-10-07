package by.epamtr.airline.dao.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtr.airline.dao.SQLConstant;
import by.epamtr.airline.dao.UserDAO;
import by.epamtr.airline.dao.connection_pool.ConnectionPool;
import by.epamtr.airline.dao.connection_pool.exception.ConnectionPoolException;
import by.epamtr.airline.dao.connection_pool.impl.ConnectionPoolImpl;
import by.epamtr.airline.dao.exception.DAOException;
import by.epamtr.airline.entity.User;
import by.epamtr.airline.entity.UserRole;

public class SQLUserDAO implements UserDAO {

	private final String GO_TO_MANAGER_PAGE_COMMAND = "GO_TO_MANAGER_PAGE";
	private final String GO_TO_DISPATCHER_PAGE_COMMAND = "GO_TO_DISPATCHER_PAGE";
	private final String GO_TO_ADMINISTRATOR_PAGE_COMMAND = "GO_TO_ADMINISTRATOR_PAGE";
	private final String GO_TO_CREW_PAGE_COMMAND = "GO_TO_CREW_PAGE";
	private final String WRONG_COMMAND = "GO_TO_LOGIN_PAGE";

	private final String ADMINISTRATOR = "ADMINISTRATOR";
	private final String DISPATCHER = "DISPATCHER";
	private final String MANAGER = "MANAGER";
	private final String PILOT = "PILOT";
	private final String FLIGHT_ATTENDANT = "FLIGHT ATTENDANT";
	private final String ENGINEER = "ENGINEER";

	private ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

	@Override
	public void signIn(HttpServletRequest request, HttpServletResponse response) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		int idUser = 0;
		String login = request.getParameter("login");
		String password = request.getParameter("pass");

		try {
			connection = connectionPool.getConnection();

			try {
				statement = connection
						.prepareStatement(String.format(SQLConstant.UserConstant.SIGN_IN_GET_ID, login, password));
				rs = statement.executeQuery();
				password = null;
				if (rs.next()) {
					idUser = rs.getInt("user_id");
				} else {
					try {
						request.getRequestDispatcher("/Controller?command=" + WRONG_COMMAND).forward(request, response);
					} catch (ServletException | IOException e) {
						throw new DAOException("error while forward to mapping /Controller", e);
					}
				}
				rs = statement.executeQuery(String.format(SQLConstant.UserConstant.SIGN_IN_GET_USER, idUser));
				rs.next();
				User user = createUser(rs);
				request.setAttribute("user", user);
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
			request.getRequestDispatcher("/Controller?command=GO_TO_LOGIN_PAGE").forward(request, response);
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
				statement = connection.prepareStatement(SQLConstant.UserConstant.ADD_USER_GET_MAX_ID);
				rs = statement.executeQuery();
				rs.next();
				int maxIdUser = rs.getInt("id_user");
				connection.setAutoCommit(false);
				statement = connection.prepareStatement("SET FOREIGN_KEY_CHECKS=0;");
				statement.executeQuery();
				statement = connection.prepareStatement(SQLConstant.UserConstant.ADD_USER_INSERT_IN_USERS);
				statement.setString(1, request.getParameter("name"));
				statement.setString(2, request.getParameter("surname"));
				statement.setString(3, request.getParameter("patronimic"));
				statement.setString(4, request.getParameter("email"));
				statement.setInt(5, defineRole(request.getParameter("role")));
				int a = statement.executeUpdate();

				statement = connection.prepareStatement(SQLConstant.UserConstant.ADD_USER_INSERT_IN_USERS_INFO);
				statement.setInt(1, ++maxIdUser);
				statement.setString(2, request.getParameter("login"));
				statement.setString(3, request.getParameter("password"));
				int b = statement.executeUpdate();
				statement = connection.prepareStatement("SET FOREIGN_KEY_CHECKS=1;");
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
				statement.executeUpdate("SET FOREIGN_KEY_CHECKS=0;");
				statement.executeUpdate(String.format(SQLConstant.UserConstant.DELETE_USER_FROM_USERS_INFO, login));
				statement.executeUpdate(String.format(SQLConstant.UserConstant.DELETE_USER_FROM_USERS, login));
				statement.executeUpdate("SET FOREIGN_KEY_CHECKS=1;");
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
	public void updateUser(String login) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<User> getUsers(UserRole role) throws DAOException {
		// TODO Auto-generated method stub
		return null;
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

	private void sendCommandToController(UserRole userRole, HttpServletRequest request, HttpServletResponse response)
			throws DAOException {
		try {
			switch (userRole) {
			case MANAGER:
				request.getRequestDispatcher("/Controller?command=" + GO_TO_MANAGER_PAGE_COMMAND).forward(request,
						response);
				break;
			case DISPATCHER:
				request.getRequestDispatcher("/Controller?command=" + GO_TO_DISPATCHER_PAGE_COMMAND).forward(request,
						response);
				break;
			case ADMINISTRATOR:
				request.getRequestDispatcher("/Controller?command=" + GO_TO_ADMINISTRATOR_PAGE_COMMAND).forward(request,
						response);
				break;
			case PILOT:
			case ENGINEER:
			case FLIGHT_ATTENDANT:
				request.getRequestDispatcher("/Controller?command=" + GO_TO_CREW_PAGE_COMMAND).forward(request,
						response);
				break;
			}
		} catch (ServletException | IOException e) {
			throw new DAOException("Error while performing command forward to mapping /Controller", e);
		}

	}

	private User createUser(ResultSet resultSet) throws SQLException {
		int idUser = resultSet.getInt("id_user");
		String name = resultSet.getString("name");
		String surname = resultSet.getString("surname");
		String patronimic = resultSet.getString("patronimic");
		String email = resultSet.getString("e_mail");
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

}
