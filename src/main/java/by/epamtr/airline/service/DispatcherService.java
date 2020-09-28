package by.epamtr.airline.service;

import java.util.List;

import by.epamtr.airline.dao.FlightStatusDAO;

import by.epamtr.airline.entity.User;
import by.epamtr.airline.entity.UserRole;
import by.epamtr.airline.service.exception.ServiceException;

public interface DispatcherService {

	void addCrewToFlight(User user) throws ServiceException;
	
	void editFlightCrew(int idFlight) throws ServiceException;

	void changeFlightStatus(FlightStatusDAO flightStatus) throws ServiceException;
	
	List<User> showUsers(UserRole userRole) throws ServiceException;

}
