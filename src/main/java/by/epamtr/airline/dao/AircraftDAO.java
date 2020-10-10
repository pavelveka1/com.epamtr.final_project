package by.epamtr.airline.dao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtr.airline.dao.exception.DAOException;
import by.epamtr.airline.entity.Aircraft;
import by.epamtr.airline.entity.AircraftType;

public interface AircraftDAO {
	void addAircraft(HttpServletRequest request, HttpServletResponse response) throws DAOException;

	void deliteAircraft(int idAircraft) throws DAOException;

	void updateAircraft(HttpServletRequest request, HttpServletResponse response) throws DAOException;

	void changeAircraftStatus(int idAircraft, AircraftStatusDAO aircraftStatus) throws DAOException;

	void addAircraftType(HttpServletRequest request, HttpServletResponse response) throws DAOException;

	void deliteAircraftType(int idAircraftType) throws DAOException;
}
