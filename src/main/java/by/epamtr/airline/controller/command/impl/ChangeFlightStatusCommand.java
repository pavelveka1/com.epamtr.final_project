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
import by.epamtr.airline.entity.User;
import by.epamtr.airline.entity.UserRole;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.exception.ServiceException;

public class ChangeFlightStatusCommand implements Command {
	private static final Logger LOGGER = Logger.getLogger(ChangeFlightStatusCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String currentUserRole = ((User) request.getSession().getAttribute(ConstantController.Attribute.SIGNED_IN_USER))
				.getRole().getRole();
		if (currentUserRole.equalsIgnoreCase(UserRole.ADMINISTRATOR.getRole())) {
			request.getSession().setAttribute(ConstantController.Attribute.CURRENT_PAGE,
					ConstantController.PathToPage.PATH_TO_DELETE_FLIGHT);
		} else {
			request.setAttribute(ConstantController.Attribute.CURRENT_PAGE,
					ConstantController.PathToPage.PATH_TO_UPDATE_FLIGHT);
		}
		String newStatus = request.getParameter(ConstantController.Parameter.NEW_STATUS);
		request.getSession().setAttribute(ConstantController.Attribute.SELECTED_FLIGHT_STATUS_FOR_FLIGHTS, newStatus);
		FlightStatus flightStatus = FlightStatus.valueOf(newStatus);
		String idFlightParam = request.getParameter(ConstantController.Parameter.ID_FLIGHT);
		int idFlight = Integer.parseInt(idFlightParam);
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		boolean result = false;
		try {
			result = serviceFactory.getFlightService().changeFlightStatus(idFlight, flightStatus);
		} catch (ServiceException e) {
			LOGGER.error(LoggerMessageConstant.ERROR_SHANGE_FLIGHT_STATUS, e);
			request.setAttribute(ConstantController.Attribute.ERROR, e);
		}
		if (result) {
			LOGGER.info(LoggerMessageConstant.FLIGHT_STATUS_CHANGED);
		} else {
			LOGGER.info(LoggerMessageConstant.FLIGHT_STATUS_NOT_CHANGED);
		}

		String selectedFlightStatusForFlights = (String) request.getSession()
				.getAttribute(ConstantController.Attribute.SELECTED_FLIGHT_STATUS_FOR_FLIGHTS);
		List<Flight> flights = null;
		try {
			flights = serviceFactory.getFlightService()
					.getFlights(FlightStatus.valueOf(selectedFlightStatusForFlights));
		} catch (ServiceException e) {
			LOGGER.error(LoggerMessageConstant.ERROR_GET_FLIGHTS, e);
			request.setAttribute(ConstantController.Attribute.ERROR, e);
		}
		request.getSession().setAttribute(ConstantController.Attribute.FLIGHTS, flights);
		request.getSession().setAttribute(ConstantController.Attribute.FLIGHT_STATUS, flightStatus);

		try {
			request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request, response);
		} catch (ServletException | IOException e) {
			LOGGER.error(LoggerMessageConstant.ERROR_GO_TO_MAIN_PAGE, e);
		}

	}
}
