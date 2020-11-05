package by.epamtr.airline.dao;

import java.util.List;
import by.epamtr.airline.dao.exception.DAOException;
import by.epamtr.airline.entity.Aircraft;
import by.epamtr.airline.entity.CrewPosition;
import by.epamtr.airline.entity.Flight;
import by.epamtr.airline.entity.FlightStatus;
import by.epamtr.airline.entity.User;
/**
 * Interface actions with flights
 * @author Pavel Veka
 *
 */
public interface FlightDAO {
	/**
	 * Add new flight in database
	 * @param flight
	 * @param aircraft
	 * @return true if flight was added, otherwise false
	 * @throws DAOException if dao exception occurred while processing
	 */
	boolean addFlight(Flight flight, Aircraft aircraft) throws DAOException;

	/**
	 * Delete flight from database
	 * @param idFlight
	 * @return true if flight was deleted, otherwise false
	 * @throws DAOException if dao exception occurred while processing
	 */
	boolean deliteFlight(int idFlight) throws DAOException;
	
/**
 * Update flight in detabase
 * @param idFlight
 * @param flight
 * @return true if flight was updated, otherwise false
 * @throws DAOException if dao exception occurred while processing
 */
	Flight updateFlight(int idFlight, Flight flight) throws DAOException;

	/**
	 * Change status of flight
	 * @param idFlight
	 * @param flightStatus new status
	 * @return true if status was updated, otherwise false
	 * @throws DAOException if dao exception occurred while processing
	 */
	boolean changeFlightStatus(int idFlight, FlightStatus flightStatus) throws DAOException;

	/**
	 * Get flight by if of flight
	 * @param idFlight
	 * @return flight if found, otherwise null
	 * @throws DAOException if dao exception occurred while processing
	 */
	Flight getFlight(int idFlight) throws DAOException;
	
	/**
	 * Get list of flights
	 * @return list of flights
	 * @throws DAOException if dao exception occurred while processing
	 */
	List<Flight> getFlights() throws DAOException;

	/**
	 * Get flights by status of flight
	 * @param flightStatus -FlightStatus object which contains name of status
	 * @return list of flights where status like in parameter flightStatus
	 * @throws DAOException if dao exception occurred while processing
	 */
	List<Flight> getFlights(FlightStatus flightStatus) throws DAOException;

	/**
	 * Get flights by id of user
	 * @param idUser
	 * @return list of flights where user with idUser occuped any position
	 * @throws DAOException if dao exception occurred while processing
	 */
	List<Flight> getFlights(int idUser) throws DAOException;

	/**
	 * Add user to flight as crew member
	 * @param user
	 * @return true if user was added to flight
	 * @throws DAOException if dao exception occurred while processing
	 */
	boolean addCrewToFlight(User user) throws DAOException;

	/**
	 * Update crew of flight by flight's id
	 * @param idFlight
	 * @return true if crew of flight was updated, otherwise false
	 * @throws DAOException if dao exception occurred while processing
	 */
	boolean updateFlightCrew(int idFlight) throws DAOException;
	
	/**
	 * Get free crew positions by flight id
	 * @param flightId
	 * @return list of free crew positions
	 * @throws DAOException if dao exception occurred while processing
	 */
	List<CrewPosition> getFreeCrewPositions(int flightId) throws DAOException;
}
