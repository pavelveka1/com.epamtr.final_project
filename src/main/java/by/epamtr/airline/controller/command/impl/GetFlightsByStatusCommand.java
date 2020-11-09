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
import by.epamtr.airline.entity.Crew;
import by.epamtr.airline.entity.Flight;
import by.epamtr.airline.entity.FlightStatus;
import by.epamtr.airline.service.FlightService;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.UserService;
import by.epamtr.airline.service.exception.ServiceException;

public class GetFlightsByStatusCommand implements Command {
	private static final Logger LOGGER = Logger.getLogger(GetFlightsByStatusCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		FlightService flightService = serviceFactory.getFlightService();
		List<Flight> flights = null;
		request.getSession().setAttribute(ConstantController.Attribute.CURRENT_PAGE,
				ConstantController.PathToPage.PATH_FLIGHTS_BY_STATUS);
		String flightStatus = request.getParameter(ConstantController.Parameter.STATUS);
		String selectedFlight = request.getParameter(ConstantController.Parameter.ID_FLIGHT);

		if (selectedFlight == null) {
			if (flightStatus != null) {
				LOGGER.info(LoggerMessageConstant.GO_TO_PAGE_FLIGHTS_BY_STATUS);
				try {
					flights = flightService.getFlights(
							FlightStatus.valueOf(request.getParameter(ConstantController.Parameter.STATUS)));
				} catch (ServiceException e) {
					LOGGER.error(LoggerMessageConstant.ERROR_GET_FLIGHTS, e);
					request.setAttribute(ConstantController.Attribute.ERROR, e);
				}
				request.getSession().setAttribute(ConstantController.Attribute.FLIGHTS, flights);
				request.setAttribute(ConstantController.Attribute.SELECTED_STATUS, flightStatus);
			}
		} else {
			LOGGER.info(LoggerMessageConstant.GO_TO_CREW_BY_FLIGHT);
			int idSelectedFlight = Integer.parseInt(selectedFlight);
			UserService userService = serviceFactory.getUserService();

			Flight flight = null;
			try {
				flight = flightService.getFlight(idSelectedFlight);
			} catch (ServiceException e) {
				LOGGER.error(LoggerMessageConstant.ERROR_GET_FLIGHT, e);
				request.setAttribute(ConstantController.Attribute.ERROR, e);
			}
			request.setAttribute(ConstantController.Attribute.SELECTED_FLIGHT, flight);
			List<Crew> team = null;
			try {
				team = userService.getUsers(idSelectedFlight);
			} catch (ServiceException e) {
				LOGGER.error(LoggerMessageConstant.ERROR_GET_USERS_BY_FLIGHT_ID, e);
				request.setAttribute(ConstantController.Attribute.ERROR, e);
			}
			request.setAttribute(ConstantController.Attribute.TEAM_BY_FLIGHT, team);
			request.getSession().setAttribute(ConstantController.Attribute.CURRENT_PAGE,
					ConstantController.PathToPage.PATH_TO_GET_CREW_BY_FLIGHT);

		}
		try {
			request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request, response);
		} catch (ServletException | IOException e) {
			LOGGER.error(LoggerMessageConstant.ERROR_GO_TO_MAIN_PAGE, e);
		}
	}

}
