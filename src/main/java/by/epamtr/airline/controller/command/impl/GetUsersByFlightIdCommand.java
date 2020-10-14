package by.epamtr.airline.controller.command.impl;

import java.io.IOException;
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

public class GetUsersByFlightIdCommand implements Command {
	private static final String PATH_TO_GET_USERS_BY_FLIGHT = "/WEB-INF/jsp/administrator_action/users_by_flight.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		List<Flight> flights = (List<Flight>) request.getSession().getAttribute("flights");
		Integer idFlight =  (Integer) request.getSession().getAttribute("id_flight");
		String flightStatus=request.getParameter("flight_status");
		if(flightStatus!=null) {
			if (flights != null) {
				if (idFlight != null) {

				} else {

				}
			} else {
				ServiceFactory serviceFactory = ServiceFactory.getInstance();
				FlightService flightService = serviceFactory.getFlightService();
				try {
					flights = flightService.getFlights(FlightStatus.valueOf(request.getParameter("flight_status")));
					request.getSession().setAttribute("flights", flights);
					try {
						request.getRequestDispatcher(PATH_TO_GET_USERS_BY_FLIGHT).forward(request, response);
					} catch (ServletException | IOException e) {
						rootLogger.error(e);
					}
				}catch (ServiceException e2) {
					//	rootLogger.error(e2);
					e2.printStackTrace();
				}
				
			}
		}else {
			try {
				request.getRequestDispatcher(PATH_TO_GET_USERS_BY_FLIGHT).forward(request, response);
			} catch (ServletException | IOException e) {
				rootLogger.error(e);
			}
		}
		
		

	}

}
