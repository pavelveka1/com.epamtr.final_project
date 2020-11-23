package by.epamtr.airline.dao.impl;

import org.junit.Test;
import by.epamtr.airline.dao.AircraftDAO;
import by.epamtr.airline.dao.AircraftStatusDAO;
import by.epamtr.airline.dao.connection_pool.ConnectionPool;
import by.epamtr.airline.dao.connection_pool.exception.ConnectionPoolException;
import by.epamtr.airline.dao.connection_pool.impl.ConnectionPoolImpl;
import by.epamtr.airline.dao.exception.DAOException;
import by.epamtr.airline.entity.Aircraft;
import by.epamtr.airline.entity.AircraftType;

import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public class SQLAircraftDAOTest {

	private static AircraftType testAircraftTypeOne;
	private static AircraftType testAircraftTypeTwo;
	private static AircraftType testAircraftTypeThree;
	private static AircraftType testAircraftTypeFour;
	private static AircraftType testAircraftTypeFive;
	private static Aircraft testAircraftOne;
	private static Aircraft testAircraftTwo;
	private static Aircraft testAircraftThree;
	private static Aircraft testAircraftFour;
	private static Aircraft testAircraftFive;
	private final static ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();
	private static final String ADD_AIRCRAFT_TYPE = " INSERT INTO aircraft_types (`aircraft_type`, `range_flight`, `number_passengers`) VALUES (?,?,?) ;";
	private static final String ADD_AIRCRAFT = " INSERT INTO aircrafts (`type_aircraft`, `registration_number`, `status`) VALUES (?,?,?) ;";
	public static final String DELETE_AIRCRAFT_TYPE = "DELETE FROM aircraft_types WHERE id_aircraft_type =%d ;";
	public static final String DELETE_AIRCRAFT_TYPE_BY_NAME = "DELETE FROM aircraft_types WHERE aircraft_type ='%s';";
	public static final String DELETE_AIRCRAFT = " DELETE FROM aircrafts WHERE id_aircraft =%d;";
	public static final String DELETE_AIRCRAFT_BY_REG_NUMBER = " DELETE FROM aircrafts WHERE registration_number ='%s';";
	private static final String GET_AIRCRAFTS_DATA = "select id_aircraft_type, aircraft_type, range_flight, number_passengers, id_aircraft, registration_number, status from aircrafts\r\n"
			+ "		JOIN aircraft_types ON aircrafts.type_aircraft=aircraft_types.id_aircraft_type;";
	private static final String GET_AIRCRAFT_TYPES = "SELECT * FROM aircraft_types ;";
	private final AircraftDAO aircraftDao = new SQLAircraftDAO();
	private static int count = 1;
	private static final String REGISTRATION_NUMBER = "test";
	private static final String REGISTRATION_NUMBER_NOT_EXIST = "regNumNot";
	private static final String NEW_REGISTRATION_NUMBER = "newRegNum";
	private static final int RANGE_FLIGHT = 5000;
	private static final int NUMBER_PASSENGERS = 100;
	private static final String TEST_REG_NUMBER = "TestNumber";
	private static final String AIRCRAFT_TYPE = "TestAircraftType";
	private static final String AIRCRAFT_STATUS_NOT_EXIST = "StatusNotExist";
	private static final String ID_AIRCRAFT_COLUMN = "id_aircraft";
	private static final String TYPE_AIRCRAFT_COLUMN = "type_aircraft";
	private static final String REGISTRATION_NUMBER_COLUMN = "registration_number";
	private static final String STATUS_COLUMN = "status";
	private static final String ID_AIRCRAFT_TYPE_COLUMN = "id_aircraft_type";
	private static final String AIRCRAFT_TYPE_COLUMN = "aircraft_type";
	private static final String RANGE_FLIGHT_COLUMN = "range_flight";
	private static final String NUMBER_PASSENGERS_COLUMN = "number_passengers";

	private static AircraftType testAircraftType;
	private static Aircraft testAircraft;

	@BeforeClass
	public static void initializeData() throws ConnectionPoolException, SQLException {

		testAircraftTypeOne = makeAircraftType();
		testAircraftTypeTwo = makeAircraftType();
		testAircraftTypeThree = makeAircraftType();
		testAircraftTypeFour = makeAircraftType();
		testAircraftTypeFive = makeAircraftType();

		testAircraftOne = makeAircraft(testAircraftTypeOne);
		testAircraftTwo = makeAircraft(testAircraftTypeTwo);
		testAircraftThree = makeAircraft(testAircraftTypeThree);
		testAircraftFour = makeAircraft(testAircraftTypeFour);

		testAircraft = new Aircraft();
		testAircraft.setRegisterNumber(TEST_REG_NUMBER);
		testAircraft.setType(testAircraftTypeOne);

		testAircraftType = new AircraftType();
		testAircraftType.setAircraftType(AIRCRAFT_TYPE);
		testAircraftType.setNumberPassenger(NUMBER_PASSENGERS);
		testAircraftType.setRangeFlight(RANGE_FLIGHT);
		testAircraft.setStatus(AircraftStatusDAO.SERVICEABLE.getStatus());
	}

	@AfterClass
	public static void deleteTestData() throws ConnectionPoolException, SQLException {
		if (testAircraftOne != null) {
			deleteAircraft(testAircraftOne.getIdAircraft());
		}
		if (testAircraftTwo != null) {
			deleteAircraft(testAircraftTwo.getIdAircraft());
		}
		if (testAircraftThree != null) {
			deleteAircraft(testAircraftThree.getIdAircraft());
		}
		if (testAircraftFour != null) {
			deleteAircraft(testAircraftFour.getIdAircraft());
		}

		if (testAircraftTypeOne != null) {
			deleteAircraftType(testAircraftTypeOne.getIdAircraftType());
		}
		if (testAircraftTypeTwo != null) {
			deleteAircraftType(testAircraftTypeTwo.getIdAircraftType());
		}
		if (testAircraftTypeThree != null) {
			deleteAircraftType(testAircraftTypeThree.getIdAircraftType());
		}
		if (testAircraftTypeFour != null) {
			deleteAircraftType(testAircraftTypeFour.getIdAircraftType());
		}
		if (testAircraftTypeFive != null) {
			deleteAircraftType(testAircraftTypeFive.getIdAircraftType());
		}
		deleteAircraftTypeByName(testAircraftType.getAircraftType());
		deleteAircraftByRegistrationNumber(testAircraft.getRegisterNumber());

	}

	@Test(timeout = 100)
	public void addAircraftCorrect() throws DAOException {
		boolean result = aircraftDao.addAircraft(testAircraft, testAircraftTypeOne);
		assertTrue(result);
	}

	@Test(timeout = 100)
	public void addAircrafParameterIsNull() throws DAOException {
		boolean result = aircraftDao.addAircraft(null, testAircraftTypeOne);
		assertFalse(result);
	}

	@Test(expected = DAOException.class)
	public void addAircraftDuplicate() throws DAOException {
		boolean secondAttempt = aircraftDao.addAircraft(testAircraftOne, testAircraftTypeOne);

	}

	@Test(timeout = 100)
	public void addAircraftType() throws DAOException {
		boolean result = aircraftDao.addAircraftType(testAircraftType);
		assertTrue(result);
	}

	@Test(timeout = 100)
	public void addAircraftTypeParameterIsNull() throws DAOException {
		boolean result = aircraftDao.addAircraftType(null);
		assertFalse(result);
	}

	@Test(expected = DAOException.class)
	public void addAircraftTypeDuplicate() throws DAOException {
		boolean secondAttempt = aircraftDao.addAircraftType(testAircraftTypeOne);
	}

	@Test(timeout = 100)
	public void deleteAircraftExisting() throws DAOException {
		String registrationNumber = testAircraftTwo.getRegisterNumber();
		boolean result = aircraftDao.deleteAircraft(registrationNumber);
		assertTrue(result);
	}

	@Test(timeout = 100)
	public void deleteAircraftParameterIsNull() throws DAOException {
		boolean result = aircraftDao.deleteAircraft(null);
		assertFalse(result);
	}

	@Test(timeout = 100)
	public void deleteAircraftNotExisting() throws DAOException {
		String nonExistentRegistrationNumber = REGISTRATION_NUMBER_NOT_EXIST;
		boolean result = aircraftDao.deleteAircraft(nonExistentRegistrationNumber);
		assertFalse(result);
	}

	@Test(timeout = 100)
	public void deleteAircraftTypeByExistingId() throws DAOException, ConnectionPoolException, SQLException {
		boolean result = aircraftDao.deliteAircraftType(testAircraftTypeFive.getIdAircraftType());
		assertTrue(result);
	}

	@Test(timeout = 100)
	public void deleteAircraftTypeByNonExistingId() throws DAOException {
		int nonExistingId = 0;
		boolean result = aircraftDao.deliteAircraftType(nonExistingId);
		assertFalse(result);
	}

	@Test(timeout = 100)
	public void updateAircraft() throws DAOException {
		String registrationNumber = testAircraftOne.getRegisterNumber();
		String newRegistrationNumber = NEW_REGISTRATION_NUMBER;
		testAircraftOne.setRegisterNumber(newRegistrationNumber);
		boolean result = aircraftDao.updateAircraft(registrationNumber, newRegistrationNumber);
		assertTrue(result);
	}

	@Test(timeout = 100)
	public void updateAircraftParameterIsNull() throws DAOException {
		String newRegistrationNumber = NEW_REGISTRATION_NUMBER;
		testAircraftOne.setRegisterNumber(newRegistrationNumber);
		boolean result = aircraftDao.updateAircraft(null, newRegistrationNumber);
		assertFalse(result);

	}

	@Test(timeout = 100)
	public void changeAircraftStatus() throws DAOException, ConnectionPoolException, SQLException {
		String status = AircraftStatusDAO.MAINTENANCE.getStatus();
		boolean result = aircraftDao.changeAircraftStatus(testAircraftOne.getIdAircraft(), status);
		assertTrue(result);
	}

	@Test(timeout = 100)
	public void changeAircraftStatusParameterIsNull() throws DAOException, ConnectionPoolException, SQLException {
		boolean result = aircraftDao.changeAircraftStatus(testAircraftOne.getIdAircraft(), null);
		assertFalse(result);
	}

	@Test(timeout = 100)
	public void changeAircraftStatusIdNotExist() throws DAOException, ConnectionPoolException, SQLException {
		String status = testAircraftOne.getStatus();
		int idAircraft = 0;
		boolean result = aircraftDao.changeAircraftStatus(idAircraft, status);
		assertFalse(result);
	}

	@Test(timeout = 100)
	public void changeAircraftStatusNotExist() throws DAOException, ConnectionPoolException, SQLException {
		String status = AIRCRAFT_STATUS_NOT_EXIST;
		boolean result = aircraftDao.changeAircraftStatus(testAircraftOne.getIdAircraft(), status);
		assertFalse(result);
	}

	@Test(timeout = 100)
	public void getAircrafts() throws ConnectionPoolException, SQLException, DAOException {
		List<Aircraft> expectedList = new ArrayList<Aircraft>();
		Connection connection = null;
		connection = connectionPool.getConnection();
		PreparedStatement statement = connection.prepareStatement(GET_AIRCRAFTS_DATA);
		ResultSet rs = statement.executeQuery();
		while (rs.next()) {
			AircraftType type = new AircraftType(rs.getInt(ID_AIRCRAFT_TYPE_COLUMN), rs.getString(AIRCRAFT_TYPE_COLUMN),
					rs.getInt(RANGE_FLIGHT_COLUMN), rs.getInt(NUMBER_PASSENGERS_COLUMN));
			Aircraft aircraft = new Aircraft(rs.getInt(ID_AIRCRAFT_COLUMN), type,
					rs.getString(REGISTRATION_NUMBER_COLUMN), rs.getString(STATUS_COLUMN));
			expectedList.add(aircraft);
		}
		connectionPool.releaseResourses(statement, rs, connection);
		List<Aircraft> actualList = aircraftDao.getAircrafts();
		assertEquals(expectedList, actualList);
	}

	@Test(timeout = 100)
	public void getAircraftsNotNull() throws DAOException {
		List<Aircraft> actualList = aircraftDao.getAircrafts();
		assertNotNull(actualList);
	}

	@Test(timeout = 100)
	public void getAircraftTypes() throws ConnectionPoolException, SQLException, DAOException {
		List<AircraftType> expectedList = new ArrayList<AircraftType>();
		Connection connection = null;
		connection = connectionPool.getConnection();
		PreparedStatement statement = connection.prepareStatement(GET_AIRCRAFT_TYPES);
		ResultSet rs = statement.executeQuery();
		while (rs.next()) {
			AircraftType type = new AircraftType(rs.getInt(ID_AIRCRAFT_TYPE_COLUMN), rs.getString(AIRCRAFT_TYPE_COLUMN),
					rs.getInt(RANGE_FLIGHT_COLUMN), rs.getInt(NUMBER_PASSENGERS_COLUMN));

			expectedList.add(type);
		}
		connectionPool.releaseResourses(statement, rs, connection);
		List<AircraftType> actualList = aircraftDao.getAircraftTypes();
		assertEquals(expectedList, actualList);
	}

	@Test(timeout = 100)
	public void getAircraftTypesNotNull() throws DAOException {
		List<AircraftType> actualList = aircraftDao.getAircraftTypes();
		assertNotNull(actualList);
	}

	private static AircraftType makeAircraftType() throws ConnectionPoolException, SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		AircraftType aircraftType = new AircraftType();
		aircraftType.setAircraftType(AIRCRAFT_TYPE + count++);
		aircraftType.setRangeFlight(RANGE_FLIGHT);
		aircraftType.setNumberPassenger(NUMBER_PASSENGERS);
		connection = connectionPool.getConnection();
		statement = connection.prepareStatement(ADD_AIRCRAFT_TYPE, Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, aircraftType.getAircraftType());
		statement.setInt(2, aircraftType.getRangeFlight());
		statement.setInt(3, aircraftType.getNumberPassenger());
		int row = statement.executeUpdate();

		if (row != 0) {
			rs = statement.getGeneratedKeys();
			rs.next();
			aircraftType.setIdAircraftType(rs.getInt(1));
		}
		connectionPool.releaseResourses(statement, rs, connection);
		return aircraftType;
	}

	private static Aircraft makeAircraft(AircraftType aircraftType) throws SQLException, ConnectionPoolException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Aircraft aircraft = new Aircraft();
		aircraft.setRegisterNumber(REGISTRATION_NUMBER + count++);
		aircraft.setStatus(AircraftStatusDAO.SERVICEABLE.getStatus());
		aircraft.setType(aircraftType);
		connection = connectionPool.getConnection();
		statement = connection.prepareStatement(ADD_AIRCRAFT, Statement.RETURN_GENERATED_KEYS);
		statement.setInt(1, aircraft.getType().getIdAircraftType());
		statement.setString(2, aircraft.getRegisterNumber());
		statement.setString(3, aircraft.getStatus());
		int row = statement.executeUpdate();

		if (row != 0) {
			rs = statement.getGeneratedKeys();
			rs.next();
			aircraft.setIdAircraft(rs.getInt(1));
		}
		connectionPool.releaseResourses(statement, rs, connection);
		return aircraft;
	}

	private static boolean deleteAircraftType(int idAircraftType) throws ConnectionPoolException, SQLException {
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			connection = connectionPool.getConnection();
			statement = connection.createStatement();
			int row = statement.executeUpdate(String.format(DELETE_AIRCRAFT_TYPE, idAircraftType));
			if (row != 0) {
				return true;
			}
			return false;
		} finally {
			connectionPool.releaseResourses(statement, rs, connection);
		}
	}

	private static boolean deleteAircraftTypeByName(String AircraftType) throws ConnectionPoolException, SQLException {
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			connection = connectionPool.getConnection();
			statement = connection.createStatement();
			int row = statement.executeUpdate(String.format(DELETE_AIRCRAFT_TYPE_BY_NAME, AircraftType));
			if (row != 0) {
				return true;
			}
			return false;
		} finally {
			connectionPool.releaseResourses(statement, rs, connection);
		}
	}

	private static boolean deleteAircraft(int idAircraft) throws ConnectionPoolException, SQLException {
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			connection = connectionPool.getConnection();
			statement = connection.createStatement();
			int row = statement.executeUpdate(String.format(DELETE_AIRCRAFT, idAircraft));
			if (row != 0) {
				return true;
			}
			return false;
		} finally {
			connectionPool.releaseResourses(statement, rs, connection);
		}
	}

	private static boolean deleteAircraftByRegistrationNumber(String registrationNumber)
			throws ConnectionPoolException, SQLException {
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			connection = connectionPool.getConnection();
			statement = connection.createStatement();
			int row = statement.executeUpdate(String.format(DELETE_AIRCRAFT_BY_REG_NUMBER, registrationNumber));
			if (row != 0) {
				return true;
			}
			return false;
		} finally {
			connectionPool.releaseResourses(statement, rs, connection);
		}
	}

}
