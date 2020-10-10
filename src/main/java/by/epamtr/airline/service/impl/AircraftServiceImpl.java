package by.epamtr.airline.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtr.airline.dao.AircraftDAO;
import by.epamtr.airline.dao.AircraftStatusDAO;
import by.epamtr.airline.dao.DAOFactory;
import by.epamtr.airline.dao.UserDAO;
import by.epamtr.airline.dao.exception.DAOException;
import by.epamtr.airline.dao.impl.SQLAircraftDAO;
import by.epamtr.airline.entity.Aircraft;
import by.epamtr.airline.entity.AircraftType;
import by.epamtr.airline.service.AircraftService;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.exception.ServiceException;

public class AircraftServiceImpl implements AircraftService{
	private AircraftDAO aircraftDAO = DAOFactory.getInstance().getSqlAircraftImpl();

	@Override
	public void addAircraft(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deliteAircraft(int idAircraft) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateAircraft(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeAircraftStatus(int idAircraft, AircraftStatusDAO aircraftStatus) throws ServiceException {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}



}
