package by.epamtr.airline.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtr.airline.dao.AircraftDAO;
import by.epamtr.airline.dao.DAOFactory;
import by.epamtr.airline.dao.FlightDAO;
import by.epamtr.airline.dao.exception.DAOException;
import by.epamtr.airline.entity.Aircraft;
import by.epamtr.airline.entity.CrewPosition;
import by.epamtr.airline.entity.Flight;
import by.epamtr.airline.entity.FlightStatus;
import by.epamtr.airline.entity.User;
import by.epamtr.airline.service.FlightService;
import by.epamtr.airline.service.exception.ServiceException;

public class FlightServiceImpl implements FlightService {
	private FlightDAO flightDAO = DAOFactory.getInstance().getSqlFlightImpl();

	@Override
	public boolean addFlight(Flight flight, Aircraft aircraft) throws ServiceException {
		try {
			return flightDAO.addFlight(flight, aircraft);
		} catch (DAOException e) {
			throw new ServiceException("Error while adding new flight", e);
		}

	}

	@Override
	public void deliteFlight(int idFlight) throws ServiceException {
		try {
			flightDAO.deliteFlight(idFlight);
		} catch (DAOException e) {
			throw new ServiceException("Error while deletion flight", e);
		}

	}

	@Override
	public Flight updateFlight(int idFlight,Flight flight )
			throws ServiceException {
		try {
			return flightDAO.updateFlight(idFlight, flight);
		} catch (DAOException e) {
			throw new ServiceException("Error while updation flight", e);
		}
	}

	@Override
	public void changeFlightStatus(int idFlight, FlightStatus flightStatus) throws ServiceException {
		try {
			flightDAO.changeFlightStatus(idFlight, flightStatus);
		} catch (DAOException e) {
			throw new ServiceException("Error while updation flight status", e);
		}

	}

	@Override
	public Flight getFlight(int idFlight) throws ServiceException {
		Flight flight;
		try {
			flight = flightDAO.getFlight(idFlight);
		} catch (DAOException e) {
			throw new ServiceException("Error while adding new flight", e);
		}
		return flight;
	}

	@Override
	public List<Flight> getFlights() throws ServiceException {
		return null;

	}

	@Override
	public List<Flight> getFlights(FlightStatus flightStatus) throws ServiceException {
		List<Flight> flightsByStatus;
		try {
			flightsByStatus = flightDAO.getFlights(flightStatus);
		} catch (DAOException e) {
			throw new ServiceException("Error while getting flights from DB", e);
		}
		return flightsByStatus;
	}

	@Override
	public List<Flight> getFlights(int idUser) throws ServiceException {
		List<Flight> flightsByUserId;
		try {
			flightsByUserId = flightDAO.getFlights(idUser);
		} catch (DAOException e) {
			throw new ServiceException("Error while getting flights from DB", e);
		}
		return flightsByUserId;
	}

	@Override
	public void addCrewToFlight(User user) throws ServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateFlightCrew(int idFlight) throws ServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<CrewPosition> getFreeCrewPositions(int flightId) throws ServiceException {
		List<CrewPosition> freeCrewPosition;
		try {
			freeCrewPosition = flightDAO.getFreeCrewPositions(flightId);
		} catch (DAOException e) {
			throw new ServiceException("Error while getting free crew positions for flight id from DB", e);
		}
		return freeCrewPosition;
	}

}
