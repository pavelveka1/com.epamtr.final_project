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
import by.epamtr.airline.service.FlightService;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.exception.ServiceException;

public class AddFlightCommand implements Command {
	private static final String PATH_TO_ADD_FLIGHT = "/WEB-INF/jsp/administrator_action/add_flight.jsp";
	private static final String PATH_TO_ADMIN_PAGE="/WEB-INF/jsp/administrator_page.jsp";
	private static final String CURRENT_PAGE="current_page";
	private static final String CURRENT_CITY_PARAM = "current_city";
	private static final String AIRCRAFTS_ATTR="aircrafts";
	private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private final AircraftService aircraftService=serviceFactory.getAircraftService();
	private final FlightService flightService = serviceFactory.getFlightService();
	private  List<Aircraft> aircrafts=new ArrayList<Aircraft>();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String currentCity=request.getParameter(CURRENT_CITY_PARAM);
		request.setAttribute(CURRENT_PAGE, PATH_TO_ADD_FLIGHT);
		if(currentCity==null) {
			try {
				try {
					aircrafts=aircraftService.getAircraftrs();
					request.setAttribute(AIRCRAFTS_ATTR, aircrafts);
				} catch (ServiceException e) {
					rootLogger.error("Error while getting registration number of aircrafts", e);
				}
				request.getRequestDispatcher(PATH_TO_ADMIN_PAGE).forward(request, response);
			} catch (ServletException | IOException e) {
				rootLogger.error("Error while going to add_flight.jsp page", e);
			}
		}else {
			try {
				flightService.addFlight(request, response);
				request.getRequestDispatcher(PATH_TO_ADMIN_PAGE).forward(request, response);
			} catch (ServiceException | ServletException | IOException e) {
				rootLogger.error("Error while adding new flght",e);
			}
			
		}
		
	}

}
