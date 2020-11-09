package by.epamtr.airline.dao.impl;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.mysql.cj.xdevapi.Statement;

import by.epamtr.airline.dao.AircraftDAO;
import by.epamtr.airline.dao.AircraftStatusDAO;
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
import java.util.ArrayList;
import java.util.List;

import org.junit.After;

public class SQLAircraftDAOTest {

	private final AircraftDAO aircraftDao = new SQLAircraftDAO();
	private AircraftType aircraftType = new AircraftType(1, "Embraer 195", 4200, 124);
	private AircraftType testAircraftType = new AircraftType(1, "Boeing 545", 4200, 124);
	private Aircraft aircraft = new Aircraft(1000000, aircraftType, "EW-881PM",
			AircraftStatusDAO.SERVICEABLE.getStatus());

	@After
	public void deleteAddedEntity() throws DAOException, SQLException, ConnectionPoolException {
		aircraftDao.deleteAircraft(aircraft.getRegisterNumber());
		Connection connection = null;
		connection = ConnectionPoolImpl.getInstance().getConnection();
		PreparedStatement statement = connection
				.prepareStatement("select id_aircraft_type from aircraft_types where aircraft_type='Boeing 545';");
		ConnectionPoolImpl.getInstance().releaseConnection(connection);
		ResultSet rs = statement.executeQuery();
		if (rs.next()) {
			int idAircraftType = rs.getInt("id_aircraft_type");
			aircraftDao.deliteAircraftType(idAircraftType);
		}
	}

	@Test
	public void addAircraftCorrect() throws DAOException {
		boolean result = aircraftDao.addAircraft(aircraft, aircraftType);
		assertTrue(result);
	}

	@Test(expected = DAOException.class)
	public void addAircraftDuplicate() throws DAOException {
		boolean firstAttempt = aircraftDao.addAircraft(aircraft, aircraftType);
		boolean secondAttempt = aircraftDao.addAircraft(aircraft, aircraftType);
		assertFalse(secondAttempt);
	}

	@Test
	public void addAircraftType() throws DAOException {
		boolean result = aircraftDao.addAircraftType(testAircraftType);
		assertTrue(result);
	}

	@Test(expected = DAOException.class)
	public void addAircraftTypeDuplicate() throws DAOException {
		boolean firstAttempt = aircraftDao.addAircraftType(aircraftType);
		boolean secondAttempt = aircraftDao.addAircraftType(aircraftType);
		assertTrue(secondAttempt);
	}

	@Test
	public void deleteAircraftExisting() throws DAOException {
		aircraftDao.addAircraft(aircraft, aircraftType);
		String registrationNumber = aircraft.getRegisterNumber();
		boolean result = aircraftDao.deleteAircraft(registrationNumber);
		assertTrue(result);
	}

	@Test
	public void deleteAircraftNotExisting() throws DAOException {
		String nonExistentRegistrationNumber = "Number-100";
		boolean result = aircraftDao.deleteAircraft(nonExistentRegistrationNumber);
		assertFalse(result);
	}

	@Test
	public void deleteAircraftTypeByExistingId() throws DAOException, ConnectionPoolException, SQLException {
		aircraftDao.addAircraftType(testAircraftType);
		Connection connection = null;
		connection = ConnectionPoolImpl.getInstance().getConnection();
		PreparedStatement statement = connection
				.prepareStatement("select id_aircraft_type from aircraft_types where aircraft_type='Boeing 545';");
		ConnectionPoolImpl.getInstance().releaseConnection(connection);
		ResultSet rs = statement.executeQuery();
		int idAircraftType = 0;
		if (rs.next()) {
			idAircraftType = rs.getInt("id_aircraft_type");
		}
		boolean result = aircraftDao.deliteAircraftType(idAircraftType);
		assertTrue(result);
	}

	@Test
	public void deleteAircraftTypeByNonExistingId() throws DAOException {
		int nonExistingId = Integer.MAX_VALUE;
		boolean result = aircraftDao.deliteAircraftType(nonExistingId);
		assertFalse(result);
	}

