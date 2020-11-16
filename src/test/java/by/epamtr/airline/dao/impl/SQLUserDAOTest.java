package by.epamtr.airline.dao.impl;

import static org.junit.Assert.*;
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
import org.junit.Ignore;
import org.junit.Test;

import by.epamtr.airline.dao.AircraftStatusDAO;
import by.epamtr.airline.dao.SQLQueryConstant;
import by.epamtr.airline.dao.SQLTableConstant;
import by.epamtr.airline.dao.UserDAO;
import by.epamtr.airline.dao.connection_pool.ConnectionPool;
import by.epamtr.airline.dao.connection_pool.exception.ConnectionPoolException;
import by.epamtr.airline.dao.connection_pool.impl.ConnectionPoolImpl;
import by.epamtr.airline.dao.exception.DAOException;
import by.epamtr.airline.entity.Aircraft;
import by.epamtr.airline.entity.AircraftType;
import by.epamtr.airline.entity.Crew;
import by.epamtr.airline.entity.CrewPosition;
import by.epamtr.airline.entity.Flight;
import by.epamtr.airline.entity.User;
import by.epamtr.airline.entity.UserInfo;
import by.epamtr.airline.entity.UserRole;

public class SQLUserDAOTest {

	private final static UserDAO userDao = new SQLUserDAO();
	private static int count = 1;
	private static User testUserOne;
	private static User testUserTwo;
	private static User testUserThree;
	private static User testUserFour;
	private static User testUserFive;
	private static User testUserSix;
	private static UserInfo testUserInfoOne;
	private static UserInfo testUserInfoTwo;
	private static UserInfo testUserInfoThree;
	private static UserInfo testUserInfoFour;
	private static UserInfo testUserInfoFive;
	private static UserInfo testUserInfoSix;
	private static Crew testCrewOne;
	private static Crew testCrewTwo;
	private static Crew testCrewThree;
	private static Aircraft testAircraft;
	private static AircraftType testAircraftType;
	private static int idTestAircraftType = -1;
	private static int idTestAircraft = -1;
	private static Flight testFlightOne;
	private static Flight testFlightTwo;
	private static Flight testFlightThree;

