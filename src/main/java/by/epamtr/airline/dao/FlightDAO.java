package by.epamtr.airline.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtr.airline.dao.exception.DAOException;
import by.epamtr.airline.entity.CrewPosition;
import by.epamtr.airline.entity.Flight;
import by.epamtr.airline.entity.FlightStatus;
import by.epamtr.airline.entity.User;

public interface FlightDAO {
	void addFlight(HttpServletRequest request, HttpServletResponse response) throws DAOException;

	void deliteFlight(int idFlight) throws DAOException;

	Flight updateFlight(int idFlight, HttpServletRequest request, HttpServletResponse response) throws DAOException;

	void changeFlightStatus(int idFlight, FlightStatus flightStatus) throws DAOException;

	Flight getFlight(int idFlight) throws DAOException;
	
	List<Flight> getFlights() throws DAOException;

	List<Flight> getFlights(FlightStatus flightStatus) throws DAOException;

	List<Flight> getFlights(int idUser) throws DAOException;

	void addCrewToFlight(User user) throws DAOException;

	void updateFlightCrew(int idFlight) throws DAOException;
	
	List<CrewPosition> getFreeCrewPositions(int flightId) throws DAOException;
}
