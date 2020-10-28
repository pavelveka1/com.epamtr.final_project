package by.epamtr.airline.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtr.airline.controller.command.Command;
import by.epamtr.airline.entity.AircraftType;
import by.epamtr.airline.service.AircraftService;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.exception.ServiceException;

public class AddAircraftCommand implements Command {
	private static final String PATH_TO_ADD_AIRCRAFT = "/WEB-INF/jsp/administrator_action/add_aircraft.jsp";
	private static final String PATH_TO_MAIN_PAGE="/WEB-INF/jsp/main_page.jsp";
	private static final String CURRENT_PAGE="current_page";
	ServiceFactory serviceFactory = ServiceFactory.getInstance();
	AircraftService aircraftService = serviceFactory.getAircraftService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		List<AircraftType> aircraftTypes = null;
		try {
			aircraftTypes = aircraftService.getAircraftTypes();
		} catch (ServiceException e1) {
			// rootLogger.error(e);
			e1.printStackTrace();
		}
		
		request.getSession().setAttribute("aircraftTypes", aircraftTypes);
		String registerNumberParameter = request.getParameter("register_number");
		request.setAttribute(CURRENT_PAGE, PATH_TO_ADD_AIRCRAFT);
		if (registerNumberParameter == null) {
			try {
				
				request.getRequestDispatcher(PATH_TO_MAIN_PAGE).forward(request, response);
			} catch (ServletException | IOException  e) {
				// rootLogger.error(e);
				e.printStackTrace();
			}
		} else {
			try {
				aircraftService.addAircraft(request, response);
				request.getRequestDispatcher(PATH_TO_MAIN_PAGE).forward(request, response);
			} catch (ServletException | IOException | ServiceException e) {
				// rootLogger.error(e);
				e.printStackTrace();
			}
		}

	}

}
