package by.epamtr.airline.dao.impl;

import java.util.List;

import by.epamtr.airline.dao.FlightDAO;
import by.epamtr.airline.dao.FlightStatusDAO;
import by.epamtr.airline.dao.exception.DAOException;
import by.epamtr.airline.entity.Flight;
import by.epamtr.airline.entity.User;

public class SQLFlightDAO implements FlightDAO {

	@Override
	public void addFlight(Flight flight) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deliteFlight(int idFlight) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateFlight(int idFlight) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeFlightStatus(int idFlight, FlightStatusDAO flightStatus) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Flight getFlight(int idFlight) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Flight> getFlights() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Flight> getFlights(FlightStatusDAO flightStatus) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Flight> getFlights(User user) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addCrewToFlight(User user) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateFlightCrew(int idFlight) throws DAOException {
		// TODO Auto-generated method stub
		
	}

}
