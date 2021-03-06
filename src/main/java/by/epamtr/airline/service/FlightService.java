package by.epamtr.airline.service;

import java.util.List;
import by.epamtr.airline.entity.Aircraft;
import by.epamtr.airline.entity.CrewPosition;
import by.epamtr.airline.entity.Flight;
import by.epamtr.airline.entity.FlightStatus;
import by.epamtr.airline.service.exception.ServiceException;

public interface FlightService {
	boolean addFlight(Flight flight, Aircraft aircraft) throws ServiceException;

	boolean deliteFlight(int idFlight) throws ServiceException;

	Flight updateFlight(int idFlight, Flight flight) throws ServiceException;

	boolean changeFlightStatus(int idFlight, FlightStatus flightStatus) throws ServiceException;

	Flight getFlight(int idFlight) throws ServiceException;

	List<Flight> getFlights(FlightStatus flightStatus) throws ServiceException;

	List<Flight> getFlights(int idUser) throws ServiceException;

	List<CrewPosition> getFreeCrewPositions(int flightId) throws ServiceException;
}
