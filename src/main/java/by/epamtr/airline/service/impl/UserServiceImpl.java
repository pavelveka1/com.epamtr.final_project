package by.epamtr.airline.service.impl;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.epamtr.airline.dao.DAOFactory;
import by.epamtr.airline.dao.UserDAO;
import by.epamtr.airline.dao.exception.DAOException;
import by.epamtr.airline.entity.Crew;
import by.epamtr.airline.entity.User;
import by.epamtr.airline.entity.UserRole;
import by.epamtr.airline.service.UserService;
import by.epamtr.airline.service.exception.ServiceException;
import by.epamtr.airline.service.validator.SignInDataValidator;

public class UserServiceImpl implements UserService {
	private UserDAO userDAO = DAOFactory.getInstance().getSqlUserImpl();
	@Override
	public void signIn(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		if(!SignInDataValidator.validate(request)) {
			// send command to MainController login data invalid? login again
		}
		try {
			userDAO.signIn(request, response);
		} catch (DAOException e) {
			throw new ServiceException("Error while signing in", e);
		}

	}

	@Override
	public void signOut(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		try {
			userDAO.signOut(request, response);
		} catch (DAOException e) {
			throw new ServiceException("Error while signing out", e);
		}

	}

	@Override
	public void addUser(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		try {
			userDAO.addUser(request, response);
		} catch (DAOException e) {
			throw new ServiceException("Error while adding new user", e);
		}
		
	}

	@Override
	public void deliteUser(String login) throws ServiceException {
		try {
			userDAO.deliteUser(login);
		} catch (DAOException e) {
			throw new ServiceException("Error while adding new user", e);
		}
		
	}

	@Override
	public void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		try {
			userDAO.updateUser( request, response);
		} catch (DAOException e) {
			throw new ServiceException("Error while adding new user", e);
		}
		
	}

	@Override
	public List<User> getUsers(UserRole role) throws ServiceException {
		try {
		return	userDAO.getUsers(role);
		} catch (DAOException e) {
			throw new ServiceException("Error while getting users by role", e);
		}	
	}

	@Override
	public List<Crew> getUsers(int idFlight) throws ServiceException {
		List<Crew> crew;
		try {
			crew=userDAO.getUsers(idFlight);
			} catch (DAOException e) {
				throw new ServiceException("Error while getting users by role", e);
			}	
		return crew;
	}

	@Override
	public User getUser(int idUser) throws ServiceException {
		User user;
		try {
			user=userDAO.getUser(idUser);
			} catch (DAOException e) {
				throw new ServiceException("Error while getting user by id from DB", e);
			}	
		return user;
	}

	@Override
	public void findUser(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		try {
			userDAO.findUser(request, response);
		} catch (DAOException e) {
			throw new ServiceException("Error while adding new user", e);
		}
	}

	@Override
	public void deliteCrewFromFlight(int flightId, int userId) throws ServiceException {
		try {
			userDAO.deliteCrewFromFlight(flightId, userId);
		} catch (DAOException e) {
			throw new ServiceException("Error while deletion crew from flight", e);
		}
		
	}

	@Override
	public void addCrewToFlight(int flightId, int userId) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	

}