	private static int idTestFlight = -1;
	private static int idTestUser = -1;
	private final static UserInfo testUserInfoNotExist = new UserInfo(1, "testLogin", "testPassword");
	private final static User testUserNotExist = new User(1, "testName", "testSurname", "testPatronimic",
			"test@mail.com", UserRole.ADMINISTRATOR);
	private final static UserInfo testUserInfoForAddOperation = new UserInfo(1, "newtestLogin", "testPassword");
	private final static User testUserForAddOperation = new User(1, "testName", "testSurname", "testPatronimic",
			"newtest@mail.com", UserRole.ADMINISTRATOR);
	private final static ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();
	private final static String DELETE_TEST_FLIGHT = "DELETE FROM flights WHERE id_flight=%d;";
	private final static String ADD_TEST_FLIGHT = " INSERT INTO flights (`current_city`, `destination_city`, `flight_range`, `flight_time`, `day_and_time_departure`, `aircraft`, `flight_status`) VALUES (?,?,?,?,?,?,?);";
	private final static String DELETE_ALL_CREW_FROM_FLIGHT = "DELETE FROM crews WHERE flight_id = %d;";
	private final static String DELETE_TEST_CREW_FROM_FLIGHT = "DELETE FROM crews WHERE flight_id = %d and crew_user_id = %d;";
	private final static String ADD_TEST_CREW_TO_FLIGHT = "INSERT INTO crews (`crew_position`,`flight_id`,`crew_user_id` ) VALUES (?,?,?);";
	private final static String ADD_TEST_USER = "INSERT INTO `users` (`name`,`surname`,`patronimic`,`e_mail`,`user_role`) VALUES (?,?,?,?,?)";
	private final static String ADD_TEST_USER_INFO = "INSERT INTO `users_info` (`user_id`,`login`,`password`) VALUES (?,?,?)";
	private final static String DELETE_TEST_USER = "DELETE FROM users WHERE id_user=%d;";
	private final static String DELETE_TEST_USER_INFO = "DELETE FROM users_info WHERE user_id =%d;";
	private final static String ADD_TEST_AIRCRAFT_TYPE = " INSERT INTO aircraft_types (`aircraft_type`, `range_flight`, `number_passengers`) VALUES (?,?,?) ;";
	private final static String ADD_TEST_AIRCRAFT = " INSERT INTO aircrafts (`type_aircraft`, `registration_number`, `status`) VALUES (?,?,?) ;";
	private final static String DELETE_TEST_AIRCRAFT_TYPE = "DELETE FROM aircraft_types WHERE id_aircraft_type =%d AND id_aircraft_type NOT IN (SELECT type_aircraft FROM aircrafts);";
	private final static String DELETE_TEST_AIRCRAFT = "DELETE FROM aircrafts WHERE id_aircraft=%d;";
	private final static String GET_USERS_BY_ROLE = "SELECT users.id_user, users.name, users.surname, users.patronimic, users.e_mail, user_roles.user_role  FROM users "
			+ "JOIN user_roles ON user_roles.id_user_role = users.user_role "
			+ "WHERE users.user_role=(SELECT id_user_role FROM user_roles WHERE user_roles.user_role='%s');";
	private final static String CURRENT_CITY = "TestOneCity";
	private final static String DESTINATION_CITY = "TestTwoCity";
	private final static String FLIGHT_STATUS = "COMPLITED";
	private final static String DATE_DEPARTURE = "2020-09-22 21:15:00.00";
	private final static String NAME = "TestName";
	private final static String SURNAME = "TestSurname";
	private final static String PATRONIMIC = "TestPatronimic";
	private final static String EMAIL = "testemail@mail.com";
	private final static String EMAIL_ONE = "testemail1@mail.com";
	private final static String EMAIL_TWO = "testemail2@mail.com";
	private final static String EMAIL_THREE = "testemail3@mail.com";
	private final static String EMAIL_FOUR = "testemail4@mail.com";
	private final static String EMAIL_FIVE = "testemail5@mail.com";
	private final static String EMAIL_SIX = "testemail6@mail.com";
	private final static String LOGIN = "testLogin";
	private final static String PASSWORD = "testPassword";
	private final static int USER_ROLE = 1;
	private final static String AIRCRAFT_TYPE = "testType";
	private final static int FLIGHT_RANGE = 5000;
	private final static int NUMBER_PASSENGERS = 100;
	private final static String REGISTRATION_NUMBER = "TEST-100";
	private final static String FIRST_PILOT = "Командир ВС";
	private final static String SECOND_PILOT = "Второй пилот";
	private final static int CREW_POSITION=6;
	private final static String UPDATED_NAME="updatedName";
	private final static String UPDATED_PASSWORD="updatedPassword";

	@BeforeClass
	public static void initializeData() throws ConnectionPoolException, SQLException {
		testAircraftType = addAircraftType();
		testAircraft = addAircraft(testAircraftType);
		testFlightOne = addFlight(testAircraft);
		testFlightTwo = addFlight(testAircraft);
		testFlightThree = addFlight(testAircraft);
		testUserOne = addTestUser(EMAIL_ONE);
		testUserInfoOne = addUserInfo(testUserOne.getIdUser());
		testUserTwo = addTestUser(EMAIL_TWO);
		testUserInfoTwo = addUserInfo(testUserTwo.getIdUser());
		testUserThree = addTestUser(EMAIL_THREE);
		testUserInfoThree = addUserInfo(testUserThree.getIdUser());
		testUserFour = addTestUser(EMAIL_FOUR);
		testUserInfoFour = addUserInfo(testUserFour.getIdUser());
		testUserFive = addTestUser(EMAIL_FIVE);
		testUserInfoFive = addUserInfo(testUserFive.getIdUser());
		testUserSix = addTestUser(EMAIL_SIX);
		testUserInfoSix = addUserInfo(testUserSix.getIdUser());

		CrewPosition captain = new CrewPosition(1, FIRST_PILOT);
		CrewPosition coPilot = new CrewPosition(2, SECOND_PILOT);
		testCrewOne = addCrewToFlight(testFlightOne.getIdFlight(), testUserOne, captain);
		testCrewTwo = addCrewToFlight(testFlightOne.getIdFlight(), testUserTwo, coPilot);
	}

