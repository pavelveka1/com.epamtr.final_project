package by.epamtr.airline.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import by.epamtr.airline.dao.FlightDAO;
import by.epamtr.airline.dao.SQLQueryConstant;
import by.epamtr.airline.dao.SQLTableConstant;
import by.epamtr.airline.dao.connection_pool.ConnectionPool;
import by.epamtr.airline.dao.connection_pool.exception.ConnectionPoolException;
import by.epamtr.airline.dao.connection_pool.impl.ConnectionPoolImpl;
import by.epamtr.airline.dao.exception.DAOException;
import by.epamtr.airline.entity.Aircraft;
import by.epamtr.airline.entity.CrewPosition;
import by.epamtr.airline.entity.Flight;
import by.epamtr.airline.entity.FlightStatus;
import by.epamtr.airline.entity.User;

/**
 * Class actions with flights
 * 
 * @author HP
 *
 */
public class SQLFlightDAO implements FlightDAO {
	/**
	 * Instance of connection pool for database
	 */
	private ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

	/**
	 * Add new flight in database
	 * 
	 * @param flight
	 * @param aircraft
	 * @return true if flight was added, otherwise false
	 * @throws DAOException if dao exception occurred while processing
	 */
	@Override
	public boolean addFlight(Flight flight, Aircraft aircraft) throws DAOException {
		String currentCity = flight.getCurrentCity();
		String destinationCity = flight.getDestinationCity();
		int flightRange = flight.getFlightRange();
		int flightTime = flight.getFlightTime();
		String timeDeparture = flight.getTimeDeparture();
		String status = flight.getStatus();
		int idAircraft = aircraft.getIdAircraft();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			connection = connectionPool.getConnection();
			try {
				statement = connection.prepareStatement(SQLQueryConstant.FlightConstant.ADD_FLIGHT);
				statement.setString(1, currentCity);
				statement.setString(2, destinationCity);
				statement.setInt(3, flightRange);
				statement.setInt(4, flightTime);
				statement.setString(5, timeDeparture);
				statement.setInt(6, idAircraft);
				statement.setString(7, status);
				int row = statement.executeUpdate();
				if (row == 1) {
					return true;
				} else {
					return false;
				}

			} catch (SQLException e) {
				throw new DAOException("error while adding aircraft", e);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("error while getting connection from ConnectionPool", e);
		} finally {
			releaseResourses(statement, rs, connection);
		}

	}

