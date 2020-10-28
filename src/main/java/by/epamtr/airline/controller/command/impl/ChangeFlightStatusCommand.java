package by.epamtr.airline.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtr.airline.controller.command.Command;
import by.epamtr.airline.entity.Flight;
import by.epamtr.airline.entity.FlightStatus;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.exception.ServiceException;

public class ChangeFlightStatusCommand implements Command {
	private static final String NEW_STATUS_PARAM = "flight_new_status";
	private static final String ID_FLIGHT_PARAM = "id_flight";
	private static final String SELECTED_FLIGHT_STATUS_FOR_FLIGHTS_ATTR = "selected_flight_status_attr";
	private static final String FLIGHT_STATUS_ATTR = "flight_status_attr";
	private static final String SELECTED_FLIGHT_STATUS_ATTR = "selected_flight_status_attr";
	private static final String FLIGHTS_ATTR = "flights";
	private static final String PATH_TO_DELETE_FLIGHT = "/WEB-INF/jsp/administrator_action/delete_flight.jsp";
	private static final String PATH_TO_MAIN_PAGE="/WEB-INF/jsp/main_page.jsp";
	private static final String CURRENT_PAGE = "current_page";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(CURRENT_PAGE, PATH_TO_DELETE_FLIGHT);
		String newStatus = request.getParameter(NEW_STATUS_PARAM);
		FlightStatus flightStatus = FlightStatus.valueOf(newStatus);
		String idFlightParam = request.getParameter(ID_FLIGHT_PARAM);
		int idFlight = Integer.parseInt(idFlightParam);
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		try {
			serviceFactory.getFlightService().changeFlightStatus(idFlight, flightStatus);
		} catch (ServiceException e) {
			// rootLogger.error(e);
			e.printStackTrace();
		}
		String selectedFlightStatusForFlights = (String) request.getSession()
				.getAttribute(SELECTED_FLIGHT_STATUS_FOR_FLIGHTS_ATTR);
		try {
			List<Flight> flights = serviceFactory.getFlightService()
					.getFlights(FlightStatus.valueOf(selectedFlightStatusForFlights));
			request.getSession().setAttribute(FLIGHTS_ATTR, flights);
			request.getSession().setAttribute(FLIGHT_STATUS_ATTR, flightStatus);
			try {
				request.getRequestDispatcher(PATH_TO_MAIN_PAGE).forward(request, response);
			} catch (ServletException | IOException e) {
				rootLogger.error(e);
			}
		} catch (ServiceException e) {
			rootLogger.error(e);
		}

	}
}
