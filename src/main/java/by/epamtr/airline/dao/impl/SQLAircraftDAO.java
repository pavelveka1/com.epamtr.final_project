package by.epamtr.airline.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.epamtr.airline.dao.AircraftDAO;
import by.epamtr.airline.dao.AircraftStatusDAO;
import by.epamtr.airline.dao.SQLQueryConstant;
import by.epamtr.airline.dao.connection_pool.ConnectionPool;
import by.epamtr.airline.dao.connection_pool.exception.ConnectionPoolException;
import by.epamtr.airline.dao.connection_pool.impl.ConnectionPoolImpl;
import by.epamtr.airline.dao.exception.DAOException;
import by.epamtr.airline.entity.Aircraft;
import by.epamtr.airline.entity.AircraftType;
import by.epamtr.airline.entity.User;

public class SQLAircraftDAO implements AircraftDAO {
	private ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();
	private static final String ID_AIRCRAFT_TYPE_COLUMN = "id_aircraft_type";
	private static final String AIRCRAFT_TYPE_COLUMN = "aircraft_type";
	private static final String RANGE_FLIGHT_COLUMN = "range_flight";
	private static final String NUMBER_PASSENGER_COLUMN = "number_passengers";
	
	private static final String ID_AIRCRAFT_COLUMN="id_aircraft";
	private static final String STATUS_COLUMN="status";
	private static final String REGISTER_NUMBER_COLUMN = "registration_number";

	@Override
	public boolean addAircraft(Aircraft aircraft, AircraftType aircraftType) throws DAOException {
		int idTypeAircraft = aircraftType.getIdAircraftType();
		String registerNumber = aircraft.getRegisterNumber();
		String statusAircraft=aircraft.getStatus();
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
				int row=statement.executeUpdate();
				if(row==1) {
					return true;
				}else {
					return false;
				}

			} catch (SQLException e) {
				throw new DAOException("error while adding aircraft", e);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("error while getting connection from ConnectionPool", e);
		} finally {
			releaseResourses( statement,  rs, connection);
		}
	}

	@Override
	public void deleteAircraft(String registrationNumber) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			connection = connectionPool.getConnection();
			try {
				statement = connection.prepareStatement(
						String.format(SQLQueryConstant.AircraftConstant.DELETE_AIRCRAFT, registrationNumber));
				 statement.executeUpdate();

			} catch (SQLException e) {
				throw new DAOException("error while deletion aircraft", e);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("error while getting connection from ConnectionPool", e);
		} finally {
			releaseResourses( statement,  rs, connection);
		}
	}

	@Override
	public boolean updateAircraft(String registrationNumber, String newRegistrationNumber) throws DAOException {
		
		
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = connectionPool.getConnection();
			try {
				connection.prepareStatement(SQLQueryConstant.CONSTRAINT_DISABLE).executeQuery();
				statement = connection.prepareStatement(String.format(SQLQueryConstant.AircraftConstant.UPDATE_AIRCRAFT, newRegistrationNumber, registrationNumber ));
				int row=statement.executeUpdate();
				connection.prepareStatement(SQLQueryConstant.CONSTRAINT_ENABLE).executeQuery();
				if(row==1) {
					return true;
				}else {
					return false;
				}
			} catch (SQLException e) {
				throw new DAOException("error while updating user", e);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("error while getting connection from ConnectionPool", e);
		} finally {
			
			//*******************************************************
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
	public void changeAircraftStatus(int idAircraft, String status) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = connectionPool.getConnection();
			try {
				//connection.prepareStatement(SQLConstant.CONSTRAINT_DISABLE).executeQuery();
				statement = connection.prepareStatement(String.format(SQLQueryConstant.AircraftConstant.CHANGE_STATUS_AIRCRAFT, status, idAircraft));
				statement.executeUpdate();
				//connection.prepareStatement(SQLConstant.CONSTRAINT_ENABLE).executeQuery();
			} catch (SQLException e) {
				throw new DAOException("error while updating aircraft status", e);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("error while getting connection from ConnectionPool", e);
		} finally {
			
			//****************************************************
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
	public boolean addAircraftType(AircraftType aircraftType) throws DAOException {
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
				int row=statement.executeUpdate();
				if(row==1) {
					return true;
				}else {
					return false;
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
	public void deliteAircraftType(int idAircraftType) throws DAOException {
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			connection = connectionPool.getConnection();
			try {
				statement = connection.createStatement();
				int a = statement.executeUpdate(
						String.format(SQLQueryConstant.AircraftConstant.DELETE_AIRCRAFT_TYPE, idAircraftType));
			} catch (SQLException e) {
				throw new DAOException("Error during deletion aircraft types", e);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("error while getting connection from ConnectionPool", e);
		} finally {
			releaseResourses( statement,  rs, connection);
		}
	}

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
			releaseResourses( statement,  rs, connection);
		}
		return aircrafts;
	}

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
			releaseResourses( statement,  rs, connection);
		}
		return aircraftTypes;
	}

	private AircraftType makeAircraftType(ResultSet resultSet) throws SQLException {

		int id = resultSet.getInt(ID_AIRCRAFT_TYPE_COLUMN);
		String aircraftType = resultSet.getString(AIRCRAFT_TYPE_COLUMN);
		int flightRange = resultSet.getInt(RANGE_FLIGHT_COLUMN);
		int numberPassenger = resultSet.getInt(NUMBER_PASSENGER_COLUMN);
		AircraftType type = new AircraftType(id, aircraftType, flightRange, numberPassenger);
		return type;

	}
	
	private Aircraft makeAircraft(ResultSet resultSet) throws SQLException {
		AircraftType type=makeAircraftType(resultSet);
		int id=resultSet.getInt(ID_AIRCRAFT_COLUMN);
		String registrationNumber=resultSet.getString(REGISTER_NUMBER_COLUMN);
		String status=resultSet.getString(STATUS_COLUMN);
		return new Aircraft(id, type, registrationNumber, status);
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
