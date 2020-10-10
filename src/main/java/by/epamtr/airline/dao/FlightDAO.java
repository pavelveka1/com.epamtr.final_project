package by.epamtr.airline.dao;

import java.util.List;

import by.epamtr.airline.dao.exception.DAOException;
import by.epamtr.airline.entity.Flight;
import by.epamtr.airline.entity.User;

public interface FlightDAO {
	void addFlight(Flight flight) throws DAOException;

	void deliteFlight(int idFlight) throws DAOException;

	void updateFlight(int idFlight) throws DAOException;

	void changeFlightStatus(int idFlight, FlightStatusDAO flightStatus) throws DAOException;

	Flight getFlight(int idFlight) throws DAOException;
	
	List<Flight> getFlights() throws DAOException;

	List<Flight> getFlights(FlightStatusDAO flightStatus) throws DAOException;

	List<Flight> getFlights(User user) throws DAOException;

	void addCrewToFlight(User user) throws DAOException;

	void updateFlightCrew(int idFlight) throws DAOException;
}
