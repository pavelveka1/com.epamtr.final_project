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
	private static final String PATH_TO_ADMIN_PAGE = "/WEB-INF/jsp/administrator_page.jsp";
	private static final String CURRENT_PAGE = "current_page";
	private static final String AIRCRAFTS_ATTR = "aircrafts";
	private static final String ID_AIRCRAFT_PARAM="id_aircraft";
	private static final String AIRCRAFT_STATUS_PARAM="aircraft_status";
	private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private final AircraftService aircraftService = serviceFactory.getAircraftService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String idAircraftParam = request.getParameter(ID_AIRCRAFT_PARAM);
		List<Aircraft> aircrafts = new ArrayList<Aircraft>();
		request.setAttribute(CURRENT_PAGE, PATH_TO_CHANGE_AIRCRAFT_STATUS);
		if (idAircraftParam == null) {
			try {
				aircrafts = aircraftService.getAircraftrs();
				request.setAttribute(AIRCRAFTS_ATTR, aircrafts);
				request.getRequestDispatcher(PATH_TO_ADMIN_PAGE).forward(request, response);
			} catch (ServletException | IOException | ServiceException e) {
				// rootLogger.error(e);
				e.printStackTrace();
			}
		} else {
			try {
				String status=request.getParameter(AIRCRAFT_STATUS_PARAM);
				int idAircraft=Integer.parseInt(idAircraftParam);
				aircraftService.changeAircraftStatus(idAircraft, status);
				aircrafts = aircraftService.getAircraftrs();
				request.setAttribute(AIRCRAFTS_ATTR, aircrafts);
				request.getRequestDispatcher(PATH_TO_ADMIN_PAGE).forward(request, response);
			} catch (ServletException | IOException | ServiceException e) {
				// rootLogger.error(e);
				e.printStackTrace();
			}
		}

	}

}
