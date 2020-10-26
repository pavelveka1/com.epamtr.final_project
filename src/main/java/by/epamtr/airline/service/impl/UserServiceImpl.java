package by.epamtr.airline.service.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.epamtr.airline.dao.DAOFactory;
import by.epamtr.airline.dao.UserDAO;
import by.epamtr.airline.dao.exception.DAOException;
import by.epamtr.airline.entity.Crew;
import by.epamtr.airline.entity.User;
import by.epamtr.airline.entity.UserInfo;
import by.epamtr.airline.entity.UserRole;
import by.epamtr.airline.service.UserService;
import by.epamtr.airline.service.exception.ServiceException;
import by.epamtr.airline.service.validator.SignInDataValidator;

public class UserServiceImpl implements UserService {
	private final String SIGN_IN_FAIL_ATTR="sign_in_fail_attr";
	private final String SIGN_IN_FAIL="sign_in_fail";
	private final String PATH_TO_CONTROLLER = "/Controller?command=GO_TO_LOGIN_PAGE";
	
	private UserDAO userDAO = DAOFactory.getInstance().getSqlUserImpl();
	@Override
	public void signIn(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		if(!SignInDataValidator.validate(request)) {
			request.setAttribute(SIGN_IN_FAIL_ATTR, SIGN_IN_FAIL);
			try {
				request.getRequestDispatcher(PATH_TO_CONTROLLER).forward(request, response);
			} catch (ServletException | IOException e) {
				throw new ServiceException(e);
			}
		}else {
			try {
				userDAO.signIn(request, response);
			} catch (DAOException e) {
				throw new ServiceException("Error while signing in", e);
			}
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
	public boolean deliteUser(int idUser) throws ServiceException {
		try {
			return userDAO.deliteUser(idUser);
		} catch (DAOException e) {
			throw new ServiceException("Error while deletion user", e);
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
	public UserInfo getUserInfo(int idUser) throws ServiceException {
		UserInfo userInfo;
		try {
			userInfo=userDAO.getUserInfo(idUser);
			} catch (DAOException e) {
				throw new ServiceException("Error while getting user by id from DB", e);
			}	
		return userInfo;
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
	public void addCrewToFlight(int idCrewPosition, int flightId, int userId) throws ServiceException {
		try {
			userDAO.addCrewToFlight(idCrewPosition, flightId, userId);
		} catch (DAOException e) {
			throw new ServiceException("Error while adding user to crew", e);
		}
		
	}
	
	@Override
	public List<User> getFreeUsers(int flightId, String selectedPosition) throws ServiceException {
		List<User> freeUsersByPosition;
		try {
			freeUsersByPosition=userDAO.getFreeUsers(flightId, selectedPosition);
			} catch (DAOException e) {
				throw new ServiceException("Error while getting free users by position", e);
			}	
		return freeUsersByPosition;
	}

	
}
