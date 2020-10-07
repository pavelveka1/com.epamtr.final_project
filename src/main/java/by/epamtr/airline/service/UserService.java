package by.epamtr.airline.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.epamtr.airline.entity.User;
import by.epamtr.airline.entity.UserRole;
import by.epamtr.airline.service.exception.ServiceException;

public interface UserService {
	void signIn(HttpServletRequest request, HttpServletResponse response) throws ServiceException;

	void signOut(HttpServletRequest request, HttpServletResponse response) throws ServiceException;

	void addUser(HttpServletRequest request, HttpServletResponse response) throws ServiceException;

	void deliteUser(String login) throws ServiceException;

	void updateUser(String login) throws ServiceException;

	List<User> getUsers(UserRole role) throws ServiceException;

	List<User> getUsers(int idFlight) throws ServiceException;

	User getUser(int idUser) throws  ServiceException;
}