	@AfterClass
	public static void deleteTestData() throws ConnectionPoolException, SQLException {
		Connection connection = connectionPool.getConnection();
		PreparedStatement statement = connection
				.prepareStatement("select user_id from users_info where login='newtestLogin';");
		ResultSet rs = statement.executeQuery();
		if (rs.next()) {
			int idTestUser = rs.getInt("user_id");
			testUserForAddOperation.setIdUser(idTestUser);
			testUserInfoForAddOperation.setIdUserInfo(testUserForAddOperation.getIdUser());
		}
		connectionPool.releaseResourses(statement, rs, connection);

		if (testAircraft != null) {
			deleteAircraft(testAircraft.getIdAircraft());
		}

		if (testAircraftType != null) {
			deleteAircraftType(testAircraftType.getIdAircraftType());
		}

		if (testFlightOne != null) {
			deleteCrewFromFlight(testFlightOne.getIdFlight(), testUserOne.getIdUser());
			deleteCrewFromFlight(testFlightOne.getIdFlight(), testUserTwo.getIdUser());
			deleteCrewFromFlight(testFlightOne.getIdFlight(), testUserThree.getIdUser());
			deleteFlight(testFlightOne.getIdFlight());
		}

		if (testFlightTwo != null) {
			deleteFlight(testFlightTwo.getIdFlight());
		}
		if (testFlightThree != null) {
			deleteFlight(testFlightThree.getIdFlight());
		}
		if (testUserOne != null) {
			deleteUser(testUserOne.getIdUser());
		}
		if (testUserTwo != null) {
			deleteUser(testUserTwo.getIdUser());
		}
		if (testUserThree != null) {
			deleteUser(testUserThree.getIdUser());
		}
		if (testUserFour != null) {
			deleteUser(testUserFour.getIdUser());
		}
		if (testUserFive != null) {
			deleteUser(testUserFive.getIdUser());
		}
		if (testUserSix != null) {
			deleteUser(testUserSix.getIdUser());
		}
		if (testUserForAddOperation != null) {
			deleteUser(testUserForAddOperation.getIdUser());
		}

	}

	@Test(timeout = 100)
	public void signInCorrectData() throws DAOException {
		User expectedUser = testUserOne;
		User actualUser = userDao.signIn(testUserInfoOne.getLogin(), testUserInfoOne.getPassword());
		assertEquals(expectedUser, actualUser);
	}
	
	@Test(timeout = 100)
	public void signInParameterIsNull() throws DAOException {
		User actualUser = userDao.signIn(null, testUserInfoOne.getPassword());
		assertNull(actualUser);
	}

	@Test(timeout = 100)
	public void signInUserNotExist() throws DAOException {
		User actualUser = userDao.signIn(testUserInfoNotExist.getLogin(), testUserInfoNotExist.getPassword());
		assertNull(actualUser);
	}

	@Test(timeout = 100)
	public void addUser() throws DAOException {
		boolean result = userDao.addUser(testUserForAddOperation, testUserInfoForAddOperation);
		assertTrue(result);
	}
	
	@Test(timeout = 100)
	public void addUserParameterIsNull() throws DAOException {
		boolean result = userDao.addUser(null, testUserInfoForAddOperation);
		assertFalse(result);
	}

	@Test(timeout = 100)
	public void addUserDuplicateLogin() throws DAOException {
		boolean result = userDao.addUser(testUserOne, testUserInfoOne);
		assertFalse(result);
	}

