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

public class DeleteAircraftCommand implements Command {
	private static final String PATH_TO_DELETE_AIRCRAFT = "/WEB-INF/jsp/administrator_action/delete_aircraft.jsp";
	private static final String AIRCRAFT_NUMBER_PARAM="aircraft_numbers";
	private static final String AIRCRAFTS_ATTR = "aircrafts";
	private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private final AircraftService aircraftService = serviceFactory.getAircraftService();
	 private List<Aircraft> aircrafts = new ArrayList<Aircraft>();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		String aircraftsParameter = request.getParameter(AIRCRAFT_NUMBER_PARAM);
		if (aircraftsParameter == null) {
			try {
				aircrafts = aircraftService.getAircraftrs();
				request.setAttribute(AIRCRAFTS_ATTR, aircrafts);
				request.getRequestDispatcher(PATH_TO_DELETE_AIRCRAFT).forward(request, response);
			} catch (ServletException | IOException | ServiceException e) {
				// rootLogger.error(e);
				e.printStackTrace();
			}
		} else {
			try {
				aircraftService.deleteAircraft(request.getParameter(AIRCRAFT_NUMBER_PARAM));
				aircrafts = aircraftService.getAircraftrs();
				request.setAttribute(AIRCRAFTS_ATTR, aircrafts);
				request.getRequestDispatcher(PATH_TO_DELETE_AIRCRAFT).forward(request, response);
			} catch (ServletException | IOException | ServiceException e) {
				// rootLogger.error(e);
				e.printStackTrace();
			}
		}

	}

}
