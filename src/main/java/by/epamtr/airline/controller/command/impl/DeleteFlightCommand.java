package by.epamtr.airline.controller.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtr.airline.controller.command.Command;
import by.epamtr.airline.entity.Aircraft;
import by.epamtr.airline.entity.Crew;
import by.epamtr.airline.entity.Flight;
import by.epamtr.airline.entity.FlightStatus;
import by.epamtr.airline.entity.User;
import by.epamtr.airline.entity.UserRole;
import by.epamtr.airline.service.AircraftService;
import by.epamtr.airline.service.FlightService;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.UserService;
import by.epamtr.airline.service.exception.ServiceException;

public class DeleteFlightCommand implements Command {

	private static final String PATH_TO_DELETE_FLIGHT = "/WEB-INF/jsp/administrator_action/delete_flight.jsp";
	private static final String PATH_TO_UPDATE_FLIGHT = "/WEB-INF/jsp/dispatcher_action/update_flights.jsp";
	private static final String PATH_TO_MAIN_PAGE="/WEB-INF/jsp/main_page.jsp";
	private static final String CURRENT_PAGE="current_page";
	private static final String FLIGHT_STATUS_PARAM = "flight_status";
	private static final String FLIGHT_STATUS_ATTR = "flight_status_attr";
	private static final String ID_FLIGHT_PARAM = "id_flight";
	private static final String SELECTED_FLIGHT_ATTR="selected_flight";
	private static final String SELECTED_FLIGHT_STATUS_ATTR="selected_flight_status_attr";
	private static final String FLIGHTS_ATTR = "flights";
	private static final String SIGNED_IN_USER_ATTRIBUTE="user";
	private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private final FlightService flightService = serviceFactory.getFlightService();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		List<Flight> flights;
		String flightStatus = request.getParameter(FLIGHT_STATUS_PARAM);
		String idFlightParam=request.getParameter(ID_FLIGHT_PARAM);
		String currentUserRole=((User)request.getSession().getAttribute(SIGNED_IN_USER_ATTRIBUTE)).getRole().getRole();
		if(currentUserRole.equalsIgnoreCase(UserRole.ADMINISTRATOR.getRole())) {
			request.setAttribute(CURRENT_PAGE, PATH_TO_DELETE_FLIGHT);
		}else {
			request.setAttribute(CURRENT_PAGE, PATH_TO_UPDATE_FLIGHT);
		}
		
		if(idFlightParam==null) {
			if (flightStatus != null) {
				request.getSession().setAttribute(SELECTED_FLIGHT_STATUS_ATTR, flightStatus);
				try {
					flights = flightService.getFlights(FlightStatus.valueOf(request.getParameter(FLIGHT_STATUS_PARAM)));
					request.getSession().setAttribute(FLIGHTS_ATTR, flights);
					request.getSession().setAttribute(FLIGHT_STATUS_ATTR, flightStatus);
					try {
						request.getRequestDispatcher(PATH_TO_MAIN_PAGE).forward(request, response);
					} catch (ServletException | IOException e) {
						rootLogger.error(e);
					}
				} catch (ServiceException e2) {
					// rootLogger.error(e2);
					e2.printStackTrace();
				}

			} else {
				try {
					request.getRequestDispatcher(PATH_TO_MAIN_PAGE).forward(request, response);
				} catch (ServletException | IOException e) {
					rootLogger.error(e);
				}
			}
		}else {
			int idFlight=Integer.parseInt(idFlightParam);
			
			try {
				flightService.deliteFlight(idFlight);
				flights = flightService.getFlights(FlightStatus.valueOf((String)request.getSession().getAttribute(SELECTED_FLIGHT_STATUS_ATTR)));
				request.getSession().setAttribute(FLIGHTS_ATTR, flights);
				request.getRequestDispatcher(PATH_TO_MAIN_PAGE).forward(request, response);
			} catch (ServiceException | ServletException | IOException e) {
				// rootLogger.error(e);
				e.printStackTrace();
			}
		}
	}
}
