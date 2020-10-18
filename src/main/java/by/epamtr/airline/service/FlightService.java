package by.epamtr.airline.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtr.airline.entity.Flight;
import by.epamtr.airline.entity.FlightStatus;
import by.epamtr.airline.entity.User;
import by.epamtr.airline.service.exception.ServiceException;

public interface FlightService {
	void addFlight(HttpServletRequest request, HttpServletResponse response) throws ServiceException;

	void deliteFlight(int idFlight) throws ServiceException;

	Flight updateFlight(int idFlight, HttpServletRequest request, HttpServletResponse response) throws ServiceException;

	void changeFlightStatus(int idFlight, FlightStatus flightStatus) throws ServiceException;

	Flight getFlight(int idFlight) throws ServiceException;

	List<Flight> getFlights() throws ServiceException;

	List<Flight> getFlights(FlightStatus flightStatus) throws ServiceException;

	List<Flight> getFlights(int idUser) throws ServiceException;

	void addCrewToFlight(User user) throws ServiceException;

	void updateFlightCrew(int idFlight) throws ServiceException;
}
