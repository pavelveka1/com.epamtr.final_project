package by.epamtr.airline.service;

import by.epamtr.airline.dao.AircraftStatusDAO;
import by.epamtr.airline.entity.Aircraft;
import by.epamtr.airline.entity.AircraftType;
import by.epamtr.airline.service.exception.ServiceException;

public interface AircraftService {
	void addAircraft(Aircraft aircraft) throws ServiceException;

	void deliteAircraft(int idAircraft) throws ServiceException;

	void updateAircraft(int idAircraft) throws ServiceException;

	void changeAircraftStatus(int idAircraft, AircraftStatusDAO aircraftStatus) throws ServiceException;

	void addAircraftType(AircraftType aircraftType) throws ServiceException;

	void deliteAircraftType(int idAircraftType) throws ServiceException;
}
