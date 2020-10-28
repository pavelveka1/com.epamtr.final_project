package by.epamtr.airline.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.epamtr.airline.controller.command.Command;
import by.epamtr.airline.entity.Aircraft;
import by.epamtr.airline.entity.Flight;
import by.epamtr.airline.service.AircraftService;
import by.epamtr.airline.service.FlightService;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.exception.ServiceException;

public class UpdateFlightCommand implements Command {

	
	private static final String PATH_TO_UPDATE_FLIGHT_DATA = "/WEB-INF/jsp/administrator_action/update_flight_data.jsp";
	private static final String PATH_TO_MAIN_PAGE="/WEB-INF/jsp/main_page.jsp";
	private static final String CURRENT_PAGE = "current_page";
	private static final String ID_FLIGHT_PARAM = "id_flight";
	private static final String SELECTED_FLIGHT_ATTR = "selected_flight";
	private static final String FLIGHT_ID_PARAM = "flight_id";
	private static final String AIRCRAFTS_ATTR = "aircrafts";
	private static final String FLIGHT_DATA_STATUS = "flight_data";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		FlightService flightService = serviceFactory.getFlightService();
		String selectedFlight = request.getParameter(ID_FLIGHT_PARAM);
		String flightDataStatus = request.getParameter(FLIGHT_DATA_STATUS);
		request.setAttribute(CURRENT_PAGE, PATH_TO_UPDATE_FLIGHT_DATA);
		if (flightDataStatus != null) {
			try {

				Flight flight = flightService.updateFlight(Integer.parseInt(request.getParameter(FLIGHT_ID_PARAM)),
						request, response);
				request.setAttribute(SELECTED_FLIGHT_ATTR, flight);
				request.getRequestDispatcher(PATH_TO_MAIN_PAGE).forward(request, response);

			} catch (ServiceException | ServletException | IOException e) {
				// rootLogger.error(e2);
				e.printStackTrace();
			}
		} else {
			int idSelectedFlight = Integer.parseInt(selectedFlight);
			try {
				request.setAttribute(CURRENT_PAGE, PATH_TO_UPDATE_FLIGHT_DATA);
				Flight flight = flightService.getFlight(idSelectedFlight);
				AircraftService aircraftService = serviceFactory.getAircraftService();
				List<Aircraft> aircrafts = aircraftService.getAircraftrs();
				request.setAttribute(AIRCRAFTS_ATTR, aircrafts);
				request.setAttribute(SELECTED_FLIGHT_ATTR, flight);
				request.getRequestDispatcher(PATH_TO_MAIN_PAGE).forward(request, response);
			} catch (ServiceException | ServletException | IOException e) {
				// rootLogger.error(e2);
				e.printStackTrace();
			}
		}
	}
}