	@Test
	public void updateAircraft() throws DAOException {
		aircraftDao.addAircraft(aircraft, aircraftType);
		String registrationNumber = aircraft.getRegisterNumber();
		String newRegistrationNumber = "RA-98956";
		boolean result = aircraftDao.updateAircraft(registrationNumber, newRegistrationNumber);
		assertTrue(result);
		aircraftDao.updateAircraft(newRegistrationNumber, registrationNumber);
	}

	@Ignore
	public void updateAircraftExistingNumber() throws DAOException {
		aircraftDao.addAircraft(aircraft, aircraftType);
		String registrationNumber = aircraft.getRegisterNumber();
		String ExistingRegistrationNumber = aircraft.getRegisterNumber();
		boolean result = aircraftDao.updateAircraft(registrationNumber, ExistingRegistrationNumber);
		assertFalse(result);
	}

	@Test
	public void changeAircraftStatus() throws DAOException, ConnectionPoolException, SQLException {
		aircraftDao.addAircraft(aircraft, aircraftType);
		String status = aircraft.getStatus();
		Connection connection = null;
		connection = ConnectionPoolImpl.getInstance().getConnection();
		PreparedStatement statement = connection
				.prepareStatement("select id_aircraft from aircrafts where registration_number='EW-881PM';");
		ConnectionPoolImpl.getInstance().releaseConnection(connection);
		ResultSet rs = statement.executeQuery();
		int idAircraft = 0;
		if (rs.next()) {
			idAircraft = rs.getInt("id_aircraft");
		}
		boolean result = aircraftDao.changeAircraftStatus(idAircraft, status);
		assertTrue(result);
	}

	@Test
	public void changeAircraftStatusIdNotExist() throws DAOException, ConnectionPoolException, SQLException {
		aircraftDao.addAircraft(aircraft, aircraftType);
		String status = aircraft.getStatus();
		int idAircraft = Integer.MAX_VALUE;
		boolean result = aircraftDao.changeAircraftStatus(idAircraft, status);
		assertFalse(result);
	}

	@Test
	public void changeAircraftStatusNotExist() throws DAOException, ConnectionPoolException, SQLException {
		aircraftDao.addAircraft(aircraft, aircraftType);
		String status = "StatusNotExist";
		Connection connection = null;
		connection = ConnectionPoolImpl.getInstance().getConnection();
		PreparedStatement statement = connection
				.prepareStatement("select id_aircraft from aircrafts where registration_number='EW-881PM';");
		ConnectionPoolImpl.getInstance().releaseConnection(connection);
		ResultSet rs = statement.executeQuery();
		int idAircraft = 0;
		if (rs.next()) {
			idAircraft = rs.getInt("id_aircraft");
		}
		boolean result = aircraftDao.changeAircraftStatus(idAircraft, status);
		assertFalse(result);
	}

	@Test
	public void getAircrafts() throws ConnectionPoolException, SQLException, DAOException {
		List<Aircraft> expectedList = new ArrayList<Aircraft>();
		Connection connection = null;
		connection = ConnectionPoolImpl.getInstance().getConnection();
		PreparedStatement statement = connection.prepareStatement(
				"select id_aircraft_type, aircraft_type, range_flight, number_passengers, id_aircraft, registration_number, status from aircrafts JOIN aircraft_types ON aircrafts.type_aircraft=aircraft_types.id_aircraft_type;");
		ConnectionPoolImpl.getInstance().releaseConnection(connection);
		ResultSet rs = statement.executeQuery();
		while (rs.next()) {
			AircraftType type = new AircraftType(rs.getInt("id_aircraft_type"), rs.getString("aircraft_type"),
					rs.getInt("range_flight"), rs.getInt("number_passengers"));
			Aircraft aircraft = new Aircraft(rs.getInt("id_aircraft"), type, rs.getString("registration_number"),
					rs.getString("status"));
			expectedList.add(aircraft);
		}
		List<Aircraft> actualList=aircraftDao.getAircrafts();
		assertEquals(expectedList, actualList);
	}
}
