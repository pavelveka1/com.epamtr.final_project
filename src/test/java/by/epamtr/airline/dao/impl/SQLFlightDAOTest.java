package by.epamtr.airline.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import by.epamtr.airline.dao.AircraftDAO;
import by.epamtr.airline.dao.AircraftStatusDAO;
import by.epamtr.airline.dao.FlightDAO;
import by.epamtr.airline.dao.SQLQueryConstant;
import by.epamtr.airline.dao.SQLTableConstant;
import by.epamtr.airline.dao.UserDAO;
import by.epamtr.airline.dao.connection_pool.ConnectionPool;
import by.epamtr.airline.dao.connection_pool.exception.ConnectionPoolException;
import by.epamtr.airline.dao.connection_pool.impl.ConnectionPoolImpl;
import by.epamtr.airline.dao.exception.DAOException;
import by.epamtr.airline.entity.Aircraft;
import by.epamtr.airline.entity.AircraftType;
import by.epamtr.airline.entity.CrewPosition;
import by.epamtr.airline.entity.Flight;
import by.epamtr.airline.entity.FlightStatus;
import by.epamtr.airline.entity.User;
import by.epamtr.airline.entity.UserInfo;
import by.epamtr.airline.entity.UserRole;

public class SQLFlightDAOTest {
	private static AircraftType testAircraftTypeOne;
	private static Aircraft testAircraftOne;
	private static User testUserOne;
	private static User testUserTwo;
	private static UserInfo testUserInfoOne;
	private static UserInfo testUserInfoTwo;
	private static Flight testFlight;
	private static Flight testFlightOne;
	private static Flight testFlightTwo;
	private static Flight testFlightThree;
	private static Flight testFlightFour;
	private static Flight testFlightFive;
	private static Flight testFlightSix;
	private static Flight testFlightSeven;
	private static Flight testFlightEight;
	private final static ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();
	private static final String ADD_AIRCRAFT_TYPE = " INSERT INTO aircraft_types (`aircraft_type`, `range_flight`, `number_passengers`) VALUES (?,?,?) ;";
	private static final String ADD_AIRCRAFT = " INSERT INTO aircrafts (`type_aircraft`, `registration_number`, `status`) VALUES (?,?,?) ;";
	public static final String DELETE_AIRCRAFT_TYPE = "DELETE FROM aircraft_types WHERE id_aircraft_type =%d ;";
	public static final String DELETE_AIRCRAFT = " DELETE FROM aircrafts WHERE id_aircraft =%d;";
	private final static String ADD_TEST_USER = "INSERT INTO `users` (`name`,`surname`,`patronimic`,`e_mail`,`user_role`) VALUES (?,?,?,?,?)";
	private final static String ADD_TEST_USER_INFO = "INSERT INTO `users_info` (`user_id`,`login`,`password`) VALUES (?,?,?)";
	private final static String DELETE_TEST_USER = "DELETE FROM users WHERE id_user=%d;";
	private final static String DELETE_TEST_USER_INFO = "DELETE FROM users_info WHERE user_id =%d;";
	private final static String DELETE_TEST_FLIGHT = "DELETE FROM flights WHERE id_flight=%d;";
	private final static String ADD_TEST_FLIGHT = " INSERT INTO flights (`current_city`, `destination_city`, `flight_range`, `flight_time`, `day_and_time_departure`, `aircraft`, `flight_status`) VALUES (?,?,?,?,?,?,?);";
	private final static String DELETE_ALL_CREW_FROM_FLIGHT = "DELETE FROM crews WHERE flight_id = %d;";
	private static final String GET_FLIGHTS_BY_USER_ID = "SELECT id_flight, current_city, destination_city, flight_range, flight_time, day_and_time_departure, aircraft_types.aircraft_type, aircrafts.registration_number, flight_status FROM flights\r\n"
			+ "				   JOIN aircrafts ON aircrafts.id_aircraft = flights.aircraft\r\n"
			+ "				   JOIN aircraft_types ON aircraft_types.id_aircraft_type = aircrafts.type_aircraft\r\n"
			+ "				WHERE id_flight IN (SELECT flight_id FROM crews WHERE crew_user_id= %d);";
	private static final String GET_FLIGHTS_BY_STATUS = "SELECT id_flight, current_city, destination_city, flight_range, flight_time, day_and_time_departure, aircraft_types.aircraft_type, aircrafts.registration_number, flight_status FROM flights\r\n"
			+ "   JOIN aircrafts ON aircrafts.id_aircraft = flights.aircraft\r\n"
			+ "   JOIN aircraft_types ON aircraft_types.id_aircraft_type = aircrafts.type_aircraft;";
	private static final String GET_FREE_POSITIONS_BY_FLIGHT_ID = "select * from crew_positions where id_crew_position NOT IN (SELECT crew_position FROM crews WHERE flight_id = %d);";