	@Test(timeout = 100)
	public void updateUserData() throws DAOException, ConnectionPoolException, SQLException {
		testUserOne.setName(UPDATED_NAME);
		testUserInfoOne.setPassword(UPDATED_PASSWORD);
		boolean result = userDao.updateUser(testUserOne, testUserInfoOne);
		assertTrue(result);
		User actualUser = userDao.getUser(testUserOne.getIdUser());
		UserInfo actualUserInfo = userDao.getUserInfo(testUserInfoOne.getIdUserInfo());
		assertEquals(testUserOne, actualUser);
		testUserInfoOne.setPassword(DigestUtils.md5Hex(testUserInfoOne.getPassword()));
		assertEquals(testUserInfoOne, actualUserInfo);
	}

	@Test(timeout = 100)
	public void updateUserDataParameterIsNull() throws DAOException, ConnectionPoolException, SQLException {
		boolean result = userDao.updateUser(null, testUserInfoOne);
		assertFalse(result);
	}
	
	@Test(timeout = 100)
	public void getUsersByUserRole() throws DAOException, SQLException, ConnectionPoolException {
		UserRole role = UserRole.PILOT;
		List<User> expectedUsers = new ArrayList<User>();
		List<User> users = userDao.getUsers(role);
		Connection connection = connectionPool.getConnection();
		PreparedStatement statement = connection.prepareStatement(String.format(GET_USERS_BY_ROLE, role.getRole()));
		ResultSet rs = statement.executeQuery();
		while (rs.next()) {
			expectedUsers.add(createUser(rs));
		}
		connectionPool.releaseResourses(statement, rs, connection);
		assertEquals(expectedUsers, users);
	}
	
	@Test(timeout = 100)
	public void getUsersByUserRoleParameterIsNull() throws DAOException, SQLException, ConnectionPoolException {
		List<User> users = userDao.getUsers(null);
		assertNull(users);
	}

	@Test(timeout = 100)
	public void getUsersByUserRoleDiffRole() throws DAOException, SQLException, ConnectionPoolException {
		UserRole role = UserRole.PILOT;
		UserRole otherRole = UserRole.ATTENDANT;
		List<User> expectedUsers = new ArrayList<User>();
		List<User> actualUsers = userDao.getUsers(role);
		Connection connection = connectionPool.getConnection();
		PreparedStatement statement = connection
				.prepareStatement(String.format(GET_USERS_BY_ROLE, otherRole.getRole()));
		ResultSet rs = statement.executeQuery();
		while (rs.next()) {
			expectedUsers.add(createUser(rs));
		}
		connectionPool.releaseResourses(statement, rs, connection);
		assertNotEquals(expectedUsers, actualUsers);
	}

	@Test(timeout = 100)
	public void getUsersByFlightId() throws ConnectionPoolException, SQLException, DAOException {
		List<Crew> listCrew = userDao.getUsers(testFlightOne.getIdFlight());
		assertNotNull(listCrew);
	}

	@Test(timeout = 100)
	public void getUsersByNotExistFlightId() throws ConnectionPoolException, SQLException, DAOException {
		ResultSet rs = null;
		Connection connection = connectionPool.getConnection();
		PreparedStatement statementDeleteFlight = connection
				.prepareStatement(String.format(DELETE_TEST_FLIGHT, testFlightTwo.getIdFlight()));
		int row = statementDeleteFlight.executeUpdate();
		connectionPool.releaseResourses(statementDeleteFlight, rs, connection);
		if (row != 0) {
			List<Crew> listCrew = userDao.getUsers(testFlightTwo.getIdFlight());
			assertTrue(listCrew.isEmpty());
		} else {
			assertTrue(false);
		}

	}

	@Test(timeout = 100)
	public void getUserById() throws ConnectionPoolException, SQLException, DAOException {
		User expectedUser = testUserOne;
		User actualUser = userDao.getUser(testUserOne.getIdUser());
		assertEquals(expectedUser, actualUser);
	}

