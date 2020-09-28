package by.epamtr.airline.dao.impl;

import java.util.List;

import by.epamtr.airline.dao.AdministratorDAO;
import by.epamtr.airline.dao.AircraftStatusDAO;
import by.epamtr.airline.dao.FlightStatusDAO;
import by.epamtr.airline.dao.exception.DAOException;
import by.epamtr.airline.entity.Aircraft;
import by.epamtr.airline.entity.AircraftType;
import by.epamtr.airline.entity.Flight;
import by.epamtr.airline.entity.User;
import by.epamtr.airline.entity.UserRole;

public class SQLAdministratorDAO extends SQLUserDAO implements AdministratorDAO {


	@Override
	public void addUser(User user) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deliteUser(int idUser) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editUser(User user) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> showUsers(UserRole role) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addFlight(Flight flight) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deliteFlight(int idFlight) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editFlight(int idFlight) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeFlightStatus(int idFlight, FlightStatusDAO flightStatus) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addAircraft(Aircraft aircraft) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deliteAircraft(int idAircraft) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editAircraft(int idAircraft) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeAircraftStatus(int idAircraft, AircraftStatusDAO aircraftStatus) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addAircraftType(AircraftType aircraftType) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deliteAircraftType(int idAircraftType) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	

}
