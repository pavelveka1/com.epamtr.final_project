package by.epamtr.airline.dao;

import java.util.List;

import by.epamtr.airline.dao.exception.DAOException;
import by.epamtr.airline.entity.Aircraft;
import by.epamtr.airline.entity.AircraftType;
import by.epamtr.airline.entity.Flight;
import by.epamtr.airline.entity.User;
import by.epamtr.airline.entity.UserRole;

public interface AdministratorDAO {
	
	void addUser(User user) throws DAOException;
	void deliteUser(int idUser) throws DAOException;
	void editUser(User user) throws DAOException;
	List<User> showUsers(UserRole role) throws DAOException;
	
	void addFlight(Flight flight) throws DAOException;
	void deliteFlight(int idFlight) throws DAOException;
	void editFlight(int idFlight) throws DAOException;
	void changeFlightStatus(int idFlight, FlightStatusDAO flightStatus) throws DAOException;
	
	void addAircraft(Aircraft aircraft) throws DAOException;
	void deliteAircraft(int idAircraft) throws DAOException;
	void editAircraft(int idAircraft) throws DAOException;
	void changeAircraftStatus(int idAircraft, AircraftStatusDAO aircraftStatus) throws DAOException;
	
	void addAircraftType(AircraftType aircraftType) throws DAOException;
	void deliteAircraftType(int idAircraftType) throws DAOException;
	
	
	
	
	
}
