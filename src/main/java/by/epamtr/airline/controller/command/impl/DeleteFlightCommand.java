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
import by.epamtr.airline.service.FlightService;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.exception.ServiceException;

public class DeleteFlightCommand implements Command {
	private static final Logger LOGGER = Logger.getLogger(DeleteFlightCommand.class);
	private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private final FlightService flightService = serviceFactory.getFlightService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		List<Flight> flights = null;
		String flightStatus = request.getParameter(ConstantController.Parameter.STATUS);
		String idFlightParam = request.getParameter(ConstantController.Parameter.ID_FLIGHT);
		String currentUserRole = ((User) request.getSession().getAttribute(ConstantController.Attribute.SIGNED_IN_USER))
				.getRole().getRole();
		if (currentUserRole.equalsIgnoreCase(UserRole.ADMINISTRATOR.getRole())) {
			request.getSession().setAttribute(ConstantController.Attribute.CURRENT_PAGE,
					ConstantController.PathToPage.PATH_TO_DELETE_FLIGHT);
		} else {
			request.getSession().setAttribute(ConstantController.Attribute.CURRENT_PAGE,
					ConstantController.PathToPage.PATH_TO_UPDATE_FLIGHT);
		}

		if (idFlightParam == null) {
			if (flightStatus != null) {
				request.getSession().setAttribute(ConstantController.Attribute.SELECTED_FLIGHT_STATUS_FOR_FLIGHTS,
						flightStatus);
				try {
					flights = flightService.getFlights(
							FlightStatus.valueOf(request.getParameter(ConstantController.Parameter.STATUS)));
				} catch (ServiceException e2) {
					LOGGER.error(LoggerMessageConstant.ERROR_GET_FLIGHTS, e2);
					request.setAttribute(ConstantController.Attribute.ERROR, e2);
				}
				request.getSession().setAttribute(ConstantController.Attribute.FLIGHTS, flights);
				request.getSession().setAttribute(ConstantController.Attribute.FLIGHT_STATUS, flightStatus);

			}
		} else {
			int idFlight = Integer.parseInt(idFlightParam);

			boolean result = false;
			try {
				result = flightService.deliteFlight(idFlight);
			} catch (ServiceException e) {
				LOGGER.error(LoggerMessageConstant.ERROR_DELETE_FLIGHT, e);
				request.setAttribute(ConstantController.Attribute.ERROR, e);
			}
			if (result) {
				LOGGER.info(LoggerMessageConstant.FLIGHT_IS_DELETED);
			} else {
				LOGGER.info(LoggerMessageConstant.FLIGHT_IS_NOT_DELETED);
			}
			try {
				flights = flightService.getFlights(FlightStatus.valueOf((String) request.getSession()
						.getAttribute(ConstantController.Attribute.SELECTED_FLIGHT_STATUS_FOR_FLIGHTS)));
			} catch (ServiceException e) {
				LOGGER.error(LoggerMessageConstant.ERROR_GET_FLIGHTS, e);
				request.setAttribute(ConstantController.Attribute.ERROR, e);
			}
			request.getSession().setAttribute(ConstantController.Attribute.FLIGHTS, flights);

		}
		try {
			request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request, response);
		} catch (ServletException | IOException e) {
			LOGGER.error(LoggerMessageConstant.ERROR_GO_TO_MAIN_PAGE, e);
		}
	}
}
