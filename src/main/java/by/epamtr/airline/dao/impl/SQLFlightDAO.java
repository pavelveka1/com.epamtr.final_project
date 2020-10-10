package by.epamtr.airline.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.epamtr.airline.dao.FlightDAO;
import by.epamtr.airline.dao.FlightStatusDAO;
import by.epamtr.airline.dao.SQLConstant;
import by.epamtr.airline.dao.connection_pool.ConnectionPool;
import by.epamtr.airline.dao.connection_pool.exception.ConnectionPoolException;
import by.epamtr.airline.dao.connection_pool.impl.ConnectionPoolImpl;
import by.epamtr.airline.dao.exception.DAOException;
import by.epamtr.airline.entity.Flight;
import by.epamtr.airline.entity.User;
import by.epamtr.airline.entity.UserRole;

public class SQLFlightDAO implements FlightDAO {
	private ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

	@Override
	public void addFlight(Flight flight) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deliteFlight(int idFlight) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateFlight(int idFlight) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void changeFlightStatus(int idFlight, FlightStatusDAO flightStatus) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public Flight getFlight(int idFlight) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Flight> getFlights() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Flight> getFlights(FlightStatusDAO flightStatus) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
			List<Flight> flights=new ArrayList<Flight>();
			try {
				connection = connectionPool.getConnection();
				try {
					statement = connection.prepareStatement(SQLConstant.UserConstant.GET_USERS_BY_ROLE);
					rs = statement.executeQuery();
					while (rs.next()) {
						if(flightStatus==FlightStatusDAO.valueOf(rs.getString("user_roles.user_role"))) {
							flights.add(createFlight(rs));
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

			return flights;
		

	}

	@Override
	public List<Flight> getFlights(User user) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addCrewToFlight(User user) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateFlightCrew(int idFlight) throws DAOException {
		// TODO Auto-generated method stub

	}
	
	private Flight createFlight(ResultSet resultSet) {
		return null;
		
	}

}
