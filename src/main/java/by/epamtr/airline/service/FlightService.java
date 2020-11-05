package by.epamtr.airline.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtr.airline.entity.Aircraft;
import by.epamtr.airline.entity.CrewPosition;
import by.epamtr.airline.entity.Flight;
import by.epamtr.airline.entity.FlightStatus;
import by.epamtr.airline.entity.User;
import by.epamtr.airline.service.exception.ServiceException;

public interface FlightService {
	boolean addFlight(Flight flight, Aircraft aircraft) throws ServiceException;

	boolean deliteFlight(int idFlight) throws ServiceException;

	Flight updateFlight(int idFlight, Flight flight) throws ServiceException;

	boolean changeFlightStatus(int idFlight, FlightStatus flightStatus) throws ServiceException;

	Flight getFlight(int idFlight) throws ServiceException;

	List<Flight> getFlights() throws ServiceException;

	List<Flight> getFlights(FlightStatus flightStatus) throws ServiceException;

	List<Flight> getFlights(int idUser) throws ServiceException;

	boolean addCrewToFlight(User user) throws ServiceException;

	boolean updateFlightCrew(int idFlight) throws ServiceException;
	
	List<CrewPosition> getFreeCrewPositions(int flightId) throws ServiceException;
}
