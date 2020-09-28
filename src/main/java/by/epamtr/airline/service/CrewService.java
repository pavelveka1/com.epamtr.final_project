package by.epamtr.airline.service;

import java.util.List;

import by.epamtr.airline.entity.Flight;
import by.epamtr.airline.entity.User;
import by.epamtr.airline.service.exception.ServiceException;

public interface CrewService {
	List<Flight> showFlights(User user) throws ServiceException;

	

}
