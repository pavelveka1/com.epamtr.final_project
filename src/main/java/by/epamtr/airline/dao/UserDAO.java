package by.epamtr.airline.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.epamtr.airline.dao.exception.DAOException;
import by.epamtr.airline.entity.User;
import by.epamtr.airline.entity.UserInfo;
import by.epamtr.airline.entity.UserRole;

public interface UserDAO {

	void signIn(HttpServletRequest request, HttpServletResponse response) throws DAOException;

	void signOut(HttpServletRequest request, HttpServletResponse response) throws DAOException;

	void addUser(HttpServletRequest request, HttpServletResponse response) throws DAOException;

	void deliteUser(String login) throws DAOException;

	void updateUser(String login) throws DAOException;

	List<User> getUsers(UserRole role) throws DAOException;

	List<User> getUsers(int idFlight) throws DAOException;

	User getUser(int idUser) throws DAOException;
}
