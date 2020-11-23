package by.epamtr.airline.service.impl;

import java.util.List;
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

	private UserDAO userDAO = DAOFactory.getInstance().getSqlUserImpl();

	@Override
	public User signIn(String login, String password) throws ServiceException {
		if (!SignInDataValidator.validate(login, password)) {
			return null;

		} else {
			try {
				return userDAO.signIn(login, password);
			} catch (DAOException e) {
				throw new ServiceException("Error while signing in", e);
			}
		}
	}

	@Override
	public boolean addUser(User user, UserInfo userInfo) throws ServiceException {
		try {
			return userDAO.addUser(user, userInfo);
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
	public boolean updateUser(User user, UserInfo userInfo) throws ServiceException {
		try {
			return userDAO.updateUser(user, userInfo);
		} catch (DAOException e) {
			throw new ServiceException("Error while updateing new user", e);
		}

	}

	@Override
	public List<User> getUsers(UserRole role) throws ServiceException {
		try {
			return userDAO.getUsers(role);
		} catch (DAOException e) {
			throw new ServiceException("Error while getting users by role", e);
		}
	}

	@Override
	public List<Crew> getUsers(int idFlight) throws ServiceException {
		List<Crew> crew;
		try {
			crew = userDAO.getUsers(idFlight);
		} catch (DAOException e) {
			throw new ServiceException("Error while getting users by role", e);
		}
		return crew;
	}

	@Override
	public User getUser(int idUser) throws ServiceException {
		User user;
		try {
			user = userDAO.getUser(idUser);
		} catch (DAOException e) {
			throw new ServiceException("Error while getting user by id from DB", e);
		}
		return user;
	}

	@Override
	public UserInfo getUserInfo(int idUser) throws ServiceException {
		UserInfo userInfo;
		try {
			userInfo = userDAO.getUserInfo(idUser);
		} catch (DAOException e) {
			throw new ServiceException("Error while getting user by id from DB", e);
		}
		return userInfo;
	}

	@Override
	public boolean deliteCrewFromFlight(int flightId, int userId) throws ServiceException {
		try {
			return userDAO.deliteCrewFromFlight(flightId, userId);
		} catch (DAOException e) {
			throw new ServiceException("Error while deletion crew from flight", e);
		}

	}

	@Override
	public boolean addCrewToFlight(int idCrewPosition, int flightId, int userId) throws ServiceException {
		try {
			return userDAO.addCrewToFlight(idCrewPosition, flightId, userId);
		} catch (DAOException e) {
			throw new ServiceException("Error while adding user to crew", e);
		}

	}

	@Override
	public List<User> getFreeUsers(int flightId, String selectedPosition) throws ServiceException {
		List<User> freeUsersByPosition;
		try {
			freeUsersByPosition = userDAO.getFreeUsers(flightId, selectedPosition);
		} catch (DAOException e) {
			throw new ServiceException("Error while getting free users by position", e);
		}
		return freeUsersByPosition;
	}

}
