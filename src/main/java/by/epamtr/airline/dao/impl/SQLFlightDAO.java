package by.epamtr.airline.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtr.airline.dao.FlightDAO;
import by.epamtr.airline.dao.SQLConstant;
import by.epamtr.airline.dao.connection_pool.ConnectionPool;
import by.epamtr.airline.dao.connection_pool.exception.ConnectionPoolException;
import by.epamtr.airline.dao.connection_pool.impl.ConnectionPoolImpl;
import by.epamtr.airline.dao.exception.DAOException;
import by.epamtr.airline.entity.Flight;
import by.epamtr.airline.entity.FlightStatus;
import by.epamtr.airline.entity.User;
import by.epamtr.airline.entity.UserRole;

public class SQLFlightDAO implements FlightDAO {
	private ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();
	private static final String CURRENT_CITY_PARAM="current_city";
	private static final String DESTINATION_CITY_PARAM="destination_city";
	private static final String REGISTRATION_NUMBER_AIRCRAFT_PARAM="aircraft_numbers";
	private static final String FLIGHT_RANGE_PARAM="flight_range";
	private static final String FLIGHT_TIME_PARAM="flight_time";
	private static final String TIME_DEPARTURE_PARAM="time_departure";
	private static final String STATUS_PARAM="flight_status";
	
	private static final String ID_AIRCRAFT_ATTR="id_iarcraft";

	@Override
	public void addFlight(HttpServletRequest request, HttpServletResponse response) throws DAOException {
		String currentCity =  request.getParameter(CURRENT_CITY_PARAM);
		String destinationCity = request.getParameter(DESTINATION_CITY_PARAM);
		int flightRange = Integer.parseInt(request.getParameter(FLIGHT_RANGE_PARAM));
		int flightTime = Integer.parseInt(request.getParameter(FLIGHT_TIME_PARAM));
		String timeDeparture = request.getParameter(TIME_DEPARTURE_PARAM);
		String status=request.getParameter(STATUS_PARAM);
		int idAircraft=(Integer)request.getSession().getAttribute(ID_AIRCRAFT_ATTR);
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			connection = connectionPool.getConnection();
			try {
				statement = connection.prepareStatement(SQLConstant.FlightConstant.ADD_FLIGHT);
				statement.setString(1, currentCity);
				statement.setString(2, destinationCity);
				statement.setInt(3, flightRange);
				statement.setInt(4, flightTime);
				statement.setString(5, timeDeparture);
				statement.setInt(6, idAircraft);
				statement.setString(7, status);
				statement.executeUpdate();

			} catch (SQLException e) {
				throw new DAOException("error while adding aircraft", e);
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
	public void deliteFlight(int idFlight) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateFlight(int idFlight) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void changeFlightStatus(int idFlight, FlightStatus flightStatus) throws DAOException {
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
	public List<Flight> getFlights(FlightStatus flightStatus) throws DAOException {
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
						if(flightStatus==FlightStatus.valueOf(rs.getString("user_roles.user_role"))) {
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
