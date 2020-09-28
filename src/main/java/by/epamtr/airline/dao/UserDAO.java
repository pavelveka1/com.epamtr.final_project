package by.epamtr.airline.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtr.airline.dao.exception.DAOException;
import by.epamtr.airline.entity.Flight;
import by.epamtr.airline.entity.User;

public interface UserDAO {
	void signIn(HttpServletRequest request, HttpServletResponse response) throws DAOException;

	void signOut() throws DAOException;
	
	List<Flight> showFlights() throws DAOException;
	
	List<Flight> showFlights(FlightStatusDAO flightStatus) throws DAOException;
	
	//void showFlights(/* дата? */) throws DAOException;
	
	List<User> showCrew(int idFlight) throws DAOException;
}
