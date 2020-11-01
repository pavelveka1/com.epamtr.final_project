package by.epamtr.airline.controller.command.impl;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtr.airline.controller.ConstantController;
import by.epamtr.airline.controller.command.Command;
import by.epamtr.airline.entity.Crew;
import by.epamtr.airline.entity.Flight;
import by.epamtr.airline.entity.FlightStatus;
import by.epamtr.airline.service.FlightService;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.UserService;
import by.epamtr.airline.service.exception.ServiceException;

public class GetFlightsByStatusCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		FlightService flightService = serviceFactory.getFlightService();
		List<Flight> flights;
		request.getSession().setAttribute(ConstantController.Attribute.CURRENT_PAGE, ConstantController.PathToPage.PATH_FLIGHTS_BY_STATUS);
		String flightStatus = request.getParameter(ConstantController.Parameter.STATUS);
		String selectedFlight=request.getParameter(ConstantController.Parameter.ID_FLIGHT);
		
		if(selectedFlight==null) {
			if (flightStatus != null) {
				
				try {
					flights = flightService.getFlights(FlightStatus.valueOf(request.getParameter(ConstantController.Parameter.STATUS)));
					request.getSession().setAttribute(ConstantController.Attribute.FLIGHTS, flights);
					request.setAttribute(ConstantController.Attribute.SELECTED_STATUS, flightStatus);
					try {
						request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request, response);
					} catch (ServletException | IOException e) {
						rootLogger.error(e);
					}
				} catch (ServiceException e2) {
					// rootLogger.error(e2);
					e2.printStackTrace();
				}

			} else {
				try {
					request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request, response);
				} catch (ServletException | IOException e) {
					rootLogger.error(e);
				}
			}
		}else {
			int idSelectedFlight=Integer.parseInt(selectedFlight);
			UserService userService = serviceFactory.getUserService();
			try {
				Flight flight=flightService.getFlight(idSelectedFlight);
				request.setAttribute(ConstantController.Attribute.SELECTED_FLIGHT, flight);
				List<Crew> team=userService.getUsers(idSelectedFlight);
				request.setAttribute(ConstantController.Attribute.TEAM_BY_FLIGHT, team);
				request.getSession().setAttribute(ConstantController.Attribute.CURRENT_PAGE, ConstantController.PathToPage.PATH_TO_GET_CREW_BY_FLIGHT);
				request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request, response);
			} catch (ServiceException | ServletException | IOException e) {
				// rootLogger.error(e2);
				e.printStackTrace();
			}
		}
		

	}

}
