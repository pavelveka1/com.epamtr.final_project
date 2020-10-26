package by.epamtr.airline.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.epamtr.airline.dao.exception.DAOException;
import by.epamtr.airline.entity.Crew;
import by.epamtr.airline.entity.User;
import by.epamtr.airline.entity.UserInfo;
import by.epamtr.airline.entity.UserRole;

public interface UserDAO {

	void signIn(HttpServletRequest request, HttpServletResponse response) throws DAOException;

	void signOut(HttpServletRequest request, HttpServletResponse response) throws DAOException;

	void addUser(HttpServletRequest request, HttpServletResponse response) throws DAOException;

	boolean deliteUser(int idUser) throws DAOException;

	void updateUser(HttpServletRequest request, HttpServletResponse response) throws DAOException;

	void findUser(HttpServletRequest request, HttpServletResponse response) throws DAOException;

	List<User> getUsers(UserRole role) throws DAOException;

	List<Crew> getUsers(int idFlight) throws DAOException;

	User getUser(int idUser) throws DAOException;

	UserInfo getUserInfo(int idUser) throws DAOException;

	void deliteCrewFromFlight(int flightId, int userId) throws DAOException;

	void addCrewToFlight(int idCrewPosition, int flightId, int userId) throws DAOException;

	List<User> getFreeUsers(int flightId, String selectedPosition) throws DAOException;
}
