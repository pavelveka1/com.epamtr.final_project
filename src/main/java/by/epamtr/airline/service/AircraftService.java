package by.epamtr.airline.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtr.airline.dao.AircraftStatusDAO;
import by.epamtr.airline.service.exception.ServiceException;

public interface AircraftService {
	void addAircraft(HttpServletRequest request, HttpServletResponse response) throws ServiceException;

	void deliteAircraft(int idAircraft) throws ServiceException;

	void updateAircraft(HttpServletRequest request, HttpServletResponse response) throws ServiceException;

	void changeAircraftStatus(int idAircraft, AircraftStatusDAO aircraftStatus) throws ServiceException;

	void addAircraftType(HttpServletRequest request, HttpServletResponse response) throws ServiceException;

	void deliteAircraftType(int idAircraftType) throws ServiceException;
}
