package by.epamtr.airline.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtr.airline.dao.exception.DAOException;
import by.epamtr.airline.entity.Crew;
import by.epamtr.airline.entity.User;
import by.epamtr.airline.entity.UserInfo;
import by.epamtr.airline.entity.UserRole;
import by.epamtr.airline.service.exception.ServiceException;

public interface UserService {
	void signIn(HttpServletRequest request, HttpServletResponse response) throws ServiceException;

	void signOut(HttpServletRequest request, HttpServletResponse response) throws ServiceException;

	void addUser(HttpServletRequest request, HttpServletResponse response) throws ServiceException;

	boolean deliteUser(int idUser) throws ServiceException;

	void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServiceException;
	
	void findUser(HttpServletRequest request, HttpServletResponse response) throws ServiceException;

	List<User> getUsers(UserRole role) throws ServiceException;

	List<Crew> getUsers(int idFlight) throws ServiceException;

	User getUser(int idUser) throws  ServiceException;
	
	UserInfo getUserInfo(int idUser) throws ServiceException;
	
	void deliteCrewFromFlight(int flightId, int userId) throws ServiceException;
	
	void addCrewToFlight(int idCrewPosition, int flightId, int userId) throws ServiceException;
	
	List<User> getFreeUsers(int flightId, String selectedPosition) throws ServiceException;
}
