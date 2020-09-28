package by.epamtr.airline.service;

import java.util.List;

import by.epamtr.airline.dao.AircraftStatusDAO;
import by.epamtr.airline.dao.FlightStatusDAO;
import by.epamtr.airline.entity.Aircraft;
import by.epamtr.airline.entity.AircraftType;
import by.epamtr.airline.entity.Flight;
import by.epamtr.airline.entity.User;
import by.epamtr.airline.entity.UserRole;
import by.epamtr.airline.service.exception.ServiceException;

public interface AdminService {
	
	void addUser(User user) throws ServiceException;
	void deliteUser(int idUser) throws ServiceException;
	void editUser(User user) throws ServiceException;
	List<User> showUsers(UserRole role) throws ServiceException;
	
	void addFlight(Flight flight) throws ServiceException;
	void deliteFlight(int idFlight) throws ServiceException;
	void editFlight(int idFlight) throws ServiceException;
	void changeFlightStatus( int idFlight, FlightStatusDAO flightStatus) throws ServiceException;
	
	void addAircraft(Aircraft aircraft) throws ServiceException;
	void deliteAircraft(int idAircraft) throws ServiceException;
	void editAircraft(int idAircraft) throws ServiceException;
	void changeAircraftStatus(int idAircraft, AircraftStatusDAO aircraftStatus) throws ServiceException;
	
	void addAircraftType(AircraftType aircraftType) throws ServiceException;
	void deliteAircraftType(int idAircraftType) throws ServiceException;

}
