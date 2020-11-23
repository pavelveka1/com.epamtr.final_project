package by.epamtr.airline.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.epamtr.airline.dao.exception.DAOException;
import by.epamtr.airline.entity.Aircraft;
import by.epamtr.airline.entity.AircraftType;

/**
 * Interface actioans with aircraft
 * 
 * @author Pavel Veka
 *
 */
public interface AircraftDAO {
	/**
	 * Add new aircraft in database
	 * 
	 * @param aircraft
	 * @param aircraftType
	 * @return true if aircraft was added, otherwise false
	 * @throws DAOException if dao exception occurred while processing
	 */
	boolean addAircraft(Aircraft aircraft, AircraftType aircraftType) throws DAOException;

	/**
	 * Delete aircraft from database
	 * 
	 * @param registrationNumber
	 * @return true if aircraft was deleted, otherwise false
	 * @throws DAOException if dao exception occurred while processing
	 */
	boolean deleteAircraft(String registrationNumber) throws DAOException;

	/**
	 * Update aircraft in datadase
	 * 
	 * @param registrationNumber
	 * @param newRegistrationNumber
	 * @return true if aircraft was updated, otherwise false
	 * @throws DAOException
	 */
	boolean updateAircraft(String registrationNumber, String newRegistrationNumber) throws DAOException;

	/**
	 * Change status of aircraft in database
	 * 
	 * @param idAircraft
	 * @param status     - new status
	 * @return true if status was changed, otherwise false
	 * @throws DAOException if dao exception occurred while processing
	 */
	boolean changeAircraftStatus(int idAircraft, String status) throws DAOException;

	/**
	 * Add new type of aircraft in database
	 * 
	 * @param aircraftType
	 * @return true if type of aircraft was added, otherwise false
	 * @throws DAOException if dao exception occurred while processing
	 */
	boolean addAircraftType(AircraftType aircraftType) throws DAOException;

	/**
	 * Delete type of aircraft from database
	 * 
	 * @param idAircraftType
	 * @return true if type of aircraft was deleted, otherwise false
	 * @throws DAOException if dao exception occurred while processing
	 */
	boolean deliteAircraftType(int idAircraftType) throws DAOException;

	/**
	 * Get list of types of aircrafts from database
	 * 
	 * @return list of types of aircrafts
	 * @throws DAOException if dao exception occurred while processing
	 */
	List<AircraftType> getAircraftTypes() throws DAOException;

	/**
	 * Get list of aircrafts from database
	 * 
	 * @return list of aircrafts
	 * @throws DAOException if dao exception occurred while processing
	 */
	List<Aircraft> getAircrafts() throws DAOException;
}
