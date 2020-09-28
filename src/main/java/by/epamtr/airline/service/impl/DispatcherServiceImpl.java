package by.epamtr.airline.service.impl;

import java.util.List;

import by.epamtr.airline.dao.FlightStatusDAO;
import by.epamtr.airline.entity.User;
import by.epamtr.airline.entity.UserRole;
import by.epamtr.airline.service.DispatcherService;
import by.epamtr.airline.service.exception.ServiceException;

public class DispatcherServiceImpl extends UserServiceImpl implements DispatcherService {

	@Override
	public void addCrewToFlight(User user) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editFlightCrew(int idFlight) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeFlightStatus(FlightStatusDAO flightStatus) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> showUsers(UserRole userRole) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}


}
