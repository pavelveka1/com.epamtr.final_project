package by.epamtr.airline.dao;

import java.util.List;
import by.epamtr.airline.dao.exception.DAOException;
import by.epamtr.airline.entity.Crew;
import by.epamtr.airline.entity.User;
import by.epamtr.airline.entity.UserInfo;
import by.epamtr.airline.entity.UserRole;
/**
 * Interface actions with users
 * @author Pavel Veka
 *
 */
public interface UserDAO {

	/**
	 * Sign in user
	 * @param login
	 * @param password
	 * @return user if user with login and passwors is exist, otherwise null
	 * @throws DAOException if dao exception occurred while processing
	 */
	User signIn(String login, String password) throws DAOException;

	/**
	 * Add new user in database
	 * @param user
	 * @param userInfo
	 * @return true if user and userInfo were added
	 * @throws DAOException if dao exception occurred while processing
	 */
	boolean addUser(User user, UserInfo userInfo) throws DAOException;

	/**
	 * Delete user from database
	 * @param idUser
	 * @return true if user was deleted
	 * @throws DAOException if dao exception occurred while processing
	 */
	boolean deliteUser(int idUser) throws DAOException;

	/**
	 * Update user's data in database
	 * @param user
	 * @param userInfo
	 * @return true if user's data was updated
	 * @throws DAOException if dao exception occurred while processing
	 */
	boolean updateUser(User user, UserInfo userInfo) throws DAOException;

	/**
	 * Get list of users by role of user from database
	 * @param role
	 * @return list of users by role
	 * @throws DAOException if dao exception occurred while processing
	 */
	List<User> getUsers(UserRole role) throws DAOException;

	/**
	 * Get list of users by flight id from database
	 * @param idFlight
	 * @return list of users by flight id
	 * @throws DAOException if dao exception occurred while processing
	 */
	List<Crew> getUsers(int idFlight) throws DAOException;

	/**
	 * Get user by id of user from database
	 * @param idUser
	 * @return User object if user with such id is exist, otherwise false
	 * @throws DAOException if dao exception occurred while processing
	 */
	User getUser(int idUser) throws DAOException;

	/**
	 * Get UserInfo object by id of user from database
	 * @param idUser
	 * @return UserInfo object if user with such id is exist, otherwise null
	 * @throws DAOException if dao exception occurred while processing
	 */
	UserInfo getUserInfo(int idUser) throws DAOException;

	/**
	 * Delete crew member from flight by id of user in database
	 * @param flightId
	 * @param userId
	 * @return true if crem member was deleted from flight, otherwise false
	 * @throws DAOException if dao exception occurred while processing
	 */
	boolean deliteCrewFromFlight(int flightId, int userId) throws DAOException;

	/**
	 * Add crem member with id equals userId to flight with id equals flightid in database
	 * @param idCrewPosition
	 * @param flightId
	 * @param userId
	 * @return true if crew member was added to flight
	 * @throws DAOException if dao exception occurred while processing
	 */
	boolean addCrewToFlight(int idCrewPosition, int flightId, int userId) throws DAOException;

	/**
	 * Get list of free crew positions for flight by id of flight
	 * @param flightId
	 * @param selectedPosition
	 * @return list of free crew positions
	 * @throws DAOException if dao exception occurred while processing
	 */
	List<User> getFreeUsers(int flightId, String selectedPosition) throws DAOException;
}
