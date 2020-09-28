package by.epamtr.airline.service.impl;

import java.util.List;

import by.epamtr.airline.dao.AircraftStatusDAO;
import by.epamtr.airline.dao.FlightStatusDAO;
import by.epamtr.airline.entity.Aircraft;
import by.epamtr.airline.entity.AircraftType;
import by.epamtr.airline.entity.Flight;
import by.epamtr.airline.entity.User;
import by.epamtr.airline.entity.UserRole;
import by.epamtr.airline.service.AdminService;
import by.epamtr.airline.service.exception.ServiceException;

public class AdminServiceImpl extends UserServiceImpl implements AdminService {

	@Override
	public void addUser(User user) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deliteUser(int idUser) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editUser(User user) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> showUsers(UserRole role) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addFlight(Flight flight) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deliteFlight(int idFlight) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editFlight(int idFlight) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeFlightStatus(int idFlight, FlightStatusDAO flightStatus) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addAircraft(Aircraft aircraft) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deliteAircraft(int idAircraft) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editAircraft(int idAircraft) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeAircraftStatus(int idAircraft, AircraftStatusDAO aircraftStatus) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addAircraftType(AircraftType aircraftType) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deliteAircraftType(int idAircraftType) throws ServiceException {
		// TODO Auto-generated method stub
		
	}



}