	@Test(timeout = 100)
	public void getUserByNotExistId() throws ConnectionPoolException, SQLException, DAOException {
		ResultSet rs = null;
		Connection connection = connectionPool.getConnection();

		PreparedStatement testUserStatement = connection
				.prepareStatement(String.format(DELETE_TEST_USER, testUserSix.getIdUser()));
		int row = testUserStatement.executeUpdate();
		connectionPool.releaseResourses(testUserStatement, rs, connection);
		if (row != 0) {
			User actualUser = userDao.getUser(testUserSix.getIdUser());
			assertNull(actualUser);
		} else {
			assertTrue(false);
		}
	}

	@Test(timeout = 100)
	public void getUserInfoById() throws ConnectionPoolException, SQLException, DAOException {
		UserInfo expectedUserInfo = testUserInfoOne;
		UserInfo actualUserInfo = userDao.getUserInfo(testUserInfoOne.getIdUserInfo());
		assertEquals(expectedUserInfo, actualUserInfo);
	}

	@Test(timeout = 100)
	public void getUserInfoByNotExistId() throws ConnectionPoolException, SQLException, DAOException {
		ResultSet rs = null;
		Connection connection = connectionPool.getConnection();
		PreparedStatement testUserInfoStatement = connection
				.prepareStatement(String.format(DELETE_TEST_USER, testUserInfoFive.getIdUserInfo()));
		int row = testUserInfoStatement.executeUpdate();
		connectionPool.releaseResourses(testUserInfoStatement, rs, connection);
		if (row != 0) {
			UserInfo actualUserInfo = userDao.getUserInfo(idTestUser);
			assertNull(actualUserInfo);
		} else {
			assertTrue(false);
		}
	}

	@Test(timeout = 100)
	public void deleteCrewFromFlight() throws DAOException {
		boolean result = userDao.deliteCrewFromFlight(testFlightOne.getIdFlight(), testUserOne.getIdUser());
		assertTrue(result);
	}

	@Test(timeout = 100)
	public void deleteNotExistCrewFromFlight() throws DAOException, SQLException, ConnectionPoolException {
		boolean result = userDao.deliteCrewFromFlight(testFlightOne.getIdFlight(), testUserFour.getIdUser());
		assertFalse(result);
	}

	@Test(timeout = 100)
	public void addCrewToFlight() throws DAOException {
		boolean result = userDao.addCrewToFlight(CREW_POSITION, testFlightOne.getIdFlight(), testUserThree.getIdUser());
		assertTrue(result);
	}

	private User createUser(ResultSet resultSet) throws SQLException {
		int idUser = resultSet.getInt(SQLTableConstant.User.ID_USER);
		String name = resultSet.getString(SQLTableConstant.User.NAME);
		String surname = resultSet.getString(SQLTableConstant.User.SURNAME);
		String patronimic = resultSet.getString(SQLTableConstant.User.PATRONIMIC);
		String email = resultSet.getString(SQLTableConstant.User.E_MAIL);
		UserRole userRole = UserRole.valueOf(resultSet.getString("user_roles.user_role"));
		return new User(idUser, name, surname, patronimic, email, userRole);
	}

	private static AircraftType addAircraftType() throws SQLException, ConnectionPoolException {
		ResultSet rs = null;
		Connection connection = connectionPool.getConnection();
		PreparedStatement statementTestAircraftType = connection.prepareStatement(ADD_TEST_AIRCRAFT_TYPE,
				Statement.RETURN_GENERATED_KEYS);
		statementTestAircraftType.setString(1, AIRCRAFT_TYPE);
		statementTestAircraftType.setInt(2, FLIGHT_RANGE);
		statementTestAircraftType.setInt(3, NUMBER_PASSENGERS);
		int row = statementTestAircraftType.executeUpdate();
		if (row != 0) {
			rs = statementTestAircraftType.getGeneratedKeys();
			rs.next();
			idTestAircraftType = rs.getInt(1);
		}
		connectionPool.releaseResourses(statementTestAircraftType, rs, connection);
		AircraftType type = new AircraftType(idTestAircraftType, AIRCRAFT_TYPE, FLIGHT_RANGE, NUMBER_PASSENGERS);
		return type;
	}

