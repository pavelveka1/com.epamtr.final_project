package by.epamtr.airline.dao;

import java.util.List;

import by.epamtr.airline.dao.exception.DAOException;
import by.epamtr.airline.entity.User;
import by.epamtr.airline.entity.UserRole;

public interface DispatcherDAO {
	
	void addCrewToFlight(User user) throws DAOException;
	
	void editFlightCrew(int idFlight) throws DAOException;

	void changeFlightStatus(FlightStatusDAO flightStatus) throws DAOException;
	
	List<User> showUsers(UserRole userRole) throws DAOException; 
	
}