	private final static AircraftDAO aircraftDAO = new SQLAircraftDAO();
	private final static FlightDAO flightDAO = new SQLFlightDAO();
	private final static UserDAO userDAO = new SQLUserDAO();
	private static int count = 1;
	private static final String ID_FLIGHT_COLUMN = "id_flight";
	private static final String CURRENT_CITY_COLUMN = "current_city";
	private static final String DESTINATION_CITY_COLUMN = "destination_city";
	private static final String FLIGHT_RANGE_COLUMN = "flight_range";
	private static final String FLIGHT_TIME_COLUMN = "flight_time";
	private static final String DAY_AND_TIME_DEPARTURE_COLUMN = "day_and_time_departure";
	private static final String AIRCRAFT_TYPE_COLUMN = "aircraft_type";
	private static final String REGISTRATION_NUMBER_COLUMN = "registration_number";
	private static final String FLIGHT_STATUS_COLUMN = "flight_status";
	private static final String ID_CREW_POSITION_COLUMN = "id_crew_position";
	private static final String CREW_POSITION_COLUMN = "crew_position";
	private static final String REGISTRATION_NUMBER = "test";
	private static final int RANGE_FLIGHT = 5000;
	private static final int NUMBER_PASSENGERS = 100;
	private static final String AIRCRAFT_TYPE = "TestAircraftType";
	private final static String NAME = "TestName";
	private final static String SURNAME = "TestSurname";
	private final static String PATRONIMIC = "TestPatronimic";
	private final static String CURRENT_CITY_FOR_ADD_OPERATION = "testCityForAdd";
	private final static String NEW_TEST_CURRENT_CITY = "NewCurrentCity";
	private final static String CURRENT_CITY = "TestOneCity";
	private final static String DESTINATION_CITY = "TestTwoCity";
	private final static String FLIGHT_STATUS = "COMPLITED";
	private final static String DATE_DEPARTURE = "2020-09-22 21:15:00";
	private final static String EMAIL_ONE = "testemail1@mail.com";
	private final static String EMAIL_TWO = "testemail2@mail.com";
	private final static String LOGIN = "testLogin";
	private final static String PASSWORD = "testPassword";
	private final static int FLIGHT_RANGE = 5000;
	private final static int FLIGHT_TIME = 120;
	private final static int USER_ROLE = 1;

	@BeforeClass
	public static void initializeData() throws ConnectionPoolException, SQLException {

		testAircraftTypeOne = makeAircraftType();
		testAircraftOne = makeAircraft(testAircraftTypeOne);

		testUserOne = addTestUser(EMAIL_ONE);
		testUserInfoOne = addUserInfo(testUserOne.getIdUser());
		testUserTwo = addTestUser(EMAIL_TWO);
		testUserInfoTwo = addUserInfo(testUserTwo.getIdUser());

		testFlight = new Flight(CURRENT_CITY_FOR_ADD_OPERATION, DESTINATION_CITY, FLIGHT_RANGE, FLIGHT_TIME,
				DATE_DEPARTURE, testAircraftOne.getRegisterNumber(), FLIGHT_STATUS);
		testFlightOne = addFlight(testAircraftOne);
		testFlightTwo = addFlight(testAircraftOne);
		testFlightThree = addFlight(testAircraftOne);
		testFlightFour = addFlight(testAircraftOne);
		testFlightFive = addFlight(testAircraftOne);
		testFlightSix = addFlight(testAircraftOne);
		testFlightSeven = addFlight(testAircraftOne);
		testFlightEight = addFlight(testAircraftOne);

	}

