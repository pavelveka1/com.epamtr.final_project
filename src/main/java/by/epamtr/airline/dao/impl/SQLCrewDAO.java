package by.epamtr.airline.dao.impl;

import java.util.List;

import by.epamtr.airline.dao.CrewDAO;
import by.epamtr.airline.dao.exception.DAOException;
import by.epamtr.airline.entity.Flight;
import by.epamtr.airline.entity.User;

public class SQLCrewDAO extends SQLUserDAO implements CrewDAO {

	@Override
	public List<Flight> showFlights(User user) throws DAOException {
		return null;
		// TODO Auto-generated method stub
		
	}

}