	/**
	 * Delete flight from database
	 * 
	 * @param idFlight
	 * @return true if flight was deleted, otherwise false
	 * @throws DAOException if dao exception occurred while processing
	 */
	@Override
	public boolean deliteFlight(int idFlight) throws DAOException {
		Connection connection = null;
		PreparedStatement statementDeleteFlight = null;
		PreparedStatement statementDeleteCrews = null;
		ResultSet rs = null;
		boolean result = false;
		try {
			connection = connectionPool.getConnection();
			try {
				statementDeleteFlight = connection.prepareStatement(
						String.format(String.format(SQLQueryConstant.FlightConstant.DELETE_FLIGHT, idFlight)));
				int row = statementDeleteFlight.executeUpdate();
				statementDeleteCrews = connection
						.prepareStatement(String.format(SQLQueryConstant.FlightConstant.DELETE_CREW, idFlight));
				statementDeleteCrews.executeUpdate();
				if (row != 0) {
					result = true;
				}
			} catch (SQLException e) {
				throw new DAOException("error while deletion aircraft", e);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("error while getting connection from ConnectionPool", e);
		} finally {

			// ******************************************************
			connectionPool.releaseConnection(connection);
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					throw new DAOException("erroe while closing resultSet", e);
				}
			}
			if (statementDeleteFlight != null) {
				try {
					statementDeleteFlight.close();
				} catch (SQLException e) {
					throw new DAOException("erroe while closing statement", e);
				}
			}
			if (statementDeleteCrews != null) {
				try {
					statementDeleteCrews.close();
				} catch (SQLException e) {
					throw new DAOException("erroe while closing statement", e);
				}
			}
		}
		return result;
	}

	/**
	 * Update flight in detabase
	 * @param idFlight
	 * @param flight
	 * @return true if flight was updated, otherwise false
	 * @throws DAOException if dao exception occurred while processing
	 */
	@Override
	public Flight updateFlight(int idFlight, Flight flight) throws DAOException {

		String currentCity = flight.getCurrentCity();
		String destinationCity = flight.getDestinationCity();
		int flightRange = flight.getFlightRange();
		int flightTime = flight.getFlightTime();
		String aircraftNumber = flight.getAircraftNumber();
		String timeDeparture = flight.getTimeDeparture();
		String flightStatus = flight.getStatus();
		Flight updatedFlight;
		Connection connection = null;
		PreparedStatement statement = null;
		PreparedStatement updatedFlightStatement = null;
		ResultSet rs = null;

		try {
			connection = connectionPool.getConnection();
			try {
				// connection.prepareStatement(SQLConstant.CONSTRAINT_DISABLE).executeQuery();
				statement = connection.prepareStatement(
						String.format(SQLQueryConstant.FlightConstant.UPDATE_FLIGHT, currentCity, destinationCity,
								flightRange, flightTime, timeDeparture, aircraftNumber, flightStatus, idFlight));
				statement.executeUpdate();
				updatedFlightStatement = connection
						.prepareStatement(String.format(SQLQueryConstant.FlightConstant.GET_FLIGHTS_BY_ID, idFlight));
				rs = updatedFlightStatement.executeQuery();
				rs.next();
				updatedFlight = createFlight(rs);
				// connection.prepareStatement(SQLConstant.CONSTRAINT_ENABLE).executeQuery();
			} catch (SQLException e) {
				throw new DAOException("error while updating user", e);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("error while getting connection from ConnectionPool", e);
		} finally {

			// ******************************************************
			connectionPool.releaseConnection(connection);
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					throw new DAOException("erroe while closing statement", e);
				}
			}
			if (updatedFlightStatement != null) {
				try {
					updatedFlightStatement.close();
				} catch (SQLException e) {
					throw new DAOException("erroe while closing statement", e);
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					throw new DAOException("erroe while closing ResultSet", e);
				}
			}
		}
		return updatedFlight;
	}

	/**
	 * Change status of flight
	 * @param idFlight
	 * @param flightStatus new status
	 * @return true if status was updated, otherwise false
	 * @throws DAOException if dao exception occurred while processing
	 */
	@Override
	public boolean changeFlightStatus(int idFlight, FlightStatus flightStatus) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		boolean result = false;
		try {
			connection = connectionPool.getConnection();
			try {
				statement = connection.prepareStatement(
						String.format(SQLQueryConstant.FlightConstant.CHANGE_STATUS_FLIGHT, flightStatus, idFlight));
				int row = statement.executeUpdate();
				if (row != 0) {
					result = true;
				}

			} catch (SQLException e) {
				throw new DAOException("error while updating aircraft status", e);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("error while getting connection from ConnectionPool", e);
		} finally {

			// ****************************************************
			connectionPool.releaseConnection(connection);

			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					throw new DAOException("erroe while closing statement", e);
				}
			}
		}
		return result;
	}

	/**
	 * Get flight by if of flight
	 * @param idFlight
	 * @return flight if found, otherwise null
	 * @throws DAOException if dao exception occurred while processing
	 */
	@Override
	public Flight getFlight(int idFlight) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Flight flight;
		try {
			connection = connectionPool.getConnection();
			try {
				statement = connection
						.prepareStatement(String.format(SQLQueryConstant.FlightConstant.GET_FLIGHTS_BY_ID, idFlight));
				rs = statement.executeQuery();
				rs.next();
				flight = createFlight(rs);
			} catch (SQLException e) {
				throw new DAOException("error while getting flight by id", e);
			}

		} catch (ConnectionPoolException e) {
			throw new DAOException("error while getting connection from ConnectionPool", e);
		} finally {
			releaseResourses(statement, rs, connection);
		}
		return flight;
	}

	/**
	 * Get list of flights
	 * @return list of flights
	 * @throws DAOException if dao exception occurred while processing
	 */
	@Override
	public List<Flight> getFlights() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get flights by id of user
	 * @param idUser
	 * @return list of flights where user with idUser occuped any position
	 * @throws DAOException list of flights where user with idUser occuped any position
	 */
	@Override
	public List<Flight> getFlights(int idUser) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<Flight> flights = new ArrayList<Flight>();
		try {
			connection = connectionPool.getConnection();
			try {
				statement = connection.prepareStatement(
						String.format(SQLQueryConstant.FlightConstant.GET_FLIGHTS_BY_USER_ID, idUser));
				rs = statement.executeQuery();
				while (rs.next()) {
					flights.add(createFlight(rs));
				}

			} catch (SQLException e) {
				throw new DAOException("error while getting flights by id user", e);
			}

		} catch (ConnectionPoolException e) {
			throw new DAOException("error while getting connection from ConnectionPool", e);
		} finally {
			releaseResourses(statement, rs, connection);
		}
		return flights;
	}

	/**
	 * Add user to flight as crew member
	 * @param user
	 * @return true if user was added to flight
	 * @throws DAOException
	 */
	@Override
	public boolean addCrewToFlight(User user) throws DAOException {
		return false;
		// TODO Auto-generated method stub

	}

	/**
	 * Update crew of flight by flight's id
	 * @param idFlight
	 * @return true if crew of flight was updated, otherwise false
	 * @throws DAOException list of flights where user with idUser occuped any position
	 */
	@Override
	public boolean updateFlightCrew(int idFlight) throws DAOException {
		return false;
		// TODO Auto-generated method stub

	}

	/**
	 * Get flights by status of flight
	 * @param flightStatus -FlightStatus object which contains name of status
	 * @return list of flights where status like in parameter flightStatus
	 * @throws DAOException
	 */
	@Override
	public List<Flight> getFlights(FlightStatus flightStatus) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<Flight> flights = new ArrayList<Flight>();
		try {
			connection = connectionPool.getConnection();
			try {
				statement = connection.prepareStatement(SQLQueryConstant.FlightConstant.GET_FLIGHTS_BY_STATUS);
				rs = statement.executeQuery();
				while (rs.next()) {
					if (flightStatus == FlightStatus.valueOf(rs.getString(SQLTableConstant.Flight.FLIGHT_STATUS))) {
						flights.add(createFlight(rs));
					}

				}

			} catch (SQLException e) {
				throw new DAOException("error while getting users by UserRole", e);
			}

		} catch (ConnectionPoolException e) {
			throw new DAOException("error while getting connection from ConnectionPool", e);
		} finally {
			releaseResourses(statement, rs, connection);
		}
		return flights;
	}

	/**
	 * Get free crew positions by flight id
	 * @param flightId
	 * @return list of free crew positions
	 * @throws DAOException
	 */
	@Override
	public List<CrewPosition> getFreeCrewPositions(int flightId) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<CrewPosition> freeCrewPositions = new ArrayList<CrewPosition>();
		try {
			connection = connectionPool.getConnection();
			try {
				statement = connection.prepareStatement(
						String.format(SQLQueryConstant.FlightConstant.GET_FREE_POSITIONS_BY_FLIGHT_ID, flightId));
				rs = statement.executeQuery();
				while (rs.next()) {
					freeCrewPositions.add(createCrewPosition(rs));
				}

			} catch (SQLException e) {
				throw new DAOException("error while getting free crew positions", e);
			}

		} catch (ConnectionPoolException e) {
			throw new DAOException("error while getting connection from ConnectionPool", e);
		} finally {
			releaseResourses(statement, rs, connection);
		}
		return freeCrewPositions;
	}

	/**
	 * Create flight using resultSet
	 * @param resultSet
	 * @return Flight object
	 * @throws SQLException if sql exception occurred while processing
	 */
	private Flight createFlight(ResultSet resultSet) throws SQLException {
		int idFlight = resultSet.getInt(SQLTableConstant.Flight.ID_FLIGHT);
		String currentCity = resultSet.getString(SQLTableConstant.Flight.CURRENT_CITY);
		String destinationCity = resultSet.getString(SQLTableConstant.Flight.DESTINATION_CITY);
		int flightRange = resultSet.getInt(SQLTableConstant.Flight.FLIGHT_RANGE);
		int flightTime = resultSet.getInt(SQLTableConstant.Flight.FLIGHT_TIME);
		String timeDeparture = resultSet.getString(SQLTableConstant.Flight.DAY_AND_TIME_DEPARTURE);
		String aircraftType = resultSet.getString(SQLTableConstant.AircraftType.AIRCRAFT_TYPE);
		String aircraftNumber = resultSet.getString(SQLTableConstant.Aircraft.REGISTRATION_NUMBER);
		String flightStatus = resultSet.getString(SQLTableConstant.Flight.FLIGHT_STATUS);

		Flight flight = new Flight(idFlight, currentCity, destinationCity, flightRange, flightTime, timeDeparture,
				aircraftType, aircraftNumber, flightStatus);
		return flight;

	}

	/**
	 * Create CrewPosition object using resultSet
	 * @param resultSet
	 * @return CrewPosition object
	 * @throws SQLException if sql exception occurred while processing
	 */
	private CrewPosition createCrewPosition(ResultSet resultSet) throws SQLException {
		int idCrewPosition = resultSet.getInt(SQLTableConstant.CrewPosition.CREW_POSITION);
		String crewPositionName = resultSet.getString(SQLTableConstant.Crew.CREW_POSITION);
		CrewPosition crewPosition = new CrewPosition(idCrewPosition, crewPositionName);
		return crewPosition;
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
