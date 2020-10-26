package by.epamtr.airline.controller.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.epamtr.airline.controller.command.Command;
import by.epamtr.airline.entity.Aircraft;
import by.epamtr.airline.entity.AircraftType;
import by.epamtr.airline.service.AircraftService;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.exception.ServiceException;

public class UpdateAircraftCommand implements Command {
	private static final String PATH_TO_UPDATE_AIRCRAFT = "/WEB-INF/jsp/administrator_action/update_aircraft.jsp";
	private static final String PATH_TO_ADMIN_PAGE="/WEB-INF/jsp/administrator_page.jsp";
	private static final String CURRENT_PAGE="current_page";
	private static final String AIRCRAFT_NUMBER_PARAM="aircraft_numbers";
	private static final String AIRCRAFTS_ATTR="aircrafts";
	ServiceFactory serviceFactory = ServiceFactory.getInstance();
	AircraftService aircraftService = serviceFactory.getAircraftService();
	List<Aircraft> aircrafts = new ArrayList<Aircraft>();
	List<AircraftType> aircraftTypes=new ArrayList<AircraftType>();
	

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		String aircraftsParameter = request.getParameter(AIRCRAFT_NUMBER_PARAM);
		request.setAttribute(CURRENT_PAGE, PATH_TO_UPDATE_AIRCRAFT);
		if (aircraftsParameter == null) {
			try {
				aircrafts = aircraftService.getAircraftrs();
				request.getSession().setAttribute(AIRCRAFTS_ATTR, aircrafts);
				request.getRequestDispatcher(PATH_TO_ADMIN_PAGE).forward(request, response);
			} catch (ServletException | IOException | ServiceException e) {
				// rootLogger.error(e);
				e.printStackTrace();
			}
		} else {
			try {
				
				aircraftService.updateAircraft(request, response);
				aircrafts = aircraftService.getAircraftrs();
				request.getSession().setAttribute(AIRCRAFTS_ATTR, aircrafts);
				request.getRequestDispatcher(PATH_TO_ADMIN_PAGE).forward(request, response);
			} catch (ServletException | IOException | ServiceException e) {
				// rootLogger.error(e);
				e.printStackTrace();
			}
		}

	}

}