	private static Aircraft addAircraft(AircraftType aircraftType) throws SQLException, ConnectionPoolException {
		ResultSet rs = null;
		Connection connection = connectionPool.getConnection();
		PreparedStatement statementTestAircraft = connection.prepareStatement(ADD_TEST_AIRCRAFT,
				Statement.RETURN_GENERATED_KEYS);
		statementTestAircraft.setInt(1, aircraftType.getIdAircraftType());
		statementTestAircraft.setString(2, REGISTRATION_NUMBER);
		statementTestAircraft.setString(3, AircraftStatusDAO.MAINTENANCE.getStatus());
		int row = statementTestAircraft.executeUpdate();
		if (row != 0) {
			rs = statementTestAircraft.getGeneratedKeys();
			rs.next();
			idTestAircraft = rs.getInt(1);
			testAircraft = new Aircraft(idTestAircraft, aircraftType, REGISTRATION_NUMBER,
					AircraftStatusDAO.MAINTENANCE.getStatus());
		}
		connectionPool.releaseResourses(statementTestAircraft, rs, connection);
		return testAircraft;
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
		if (row != 0) {
			rs = statementAddFlight.getGeneratedKeys();
			rs.next();
			flight.setIdFlight(rs.getInt(1));
		}
		connectionPool.releaseResourses(statementAddFlight, rs, connection);
		return flight;
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

	private static boolean deleteAircraftType(int idAircraftType) throws SQLException, ConnectionPoolException {
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		boolean result = false;
		try {
			connection = connectionPool.getConnection();
			statement = connection.createStatement();
			int row = statement.executeUpdate(String.format(DELETE_TEST_AIRCRAFT_TYPE, idAircraftType));
			if (row != 0) {
				result = true;
			}
		} finally {
			connectionPool.releaseResourses(statement, rs, connection);
		}
		return result;
	}

	private static boolean deleteAircraft(int idAircraft) throws ConnectionPoolException, SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		boolean result = false;
		try {
			connection = connectionPool.getConnection();
			statement = connection.prepareStatement(String.format(DELETE_TEST_AIRCRAFT, idAircraft));
			int row = statement.executeUpdate();
			if (row != 0) {
				result = true;
			}
		} finally {
			connectionPool.releaseResourses(statement, rs, connection);
		}
		return result;
	}

	private static Crew addCrewToFlight(int idFlight, User user, CrewPosition crewPosition)
			throws SQLException, ConnectionPoolException {
		Connection connection = null;
		PreparedStatement statement = null;
		boolean result = false;
		Crew crew = new Crew(idFlight, user, crewPosition.getCrewPosition());
		try {
			connection = connectionPool.getConnection();
			statement = connection.prepareStatement(SQLQueryConstant.CONSTRAINT_DISABLE);
			statement.executeQuery();
			statement.close();
			statement = connection.prepareStatement(ADD_TEST_CREW_TO_FLIGHT);
			statement.setInt(1, crewPosition.getIdCrewPosition());
			statement.setInt(2, idFlight);
			statement.setInt(3, user.getIdUser());
			int i = statement.executeUpdate();
			if (i != 0) {
				result = true;
			}
			statement = connection.prepareStatement(SQLQueryConstant.CONSTRAINT_ENABLE);
			statement.executeQuery();
		} finally {
			connectionPool.releaseResourses(statement, null, connection);
		}
		return crew;
	}

	private static boolean deleteCrewFromFlight(int idFlight, int idUser) throws SQLException, ConnectionPoolException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		boolean result = false;
		try {
			connection = connectionPool.getConnection();

			statement = connection.prepareStatement(String.format(DELETE_TEST_CREW_FROM_FLIGHT, idFlight, idUser));
			int row = statement.executeUpdate();
			if (row != 0) {
				result = true;
			}
		} finally {
			connectionPool.releaseResourses(statement, rs, connection);
		}
		return result;
	}
}
