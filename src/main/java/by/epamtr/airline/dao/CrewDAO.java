package by.epamtr.airline.dao;

import java.util.List;

import by.epamtr.airline.dao.exception.DAOException;
import by.epamtr.airline.entity.Flight;
import by.epamtr.airline.entity.User;

public interface CrewDAO {
	List<Flight> showFlights(User user) throws DAOException;

}
