package by.epamtr.airline.dao.impl;

import java.util.List;

import by.epamtr.airline.dao.DispatcherDAO;
import by.epamtr.airline.dao.FlightStatusDAO;
import by.epamtr.airline.dao.exception.DAOException;
import by.epamtr.airline.entity.User;
import by.epamtr.airline.entity.UserRole;

public class SQLDispatcherDAO extends SQLUserDAO implements DispatcherDAO {

	@Override
	public void addCrewToFlight(User user) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editFlightCrew(int idFlight) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeFlightStatus(FlightStatusDAO flightStatus) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> showUsers(UserRole userRole) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}



}
