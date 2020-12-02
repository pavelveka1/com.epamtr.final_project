package by.epamtr.airline.service.impl;

import java.util.List;
import by.epamtr.airline.dao.AircraftDAO;
import by.epamtr.airline.dao.DAOFactory;
import by.epamtr.airline.dao.exception.DAOException;
import by.epamtr.airline.entity.Aircraft;
import by.epamtr.airline.entity.AircraftType;
import by.epamtr.airline.service.AircraftService;
import by.epamtr.airline.service.exception.ServiceException;

public class AircraftServiceImpl implements AircraftService {
	private AircraftDAO aircraftDAO = DAOFactory.getInstance().getSqlAircraftImpl();

	@Override
	public boolean addAircraft(Aircraft aircraft, AircraftType aircraftType) throws ServiceException {
		try {
			return aircraftDAO.addAircraft(aircraft, aircraftType);
		} catch (DAOException e) {
			throw new ServiceException("Error while adding new aircraft", e);
		}

	}

	@Override
	public boolean deleteAircraft(String registrationNumber) throws ServiceException {
		try {
			return aircraftDAO.deleteAircraft(registrationNumber);
		} catch (DAOException e) {
			throw new ServiceException("Error while deletion aircraft", e);
		}

	}

	@Override
	public boolean updateAircraft(String registrationNumber, String newRegistrationNumber) throws ServiceException {
		try {
			return aircraftDAO.updateAircraft(registrationNumber, newRegistrationNumber);
		} catch (DAOException e) {
			throw new ServiceException("Error while updating aircraft", e);
		}

	}

	@Override
	public boolean changeAircraftStatus(int idAircraft, String status) throws ServiceException {
		try {
			return aircraftDAO.changeAircraftStatus(idAircraft, status);
		} catch (DAOException e) {
			throw new ServiceException("Error while changing status of aircraft", e);
		}

	}

	@Override
	public boolean addAircraftType(AircraftType aircraftType) throws ServiceException {
		try {
			return aircraftDAO.addAircraftType(aircraftType);
		} catch (DAOException e) {
			throw new ServiceException("Error while adding new type of aircraft", e);
		}

	}

	@Override
	public boolean deliteAircraftType(int idAircraftType) throws ServiceException {
		try {
			return aircraftDAO.deliteAircraftType(idAircraftType);
		} catch (DAOException e) {
			throw new ServiceException("Error while deleting type of aircraft", e);
		}

	}

	@Override
	public List<AircraftType> getAircraftTypes() throws ServiceException {
		try {
			return aircraftDAO.getAircraftTypes();
		} catch (DAOException e) {
			throw new ServiceException("Error while getting aircraft types from DB", e);
		}

	}

	@Override
	public List<Aircraft> getAircraftrs() throws ServiceException {
		try {
			return aircraftDAO.getAircrafts();
		} catch (DAOException e) {
			throw new ServiceException("Error while getting aircrafts from DB", e);
		}
	}

}
