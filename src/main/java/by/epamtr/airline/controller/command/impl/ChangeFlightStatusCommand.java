package by.epamtr.airline.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtr.airline.controller.ConstantController;
import by.epamtr.airline.controller.command.Command;
import by.epamtr.airline.entity.Flight;
import by.epamtr.airline.entity.FlightStatus;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.exception.ServiceException;

public class ChangeFlightStatusCommand implements Command {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(ConstantController.Attribute.CURRENT_PAGE, ConstantController.PathToPage.PATH_TO_DELETE_FLIGHT);
		String newStatus = request.getParameter(ConstantController.Parameter.NEW_STATUS);
		request.getSession().setAttribute(ConstantController.Attribute.SELECTED_FLIGHT_STATUS_FOR_FLIGHTS, newStatus);
		FlightStatus flightStatus = FlightStatus.valueOf(newStatus);
		String idFlightParam = request.getParameter(ConstantController.Parameter.ID_FLIGHT);
		int idFlight = Integer.parseInt(idFlightParam);
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		try {
			serviceFactory.getFlightService().changeFlightStatus(idFlight, flightStatus);
		} catch (ServiceException e) {
			// rootLogger.error(e);
			e.printStackTrace();
		}
		String selectedFlightStatusForFlights = (String) request.getSession()
				.getAttribute(ConstantController.Attribute.SELECTED_FLIGHT_STATUS_FOR_FLIGHTS);
		try {
			List<Flight> flights = serviceFactory.getFlightService()
					.getFlights(FlightStatus.valueOf(selectedFlightStatusForFlights));
			request.getSession().setAttribute(ConstantController.Attribute.FLIGHTS, flights);
			request.getSession().setAttribute(ConstantController.Attribute.FLIGHT_STATUS, flightStatus);
			try {
				request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request, response);
			} catch (ServletException | IOException e) {
				rootLogger.error(e);
			}
		} catch (ServiceException e) {
			rootLogger.error(e);
		}

	}
}
