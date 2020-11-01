package by.epamtr.airline.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.epamtr.airline.dao.exception.DAOException;
import by.epamtr.airline.entity.Aircraft;
import by.epamtr.airline.entity.AircraftType;

public interface AircraftDAO {
	boolean addAircraft(Aircraft aircraft, AircraftType aircraftType) throws DAOException;

	void deleteAircraft(String registrationNumber) throws DAOException;

	boolean updateAircraft(String registrationNumber, String newRegistrationNumber) throws DAOException;

	void changeAircraftStatus(int idAircraft, String status) throws DAOException;

	boolean addAircraftType(AircraftType aircraftType) throws DAOException;

	void deliteAircraftType(int idAircraftType) throws DAOException;
	
	List<AircraftType> getAircraftTypes() throws DAOException;
	List<Aircraft> getAircrafts() throws DAOException;
}
