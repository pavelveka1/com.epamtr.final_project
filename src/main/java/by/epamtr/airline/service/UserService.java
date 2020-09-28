package by.epamtr.airline.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.epamtr.airline.dao.FlightStatusDAO;
import by.epamtr.airline.entity.Flight;
import by.epamtr.airline.entity.User;
import by.epamtr.airline.service.exception.ServiceException;

public interface UserService {
	void signIn(HttpServletRequest request, HttpServletResponse response) throws ServiceException;

	void signOut() throws ServiceException;

	List<Flight> showFlights() throws ServiceException;

	List<Flight> showFlights(FlightStatusDAO flightStatus) throws ServiceException;

	// void showFlights(/* дата? */) throws DAOException;

	List<User> showCrew(int idFlight) throws ServiceException;
}