	@AfterClass
	public static void deleteTestData() throws ConnectionPoolException, SQLException {
		Connection connection = connectionPool.getConnection();
		PreparedStatement statement = connection
				.prepareStatement("select id_flight from flights where current_city='testCityForAdd';");
		ResultSet rs = statement.executeQuery();
		if (rs.next()) {
			int idTestFlight = rs.getInt("id_flight");
			testFlight.setIdFlight(idTestFlight);
		}
		connectionPool.releaseResourses(statement, rs, connection);

		if (testAircraftOne != null) {
			deleteAircraft(testAircraftOne.getIdAircraft());
		}

		if (testAircraftTypeOne != null) {
			deleteAircraftType(testAircraftTypeOne.getIdAircraftType());
		}

		if (testFlightOne != null) {
			deleteFlight(testFlightOne.getIdFlight());
		}

		if (testFlightTwo != null) {
			deleteFlight(testFlightTwo.getIdFlight());
		}
		if (testFlightThree != null) {
			deleteFlight(testFlightThree.getIdFlight());
		}
		if (testFlightFour != null) {
			deleteFlight(testFlightFour.getIdFlight());
		}
		if (testFlightFive != null) {
			deleteFlight(testFlightFive.getIdFlight());
		}
		if (testFlightSix != null) {
			deleteFlight(testFlightSix.getIdFlight());
		}
		if (testFlightSeven != null) {
			deleteFlight(testFlightSeven.getIdFlight());
		}
		if (testFlightEight != null) {
			deleteFlight(testFlightEight.getIdFlight());
		}

		if (testFlight != null) {
			deleteFlight(testFlight.getIdFlight());
		}

		if (testUserOne != null) {
			deleteUser(testUserOne.getIdUser());
		}
		if (testUserTwo != null) {
			deleteUser(testUserTwo.getIdUser());
		}

	}

	@Test(timeout = 100)
	public void addFlight() throws DAOException {
		boolean result = flightDAO.addFlight(testFlight, testAircraftOne);
		assertTrue(result);
	}

	@Test(timeout = 100)
	public void addFlightIncorrectData() throws DAOException {
		boolean result = flightDAO.addFlight(null, testAircraftOne);
		assertFalse(result);
	}

	@Test(timeout = 100)
	public void deleteFlightById() throws DAOException {
		boolean result = flightDAO.deliteFlight(testFlightOne.getIdFlight());
		assertTrue(result);
	}

	@Test(timeout = 100)
	public void deleteFlightByNotExistId() throws SQLException, ConnectionPoolException, DAOException {
		deleteFlight(testFlightTwo.getIdFlight());
		boolean result = flightDAO.deliteFlight(testFlightTwo.getIdFlight());
	}

	@Test(timeout = 100)
	public void updateFlight() throws DAOException {
		testFlightThree.setCurrentCity(NEW_TEST_CURRENT_CITY);
		Flight actualFlight = flightDAO.updateFlight(testFlightThree.getIdFlight(), testFlightThree);
		assertEquals(testFlightThree, actualFlight);
	}

	@Test(expected = DAOException.class)
	public void updateFlightParameterIsNull() throws DAOException {
		Flight actualFlight = flightDAO.updateFlight(testFlightThree.getIdFlight(), null);
	}

	@Test(timeout = 150)
	public void updateFlightByNotExistId() throws DAOException, SQLException, ConnectionPoolException {
		deleteFlight(testFlightFour.getIdFlight());
		Flight actualFlight = flightDAO.updateFlight(testFlightFour.getIdFlight(), testFlightFour);
		assertNull(actualFlight);
	}

	@Test(timeout = 100)
	public void changeFlightStatus() throws DAOException {
		boolean result = flightDAO.changeFlightStatus(testFlightThree.getIdFlight(), FlightStatus.RECRUITMENT);
		assertTrue(result);
	}

