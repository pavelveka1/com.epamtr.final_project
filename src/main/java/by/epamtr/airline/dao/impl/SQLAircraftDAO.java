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
import by.epamtr.airline.dao.SQLConstant;
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

	private static final String REGISTER_NUMBER_COLUMN = "registration_number";
	private static final String ID_AIRCRAFT_TYPE = "id_iarcraft_type";
	private static final String REGISTER_NUMBER_PARAM = "register_number";
	private static final String AIRCRAFT_STATUS_PARAM = "aircraft_status";
	private static final String AIRCRAFT_TYPE_PARAM = "aircraft_type";

	@Override
	public void addAircraft(HttpServletRequest request, HttpServletResponse response) throws DAOException {
		int idTypeAircraft = (Integer) request.getSession().getAttribute(ID_AIRCRAFT_TYPE);
		String registerNumber = request.getParameter(REGISTER_NUMBER_PARAM);
		String statusAircraft = request.getParameter(AIRCRAFT_STATUS_PARAM);
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			connection = connectionPool.getConnection();
			try {
				statement = connection.prepareStatement(SQLConstant.AircraftConstant.ADD_AIRCRAFT);
				statement.setInt(1, idTypeAircraft);
				statement.setString(2, registerNumber);
				statement.setString(3, statusAircraft);
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
	public void deleteAircraft(String registrationNumber) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			connection = connectionPool.getConnection();
			try {
				statement = connection.prepareStatement(
						String.format(SQLConstant.AircraftConstant.DELETE_AIRCRAFT, registrationNumber));
				statement.executeUpdate();

			} catch (SQLException e) {
				throw new DAOException("error while deletion aircraft", e);
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
	public void updateAircraft(HttpServletRequest request, HttpServletResponse response) throws DAOException {
		String registrationNumber = request.getParameter("aircraft_numbers");
		String newRegistrationNumber = request.getParameter(REGISTER_NUMBER_PARAM);
		
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = connectionPool.getConnection();
			try {
				connection.prepareStatement(SQLConstant.CONSTRAINT_DISABLE).executeQuery();
				statement = connection.prepareStatement(String.format(SQLConstant.AircraftConstant.UPDATE_AIRCRAFT, newRegistrationNumber, registrationNumber ));
				statement.executeUpdate();
				connection.prepareStatement(SQLConstant.CONSTRAINT_ENABLE).executeQuery();
			} catch (SQLException e) {
				throw new DAOException("error while updating user", e);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("error while getting connection from ConnectionPool", e);
		} finally {
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
	public void changeAircraftStatus(String registrationNumber, AircraftStatusDAO aircraftStatus) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void addAircraftType(HttpServletRequest request, HttpServletResponse response) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		String aircraftType = request.getParameter("aircraft_type");
		int flightRange = Integer.parseInt(request.getParameter("range_flight"));
		int numberPassenger = Integer.parseInt(request.getParameter("number_passengers"));
		try {
			connection = connectionPool.getConnection();
			try {
				statement = connection.prepareStatement(SQLConstant.AircraftConstant.ADD_AIRCRAFT_TYPES);
				statement.setString(1, aircraftType);
				statement.setInt(2, flightRange);
				statement.setInt(3, numberPassenger);
				statement.executeUpdate();

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
	public void deliteAircraftType(int idAircraftType) throws DAOException {
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			connection = connectionPool.getConnection();
			try {
				statement = connection.createStatement();
				int a = statement.executeUpdate(
						String.format(SQLConstant.AircraftConstant.DELETE_AIRCRAFT_TYPE, idAircraftType));
			} catch (SQLException e) {
				throw new DAOException("Error during deletion aircraft types", e);
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
	public List<String> getRegisterNumbers() throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<String> registerNumbers = new ArrayList<String>();
		try {
			connection = connectionPool.getConnection();
			try {
				statement = connection.prepareStatement(SQLConstant.AircraftConstant.GET_REGISTER_NUMBERS);
				rs = statement.executeQuery();
				while (rs.next()) {
					registerNumbers.add(rs.getString(REGISTER_NUMBER_COLUMN));
				}

			} catch (SQLException e) {
				throw new DAOException("error while getting aircraft types", e);
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
		return registerNumbers;
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
				statement = connection.prepareStatement(SQLConstant.AircraftConstant.GET_AIRCRAFT_TYPES);
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

}
