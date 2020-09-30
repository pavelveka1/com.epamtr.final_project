package by.epamtr.airline.service;

import by.epamtr.airline.service.impl.AircraftServiceImpl;
import by.epamtr.airline.service.impl.FlightServiceImpl;
import by.epamtr.airline.service.impl.UserServiceImpl;

public class ServiceFactory {

	private static final ServiceFactory instance = new ServiceFactory();

	private final AircraftService aircraftService = new AircraftServiceImpl();
	private final FlightService flightService = new FlightServiceImpl();
	private final UserService userService = new UserServiceImpl();

	private ServiceFactory() {
	}

	public static ServiceFactory getInstance() {
		return instance;
	}

	public AircraftService getAircraftService() {
		return aircraftService;
	}

	public FlightService getFlightService() {
		return flightService;
	}

	public UserService getUserService() {
		return userService;
	}

}
