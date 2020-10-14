package by.epamtr.airline.service.impl;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.epamtr.airline.dao.AircraftDAO;
import by.epamtr.airline.dao.AircraftStatusDAO;
import by.epamtr.airline.dao.DAOFactory;
import by.epamtr.airline.dao.exception.DAOException;
import by.epamtr.airline.entity.Aircraft;
import by.epamtr.airline.entity.AircraftType;
import by.epamtr.airline.service.AircraftService;
import by.epamtr.airline.service.exception.ServiceException;

public class AircraftServiceImpl implements AircraftService{
	private AircraftDAO aircraftDAO = DAOFactory.getInstance().getSqlAircraftImpl();

	@Override
	public void addAircraft(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		try {
			aircraftDAO.addAircraft(request, response);
		} catch (DAOException e) {
			throw new ServiceException("Error while adding new aircraft",e);
		}
		
	}

	@Override
	public void deleteAircraft(String registrationNumber) throws ServiceException {
		try {
			aircraftDAO.deleteAircraft(registrationNumber);
		} catch (DAOException e) {
			throw new ServiceException("Error while deletion aircraft",e);
		}
		
	}

	@Override
	public void updateAircraft(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		try {
			aircraftDAO.updateAircraft(request, response);
		} catch (DAOException e) {
			throw new ServiceException("Error while updating aircraft",e);
		}
		
	}

	@Override
	public void changeAircraftStatus(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		try {
			aircraftDAO.changeAircraftStatus(request, response);;
		} catch (DAOException e) {
			throw new ServiceException("Error while changing status of aircraft",e);
		}
		
	}

	@Override
	public void addAircraftType(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		try {
			aircraftDAO.addAircraftType(request, response);
		} catch (DAOException e) {
			throw new ServiceException("Error while adding new type of aircraft",e);
		}
		
	}

	@Override
	public void deliteAircraftType(int idAircraftType) throws ServiceException {
		try {
			aircraftDAO.deliteAircraftType(idAircraftType);
		} catch (DAOException e) {
			throw new ServiceException("Error while deleting type of aircraft",e);
		}
		
	}

	@Override
	public List<AircraftType> getAircraftTypes() throws ServiceException {
		try {
			return aircraftDAO.getAircraftTypes();
		} catch (DAOException e) {
			throw new ServiceException("Error while getting aircraft types from DB",e);
		}
		
	}

	@Override
	public List<Aircraft> getAircraftrs() throws ServiceException {
		try {
			return aircraftDAO.getAircrafts();
		} catch (DAOException e) {
			throw new ServiceException("Error while getting aircrafts from DB",e);
		}
	}



}
