package by.epamtr.airline.service.impl;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.epamtr.airline.dao.DAOFactory;
import by.epamtr.airline.dao.UserDAO;
import by.epamtr.airline.dao.exception.DAOException;
import by.epamtr.airline.entity.User;
import by.epamtr.airline.entity.UserRole;
import by.epamtr.airline.service.UserService;
import by.epamtr.airline.service.exception.ServiceException;
import by.epamtr.airline.service.validator.SignInDataValidator;

public class UserServiceImpl implements UserService {

	@Override
	public void signIn(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		if(!SignInDataValidator.validate(request)) {
			// send command to MainController login data invalid? login again
		}
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
	public void addUser(User user) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deliteUser(int idUser) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(int idUser) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> getUsers(UserRole role) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUsers(int idFlight) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUser(int idUser) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	

}
