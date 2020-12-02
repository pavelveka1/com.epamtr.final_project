package by.epamtr.airline.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.epamtr.airline.dao.AircraftDAO;
import by.epamtr.airline.dao.SQLQueryConstant;
import by.epamtr.airline.dao.SQLTableConstant;
import by.epamtr.airline.dao.connection_pool.ConnectionPool;
import by.epamtr.airline.dao.connection_pool.exception.ConnectionPoolException;
import by.epamtr.airline.dao.connection_pool.impl.ConnectionPoolImpl;
import by.epamtr.airline.dao.exception.DAOException;
import by.epamtr.airline.entity.Aircraft;
import by.epamtr.airline.entity.AircraftType;

/**
 * Class actions with aircrafts
 * 
 * @author HP
 *
 */
public class SQLAircraftDAO implements AircraftDAO {
	private static final Logger logger = Logger.getLogger(SQLAircraftDAO.class);
	/**
	 * Instance of connection pool for database
	 */
	private ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

	/**
	 * Add new aircraft in database
	 * 
	 * @param aircraft
	 * @param aircraftType
	 * @return true if aircraft was added, otherwise false
	 * @throws DAOException if dao exception occurred while processing
	 */
	@Override
	public boolean addAircraft(Aircraft aircraft, AircraftType aircraftType) throws DAOException {
		if (aircraft == null || aircraftType == null) {
			return false;
		}
		int idTypeAircraft = aircraftType.getIdAircraftType();
		String registerNumber = aircraft.getRegisterNumber();
		String statusAircraft = aircraft.getStatus();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			connection = connectionPool.getConnection();
			try {
				statement = connection.prepareStatement(SQLQueryConstant.AircraftConstant.ADD_AIRCRAFT);
				statement.setInt(1, idTypeAircraft);
				statement.setString(2, registerNumber);
				statement.setString(3, statusAircraft);
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
			try {
				connectionPool.releaseResourses(statement, rs, connection);
			} catch (ConnectionPoolException e) {
				logger.error(e);
			}
		}
	}

