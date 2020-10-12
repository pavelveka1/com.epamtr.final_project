package by.epamtr.airline.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.epamtr.airline.dao.exception.DAOException;
import by.epamtr.airline.entity.AircraftType;

public interface AircraftDAO {
	void addAircraft(HttpServletRequest request, HttpServletResponse response) throws DAOException;

	void deleteAircraft(String registrationNumber) throws DAOException;

	void updateAircraft(HttpServletRequest request, HttpServletResponse response) throws DAOException;

	void changeAircraftStatus(String registrationNumber, AircraftStatusDAO aircraftStatus) throws DAOException;

	void addAircraftType(HttpServletRequest request, HttpServletResponse response) throws DAOException;

	void deliteAircraftType(int idAircraftType) throws DAOException;
	
	List<AircraftType> getAircraftTypes() throws DAOException;
	List<String> getRegisterNumbers() throws DAOException;
}
