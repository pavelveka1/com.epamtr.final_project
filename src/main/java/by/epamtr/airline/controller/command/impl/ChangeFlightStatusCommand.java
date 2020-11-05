package by.epamtr.airline.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.epamtr.airline.controller.ConstantController;
import by.epamtr.airline.controller.LoggerMessageConstant;
import by.epamtr.airline.controller.command.Command;
import by.epamtr.airline.entity.Flight;
import by.epamtr.airline.entity.FlightStatus;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.exception.ServiceException;

public class ChangeFlightStatusCommand implements Command {
	private static final Logger LOGGER = Logger.getLogger(ChangeFlightStatusCommand.class);
	
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
			boolean result=serviceFactory.getFlightService().changeFlightStatus(idFlight, flightStatus);
			if(result) {
				LOGGER.info(LoggerMessageConstant.FLIGHT_STATUS_CHANGED);
			}else {
				LOGGER.info(LoggerMessageConstant.FLIGHT_STATUS_NOT_CHANGED);
			}
		} catch (ServiceException e) {
			LOGGER.error(LoggerMessageConstant.ERROR_SHANGE_FLIGHT_STATUS, e);
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
				LOGGER.error(LoggerMessageConstant.ERROR_GO_TO_MAIN_PAGE, e);
			}
		} catch (ServiceException e) {
			LOGGER.error(LoggerMessageConstant.ERROR_GO_TO_MAIN_PAGE, e);
		}

	}
}