	/**
	 * Delete aircraft from database
	 * 
	 * @param registrationNumber
	 * @return true if aircraft was deleted, otherwise false
	 * @throws DAOException if dao exception occurred while processing
	 */
	@Override
	public boolean deleteAircraft(String registrationNumber) throws DAOException {
		if (registrationNumber == null) {
			return false;
		}
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		boolean result = false;
		try {
			connection = connectionPool.getConnection();
			try {
				statement = connection.prepareStatement(
						String.format(SQLQueryConstant.AircraftConstant.DELETE_AIRCRAFT, registrationNumber));
				int row = statement.executeUpdate();
				if (row != 0) {
					result = true;
				}
			} catch (SQLException e) {
				throw new DAOException("error while deletion aircraft", e);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("error while getting connection from ConnectionPool", e);
		} finally {
			try {
				connectionPool.releaseResourses(statement, rs, connection);
			} catch (ConnectionPoolException e) {
				logger.error(e);
			}
		}
		return result;
	}

	/**
	 * Update aircraft in datadase
	 * 
	 * @param registrationNumber
	 * @param newRegistrationNumber
	 * @return true if aircraft was updated, otherwise false
	 * @throws DAOException
	 */
	@Override
	public boolean updateAircraft(String registrationNumber, String newRegistrationNumber) throws DAOException {
		if (registrationNumber == null || newRegistrationNumber == null) {
			return false;
		}
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = connectionPool.getConnection();
			try {
				statement = connection.prepareStatement(String.format(SQLQueryConstant.AircraftConstant.UPDATE_AIRCRAFT,
						newRegistrationNumber, registrationNumber));
				int row = statement.executeUpdate();
				if (row == 1) {
					return true;
				} else {
					return false;
				}
			} catch (SQLException e) {
				return false;
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("error while getting connection from ConnectionPool", e);
		} finally {
			try {
				connectionPool.releaseResourses(statement, null, connection);
			} catch (ConnectionPoolException e) {
				logger.error(e);
			}
		}
	}

	/**
	 * Change status of aircraft in database
	 * 
	 * @param idAircraft
	 * @param status     - new status
	 * @return true if status was changed, otherwise false
	 * @throws DAOException if dao exception occurred while processing
	 */
	@Override
	public boolean changeAircraftStatus(int idAircraft, String status) throws DAOException {
		if (status == null) {
			return false;
		}
		Connection connection = null;
		PreparedStatement statement = null;
		boolean result = false;
		try {
			connection = connectionPool.getConnection();
			try {

				statement = connection.prepareStatement(
						String.format(SQLQueryConstant.AircraftConstant.CHANGE_STATUS_AIRCRAFT, status, idAircraft));
				int row = statement.executeUpdate();
				if (row != 0) {
					result = true;
				}
			} catch (SQLException e) {
				return false;
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("error while getting connection from ConnectionPool", e);
		} finally {
			try {
				connectionPool.releaseResourses(statement, null, connection);
			} catch (ConnectionPoolException e) {
				logger.error(e);
			}
		}
		return result;
	}

	/**
	 * Add new type of aircraft in database
	 * 
	 * @param aircraftType
	 * @return true if type of aircraft was added, otherwise false
	 * @throws DAOException if dao exception occurred while processing
	 */
	@Override
	public boolean addAircraftType(AircraftType aircraftType) throws DAOException {
		if (aircraftType == null) {
			return false;
		}
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		String typeAircraft = aircraftType.getAircraftType();
		int flightRange = aircraftType.getRangeFlight();
		int numberPassenger = aircraftType.getNumberPassenger();
		try {
			connection = connectionPool.getConnection();
			try {
				statement = connection.prepareStatement(SQLQueryConstant.AircraftConstant.ADD_AIRCRAFT_TYPES);
				statement.setString(1, typeAircraft);
				statement.setInt(2, flightRange);
				statement.setInt(3, numberPassenger);
				int row = statement.executeUpdate();
				if (row == 1) {
					return true;
				} else {
					return false;
				}

			} catch (SQLException e) {
				e.printStackTrace();
				throw new DAOException("error while adding user", e);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("error while getting connection from ConnectionPool", e);
		} finally {
			try {
				connectionPool.releaseResourses(statement, rs, connection);
			} catch (ConnectionPoolException e) {
				logger.error(e);
			}
		}
	}

	/**
	 * Delete type of aircraft from database
	 * 
	 * @param idAircraftType
	 * @return true if type of aircraft was deleted, otherwise false
	 * @throws DAOException if dao exception occurred while processing
	 */
	@Override
	public boolean deliteAircraftType(int idAircraftType) throws DAOException {
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		boolean result = false;
		try {
			connection = connectionPool.getConnection();
			try {
				statement = connection.createStatement();
				int row = statement.executeUpdate(
						String.format(SQLQueryConstant.AircraftConstant.DELETE_AIRCRAFT_TYPE, idAircraftType));
				if (row != 0) {
					result = true;
				}
			} catch (SQLException e) {
				throw new DAOException("Error during deletion aircraft types", e);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("error while getting connection from ConnectionPool", e);
		} finally {
			try {
				connectionPool.releaseResourses(statement, rs, connection);
			} catch (ConnectionPoolException e) {
				logger.error(e);
			}
		}
		return result;
	}

	/**
	 * Get list of aircrafts from database
	 * 
	 * @return list of aircrafts
	 * @throws DAOException if dao exception occurred while processing
	 */
	@Override
	public List<Aircraft> getAircrafts() throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<Aircraft> aircrafts = new ArrayList<Aircraft>();
		try {
			connection = connectionPool.getConnection();
			try {
				statement = connection.prepareStatement(SQLQueryConstant.AircraftConstant.GET_AIRCRAFTS);
				rs = statement.executeQuery();
				while (rs.next()) {
					aircrafts.add(makeAircraft(rs));
				}

			} catch (SQLException e) {
				throw new DAOException("error while getting aircraft types", e);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("error while getting connection from ConnectionPool", e);
		} finally {
			try {
				connectionPool.releaseResourses(statement, rs, connection);
			} catch (ConnectionPoolException e) {
				logger.error(e);
			}
		}
		return aircrafts;
	}

	/**
	 * Get list of types of aircrafts from database
	 * 
	 * @return list of types of aircrafts
	 * @throws DAOException if dao exception occurred while processing
	 */
	@Override
	public List<AircraftType> getAircraftTypes() throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<AircraftType> aircraftTypes = new ArrayList<AircraftType>();
		try {
			connection = connectionPool.getConnection();
			try {
				statement = connection.prepareStatement(SQLQueryConstant.AircraftConstant.GET_AIRCRAFT_TYPES);
				rs = statement.executeQuery();
				while (rs.next()) {
					aircraftTypes.add(makeAircraftType(rs));
				}

			} catch (SQLException e) {
				throw new DAOException("error while getting aircraft types", e);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("error while getting connection from ConnectionPool", e);
		} finally {
			try {
				connectionPool.releaseResourses(statement, rs, connection);
			} catch (ConnectionPoolException e) {
				logger.error(e);
			}
		}
		return aircraftTypes;
	}

	/**
	 * Create AircraftType object using resultSet
	 * 
	 * @param resultSet
	 * @return AircraftType object
	 * @throws SQLException if sql exception occurred while processing
	 */
	private AircraftType makeAircraftType(ResultSet resultSet) throws SQLException {

		int id = resultSet.getInt(SQLTableConstant.AircraftType.ID_AIRCRAFT_TYPE);
		String aircraftType = resultSet.getString(SQLTableConstant.AircraftType.AIRCRAFT_TYPE);
		int flightRange = resultSet.getInt(SQLTableConstant.AircraftType.RANGE_FLIGHT);
		int numberPassenger = resultSet.getInt(SQLTableConstant.AircraftType.NUMBER_PASSENGERS);
		AircraftType type = new AircraftType(id, aircraftType, flightRange, numberPassenger);
		return type;

	}

	/**
	 * Create Aircraft object using resultSet
	 * 
	 * @param resultSet
	 * @return Aircraft object
	 * @throws SQLException if sql exception occurred while processing
	 */
	private Aircraft makeAircraft(ResultSet resultSet) throws SQLException {
		AircraftType type = makeAircraftType(resultSet);
		int id = resultSet.getInt(SQLTableConstant.Aircraft.ID_AIRCRAFT);
		String registrationNumber = resultSet.getString(SQLTableConstant.Aircraft.REGISTRATION_NUMBER);
		String status = resultSet.getString(SQLTableConstant.Aircraft.STATUS);
		return new Aircraft(id, type, registrationNumber, status);
	}
}
