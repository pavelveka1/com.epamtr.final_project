package by.epamtr.airline.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.epamtr.airline.dao.exception.DAOException;
import by.epamtr.airline.entity.User;
import by.epamtr.airline.entity.UserRole;

public interface UserDAO {

	void signIn(HttpServletRequest request, HttpServletResponse response) throws DAOException;

	void signOut() throws DAOException;

	void addUser(User user) throws DAOException;

	void deliteUser(int idUser) throws DAOException;

	void updateUser(int idUser) throws DAOException;

	List<User> getUsers(UserRole role) throws DAOException;

	List<User> getUsers(int idFlight) throws DAOException;

	User getUser(int idUser) throws DAOException;
}
