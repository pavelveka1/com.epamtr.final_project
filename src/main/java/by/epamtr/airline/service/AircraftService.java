package by.epamtr.airline.service;

import java.util.List;
import by.epamtr.airline.entity.Aircraft;
import by.epamtr.airline.entity.AircraftType;
import by.epamtr.airline.service.exception.ServiceException;

public interface AircraftService {
	boolean addAircraft(Aircraft aircraft, AircraftType aircraftType) throws ServiceException;

	boolean deleteAircraft(String registrationNumber) throws ServiceException;

	boolean updateAircraft(String registrationNumber, String newRegistrationNumber) throws ServiceException;

	boolean changeAircraftStatus(int idAircraft, String status) throws ServiceException;

	boolean addAircraftType(AircraftType aircraftType) throws ServiceException;

	boolean deliteAircraftType(int idAircraftType) throws ServiceException;

	List<AircraftType> getAircraftTypes() throws ServiceException;

	List<Aircraft> getAircraftrs() throws ServiceException;
}
