package by.epamtr.airline.service.impl;

import java.util.List;

import by.epamtr.airline.dao.FlightStatusDAO;
import by.epamtr.airline.entity.Flight;
import by.epamtr.airline.entity.User;
import by.epamtr.airline.service.FlightService;
import by.epamtr.airline.service.exception.ServiceException;

public class FlightServiceImpl implements FlightService {

	@Override
	public void addFlight(Flight flight) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deliteFlight(int idFlight) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateFlight(int idFlight) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeFlightStatus(int idFlight, FlightStatusDAO flightStatus) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Flight getFlight(int idFlight) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Flight> getFlights() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Flight> getFlights(FlightStatusDAO flightStatus) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Flight> getFlights(User user) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addCrewToFlight(User user) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateFlightCrew(int idFlight) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

}
