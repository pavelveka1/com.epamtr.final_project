package by.epamtr.airline.service;

import java.util.List;
import by.epamtr.airline.dao.FlightStatusDAO;
import by.epamtr.airline.entity.Flight;
import by.epamtr.airline.entity.User;
import by.epamtr.airline.service.exception.ServiceException;

public interface FlightService {
	void addFlight(Flight flight) throws ServiceException;

	void deliteFlight(int idFlight) throws ServiceException;

	void updateFlight(int idFlight) throws ServiceException;

	void changeFlightStatus(int idFlight, FlightStatusDAO flightStatus) throws ServiceException;

	Flight getFlight(int idFlight) throws ServiceException;

	List<Flight> getFlights() throws ServiceException;

	List<Flight> getFlights(FlightStatusDAO flightStatus) throws ServiceException;

	List<Flight> getFlights(User user) throws ServiceException;

	void addCrewToFlight(User user) throws ServiceException;

	void updateFlightCrew(int idFlight) throws ServiceException;
}
