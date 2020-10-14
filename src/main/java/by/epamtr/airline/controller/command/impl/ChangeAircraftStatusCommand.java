package by.epamtr.airline.controller.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtr.airline.controller.command.Command;
import by.epamtr.airline.entity.Aircraft;
import by.epamtr.airline.service.AircraftService;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.exception.ServiceException;

public class ChangeAircraftStatusCommand implements Command {
	private static final String PATH_TO_CHANGE_AIRCRAFT_STATUS = "/WEB-INF/jsp/administrator_action/change_aircraft_status.jsp";
	private static final String REGISTER_NUMBER_PARAM = "register_number";
	private static final String AIRCRAFTS_ATTR = "aircrafts";
	private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private final AircraftService aircraftService = serviceFactory.getAircraftService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String aircraftsParameter = request.getParameter(REGISTER_NUMBER_PARAM);
		List<Aircraft> aircrafts = new ArrayList<Aircraft>();

		if (aircraftsParameter == null) {
			try {
				aircrafts = aircraftService.getAircraftrs();
				request.setAttribute(AIRCRAFTS_ATTR, aircrafts);
				request.getRequestDispatcher(PATH_TO_CHANGE_AIRCRAFT_STATUS).forward(request, response);
			} catch (ServletException | IOException | ServiceException e) {
				// rootLogger.error(e);
				e.printStackTrace();
			}
		} else {
			try {
				aircraftService.changeAircraftStatus(request, response);
				aircrafts = aircraftService.getAircraftrs();
				request.setAttribute(AIRCRAFTS_ATTR, aircrafts);
				request.getRequestDispatcher(PATH_TO_CHANGE_AIRCRAFT_STATUS).forward(request, response);
			} catch (ServletException | IOException | ServiceException e) {
				// rootLogger.error(e);
				e.printStackTrace();
			}
		}

	}

}