	@Test(timeout = 100)
	public void changeFlightStatusByNotExistIdFlight() throws DAOException, SQLException, ConnectionPoolException {
		deleteFlight(testFlightFive.getIdFlight());
		boolean result = flightDAO.changeFlightStatus(testFlightFive.getIdFlight(), FlightStatus.RECRUITMENT);
		assertFalse(result);
	}

	@Test(timeout = 100)
	public void getFlightById() throws DAOException {
		Flight actualFlight = flightDAO.getFlight(testFlightSix.getIdFlight());
		assertEquals(testFlightSix, actualFlight);
	}

	@Test(timeout = 100)
	public void getFlightByNotExistId() throws SQLException, ConnectionPoolException, DAOException {
		deleteFlight(testFlightSeven.getIdFlight());
		Flight actualFlight = flightDAO.getFlight(testFlightSeven.getIdFlight());
		assertNull(actualFlight);
	}

	@Test(timeout = 100)
	public void getFlightsByUserId() throws ConnectionPoolException, SQLException, DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<Flight> expectedFlights = new ArrayList<Flight>();
		connection = connectionPool.getConnection();
		statement = connection.prepareStatement(String.format(GET_FLIGHTS_BY_USER_ID, testUserOne.getIdUser()));
		rs = statement.executeQuery();
		while (rs.next()) {
			expectedFlights.add(createFlight(rs));
		}
		connectionPool.releaseResourses(statement, rs, connection);
		List<Flight> actualFlights = flightDAO.getFlights(testUserOne.getIdUser());
		assertEquals(expectedFlights, actualFlights);
	}

	@Test(timeout = 100)
	public void getFlightsByNotExistUserId() throws ConnectionPoolException, SQLException, DAOException {
		deleteUser(testUserTwo.getIdUser());
		List<Flight> actualFlights = flightDAO.getFlights(testUserTwo.getIdUser());
		List<Flight> expectedFlights = new ArrayList<Flight>();
		assertEquals(expectedFlights, actualFlights);
	}

	@Test(timeout = 100)
	public void getFlightsByFlightStatus() throws ConnectionPoolException, SQLException, DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<Flight> expectedFlights = new ArrayList<Flight>();
		connection = connectionPool.getConnection();
		statement = connection.prepareStatement(SQLQueryConstant.FlightConstant.GET_FLIGHTS_BY_STATUS);
		rs = statement.executeQuery();
		while (rs.next()) {
			if (FlightStatus.COMPLITED == FlightStatus.valueOf(rs.getString(SQLTableConstant.Flight.FLIGHT_STATUS))) {
				expectedFlights.add(createFlight(rs));
			}
		}
		connectionPool.releaseResourses(statement, rs, connection);
		List<Flight> actualFlights = flightDAO.getFlights(FlightStatus.COMPLITED);
		assertEquals(expectedFlights, actualFlights);
	}

	@Test(timeout = 100)
	public void getFlightsByFlightDifferentStatus() throws ConnectionPoolException, SQLException, DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<Flight> expectedFlights = new ArrayList<Flight>();
		connection = connectionPool.getConnection();
		statement = connection.prepareStatement(SQLQueryConstant.FlightConstant.GET_FLIGHTS_BY_STATUS);
		rs = statement.executeQuery();
		while (rs.next()) {
			if (FlightStatus.COMPLITED == FlightStatus.valueOf(rs.getString(SQLTableConstant.Flight.FLIGHT_STATUS))) {
				expectedFlights.add(createFlight(rs));
			}
		}
		connectionPool.releaseResourses(statement, rs, connection);
		List<Flight> actualFlights = flightDAO.getFlights(FlightStatus.CREATED);
		assertNotEquals(expectedFlights, actualFlights);
	}

	@Test(timeout = 100)
	public void getFreeCrewPositionsByIdFlight() throws SQLException, ConnectionPoolException, DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<CrewPosition> freeCrewPositions = new ArrayList<CrewPosition>();
		deleteFlight(testFlightEight.getIdFlight());
		connection = connectionPool.getConnection();
		statement = connection.prepareStatement(String.format(
				SQLQueryConstant.FlightConstant.GET_FREE_POSITIONS_BY_FLIGHT_ID, testFlightEight.getIdFlight()));
		rs = statement.executeQuery();
		while (rs.next()) {
			freeCrewPositions.add(createCrewPosition(rs));
		}
		connectionPool.releaseResourses(statement, rs, connection);
		List<CrewPosition> actualFreeCrewPositions = flightDAO.getFreeCrewPositions(testFlightEight.getIdFlight());
		assertEquals(freeCrewPositions, actualFreeCrewPositions);
	}

	@Test(timeout = 100)
	public void getFreeCrewPositionsByNotExistIdFlight() throws SQLException, ConnectionPoolException, DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<CrewPosition> freeCrewPositions = new ArrayList<CrewPosition>();
		connection = connectionPool.getConnection();
		statement = connection.prepareStatement(String.format(
				SQLQueryConstant.FlightConstant.GET_FREE_POSITIONS_BY_FLIGHT_ID, testFlightThree.getIdFlight()));
		rs = statement.executeQuery();
		while (rs.next()) {
			freeCrewPositions.add(createCrewPosition(rs));
		}
		connectionPool.releaseResourses(statement, rs, connection);
		List<CrewPosition> actualFreeCrewPositions = flightDAO.getFreeCrewPositions(testFlightThree.getIdFlight());
		assertEquals(freeCrewPositions, actualFreeCrewPositions);
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

	private static User addTestUser(String email) throws SQLException, ConnectionPoolException {
		User user = null;
		ResultSet rs = null;
		Connection connection = connectionPool.getConnection();
		PreparedStatement statementAddUser = connection.prepareStatement(ADD_TEST_USER,
				Statement.RETURN_GENERATED_KEYS);
		statementAddUser.setString(1, NAME);
		statementAddUser.setString(2, SURNAME);
		statementAddUser.setString(3, PATRONIMIC);
		statementAddUser.setString(4, email);
		statementAddUser.setInt(5, 3);
		int row = statementAddUser.executeUpdate();

		if (row != 0) {
			rs = statementAddUser.getGeneratedKeys();
			rs.next();
			user = new User();
			user.setName(NAME);
			user.setSurname(SURNAME);
			user.setPatronimic(PATRONIMIC);
			user.setEmail(email);
			user.setRole(UserRole.ADMINISTRATOR);
			user.setIdUser(rs.getInt(1));
		}
		connectionPool.releaseResourses(statementAddUser, rs, connection);
		return user;
	}

	private static UserInfo addUserInfo(int idUser) throws SQLException, ConnectionPoolException {
		String login = LOGIN + count++;
		UserInfo userInfo = null;
		ResultSet rs = null;
		Connection connection = connectionPool.getConnection();
		PreparedStatement statementAddUserInfo = connection.prepareStatement(ADD_TEST_USER_INFO);
		statementAddUserInfo.setInt(1, idUser);
		statementAddUserInfo.setString(2, login);
		statementAddUserInfo.setString(3, DigestUtils.md5Hex(PASSWORD));
		statementAddUserInfo.executeUpdate();
		connectionPool.releaseResourses(statementAddUserInfo, rs, connection);
		userInfo = new UserInfo();
		userInfo.setIdUserInfo(idUser);
		userInfo.setLogin(login);
		userInfo.setPassword(PASSWORD);

		return userInfo;
	}

	private static boolean deleteUser(int idUser) throws SQLException, ConnectionPoolException {
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			connection = connectionPool.getConnection();
			statement = connection.createStatement();
			connection.setAutoCommit(false);
			statement.executeUpdate(SQLQueryConstant.CONSTRAINT_DISABLE);
			int deletedUsers = statement.executeUpdate(String.format(DELETE_TEST_USER_INFO, idUser));
			int deletedInfo = statement.executeUpdate(String.format(DELETE_TEST_USER, idUser));
			statement.executeUpdate(SQLQueryConstant.CONSTRAINT_ENABLE);
			connection.commit();
			connection.setAutoCommit(true);
			if (deletedUsers != 0 && deletedInfo != 0) {
				return true;
			}
			return false;

		} finally {

			connectionPool.releaseResourses(statement, rs, connection);

		}
	}

	private static Flight addFlight(Aircraft aircraft) throws SQLException, ConnectionPoolException {
		Flight flight;
		ResultSet rs = null;
		Connection connection = connectionPool.getConnection();
		PreparedStatement statementAddFlight = connection.prepareStatement(ADD_TEST_FLIGHT,
				Statement.RETURN_GENERATED_KEYS);
		statementAddFlight.setString(1, CURRENT_CITY);
		statementAddFlight.setString(2, DESTINATION_CITY);
		statementAddFlight.setInt(3, 5000);
		statementAddFlight.setInt(4, 200);
		statementAddFlight.setString(5, DATE_DEPARTURE);
		statementAddFlight.setInt(6, aircraft.getIdAircraft());
		statementAddFlight.setString(7, FLIGHT_STATUS);
		int row = statementAddFlight.executeUpdate();
		flight = new Flight(CURRENT_CITY, DESTINATION_CITY, 5000, 200, DATE_DEPARTURE, aircraft.getRegisterNumber(),
				FLIGHT_STATUS);
		flight.setAircraftType(aircraft.getType().getAircraftType());
		if (row != 0) {
			rs = statementAddFlight.getGeneratedKeys();
			rs.next();
			flight.setIdFlight(rs.getInt(1));
		}
		connectionPool.releaseResourses(statementAddFlight, rs, connection);
		return flight;
	}

	private static boolean deleteFlight(int idFlight) throws SQLException, ConnectionPoolException {
		Connection connection = null;
		PreparedStatement statementDeleteFlight = null;
		PreparedStatement statementDeleteCrews = null;
		ResultSet rs = null;
		boolean result = false;
		try {
			connection = connectionPool.getConnection();

			statementDeleteFlight = connection
					.prepareStatement(String.format(String.format(DELETE_TEST_FLIGHT, idFlight)));
			int row = statementDeleteFlight.executeUpdate();
			statementDeleteCrews = connection.prepareStatement(String.format(DELETE_ALL_CREW_FROM_FLIGHT, idFlight));
			statementDeleteCrews.executeUpdate();
			if (row != 0) {
				result = true;
			}
		} finally {
			connectionPool.releaseResourses(statementDeleteFlight, statementDeleteCrews, null, null, connection);
		}
		return result;
	}

	private Flight createFlight(ResultSet resultSet) throws SQLException {
		int idFlight = resultSet.getInt(ID_FLIGHT_COLUMN);
		String currentCity = resultSet.getString(CURRENT_CITY_COLUMN);
		String destinationCity = resultSet.getString(DESTINATION_CITY_COLUMN);
		int flightRange = resultSet.getInt(FLIGHT_RANGE_COLUMN);
		int flightTime = resultSet.getInt(FLIGHT_TIME_COLUMN);
		String timeDeparture = resultSet.getString(DAY_AND_TIME_DEPARTURE_COLUMN);
		String aircraftType = resultSet.getString(AIRCRAFT_TYPE_COLUMN);
		String aircraftNumber = resultSet.getString(REGISTRATION_NUMBER_COLUMN);
		String flightStatus = resultSet.getString(FLIGHT_STATUS_COLUMN);

		Flight flight = new Flight(idFlight, currentCity, destinationCity, flightRange, flightTime, timeDeparture,
				aircraftType, aircraftNumber, flightStatus);
		return flight;
	}

	private CrewPosition createCrewPosition(ResultSet resultSet) throws SQLException {
		int idCrewPosition = resultSet.getInt(ID_CREW_POSITION_COLUMN);
		String crewPositionName = resultSet.getString(CREW_POSITION_COLUMN);
		CrewPosition crewPosition = new CrewPosition(idCrewPosition, crewPositionName);
		return crewPosition;
	}
}
