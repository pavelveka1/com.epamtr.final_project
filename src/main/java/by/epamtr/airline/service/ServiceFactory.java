package by.epamtr.airline.service;

import by.epamtr.airline.service.impl.AdminServiceImpl;
import by.epamtr.airline.service.impl.CrewServiceImpl;
import by.epamtr.airline.service.impl.DispatcherServiceImpl;
import by.epamtr.airline.service.impl.UserServiceImpl;

public class ServiceFactory {

	private static final ServiceFactory instance = new ServiceFactory();

	private final AdminService adminService = new AdminServiceImpl();
	private final DispatcherService dispatcherService = new DispatcherServiceImpl();
	private final CrewService crewService = new CrewServiceImpl();
	private final UserService userService = new UserServiceImpl();

	private ServiceFactory() {
	}

	public static ServiceFactory getInstance() {
		return instance;
	}

	public AdminService getAdminService() {
		return adminService;
	}

	public DispatcherService getDispatcherService() {
		return dispatcherService;
	}

	public CrewService getCrewService() {
		return crewService;
	}

	public UserService getUserService() {
		return userService;
	}



}
