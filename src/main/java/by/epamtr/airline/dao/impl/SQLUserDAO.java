package by.epamtr.airline.dao.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.epamtr.airline.dao.FlightStatusDAO;
import by.epamtr.airline.dao.UserDAO;
import by.epamtr.airline.dao.connextion_pool.ConnectionPool;
import by.epamtr.airline.dao.connextion_pool.impl.ConnectionPoolImpl;
import by.epamtr.airline.dao.exception.DAOException;
import by.epamtr.airline.entity.Flight;
import by.epamtr.airline.entity.User;

public class SQLUserDAO implements UserDAO {

	private final String GO_TO_MANAGER_PAGE_COMMAND = "GO_TO_MANAGER_PAGE";
	private final String GO_TO_DISPATCHER_PAGE_COMMAND = "GO_TO_DISPATCHER_PAGE";
	private final String GO_TO_ADMINISTRATOR_PAGE_COMMAND = "GO_TO_ADMINISTRATOR_PAGE";
	private final String GO_TO_CREW_PAGE_COMMAND = "GO_TO_CREW_PAGE";

	@Override
	public void signIn(HttpServletRequest request, HttpServletResponse response) throws DAOException {
		Connection connection = null;
		Statement statement;
		ResultSet rs;
		
		ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();
		try {
			connection = connectionPool.getConnection();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery("SELECT login, password, user_role FROM users;");
			connectionPool.releaseConnection(connection);
			userIsExist(rs, request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void signOut() throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Flight> showFlights() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Flight> showFlights(FlightStatusDAO flightStatus) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> showCrew(int idFlight) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	private void userIsExist(ResultSet resultSet, HttpServletRequest request, HttpServletResponse response)
			throws DAOException {
		String login = request.getParameter("username");
		String password = request.getParameter("pass");
		
		try {
			while (resultSet.next()) {
				String loginFromDB = resultSet.getString("login");
				String passwordFromDB = resultSet.getString("password");
				if ((login.trim().equalsIgnoreCase(loginFromDB.trim()))
						&& (password.trim().equalsIgnoreCase(passwordFromDB.trim()))) {
					int userRole = Integer.parseInt(resultSet.getString("user_role"));
					resultSet.close();
					sendCommandToController(userRole, request, response);
				} else {
					System.out.println("Fail");
				}
			}
		} catch (NumberFormatException | SQLException e) {
			throw new DAOException("Error whole getting data from resultSet", e);
		}

	}

	private void sendCommandToController(int userRole, HttpServletRequest request, HttpServletResponse response)
			throws DAOException {
		try {
			switch (userRole) {
			case 1:
				request.getRequestDispatcher("/Controller?command=" + GO_TO_MANAGER_PAGE_COMMAND).forward(request,
						response);
				break;
			case 2:
				request.getRequestDispatcher("/Controller?command=" + GO_TO_DISPATCHER_PAGE_COMMAND).forward(request,
						response);
				break;
			case 3:
				request.getRequestDispatcher("/Controller?command=" + GO_TO_ADMINISTRATOR_PAGE_COMMAND).forward(request,
						response);
				break;
			case 4:
			case 5:
			case 6:
				request.getRequestDispatcher("/Controller?command=" + GO_TO_CREW_PAGE_COMMAND).forward(request,
						response);
				break;
			}
		} catch (ServletException | IOException e) {
			throw new DAOException("Error while performing command forward to mapping /Controller", e);
		}

	}
}
