package by.epamtr.airline.controller.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.epamtr.airline.controller.command.Command;
import by.epamtr.airline.entity.Flight;
import by.epamtr.airline.entity.FlightStatus;
import by.epamtr.airline.service.FlightService;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.exception.ServiceException;

public class GetFlightsByStatusCommand implements Command {
	private static final String PATH_TO_SHOW_FLIGHTS = "/WEB-INF/jsp/administrator_action/get_flights_by_status.jsp";
	private static final String FLIGHT_STATUS_PARAM = "flight_status";
	private static final String FLIGHTS_ATTR="flights";
	private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private final FlightService flightService = serviceFactory.getFlightService();
	private  List<Flight> flights=new ArrayList<Flight>();
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String status=request.getParameter(FLIGHT_STATUS_PARAM);
		if(status==null) {
			try {
				request.getRequestDispatcher(PATH_TO_SHOW_FLIGHTS).forward(request, response);
			} catch (ServletException | IOException e) {
				rootLogger.error("Error while going to get_flights_by_status.jsp page", e);
			}
		}else {
			try {
				FlightStatus flightStatus=FlightStatus.valueOf("STAFF RECRUITMENT");
				flights=flightService.getFlights(flightStatus);
				request.setAttribute(FLIGHTS_ATTR, flights);
				request.getRequestDispatcher(PATH_TO_SHOW_FLIGHTS).forward(request, response);
			} catch (ServiceException | ServletException | IOException e) {
				rootLogger.error("Error while adding new flght",e);
			}
			
		}
		
	}

}
