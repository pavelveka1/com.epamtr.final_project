package by.epamtr.airline.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtr.airline.dao.DAOFactory;
import by.epamtr.airline.dao.FlightStatusDAO;
import by.epamtr.airline.dao.UserDAO;
import by.epamtr.airline.dao.exception.DAOException;
import by.epamtr.airline.entity.Flight;
import by.epamtr.airline.entity.User;
import by.epamtr.airline.service.UserService;
import by.epamtr.airline.service.exception.ServiceException;

public class UserServiceImpl implements UserService {

	@Override
	public void signIn(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getSqlUserImpl();
		try {
			userDAO.signIn(request, response);
		} catch (DAOException e) {
			throw new ServiceException("Error while signing in", e);
		}

	}

	@Override
	public void signOut() throws ServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Flight> showFlights() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Flight> showFlights(FlightStatusDAO flightStatus) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> showCrew(int idFlight) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
