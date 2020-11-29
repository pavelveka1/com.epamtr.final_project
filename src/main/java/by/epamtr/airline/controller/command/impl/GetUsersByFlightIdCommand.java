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
import by.epamtr.airline.entity.User;
import by.epamtr.airline.entity.UserRole;
import by.epamtr.airline.service.FlightService;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.UserService;
import by.epamtr.airline.service.exception.ServiceException;

public class GetUsersByFlightIdCommand implements Command {
	private static final Logger logger = Logger.getLogger(GetUsersByFlightIdCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		FlightService flightService = serviceFactory.getFlightService();
		List<Flight> flights;
		String flightStatus = request.getParameter(ConstantController.Parameter.STATUS);
		String selectedFlight = request.getParameter(ConstantController.Parameter.ID_FLIGHT);
		String addCrewParam = request.getParameter(ConstantController.Parameter.ADD_CREW_TO_FLIGHT);
		request.getSession().setAttribute(ConstantController.Attribute.CURRENT_PAGE,
				ConstantController.PathToPage.PATH_TO_GET_USERS_BY_FLIGHT);
		if (addCrewParam == null) {
			if (selectedFlight == null) {
				request.getSession().setAttribute(ConstantController.Attribute.SELECTED_FLIGHT, selectedFlight);
				if (flightStatus != null) {
					logger.info(LoggerMessageConstant.GO_TO_PAGE_FLIGHTS_BY_STATUS);
					try {
						flights = flightService.getFlights(
								FlightStatus.valueOf(request.getParameter(ConstantController.Parameter.STATUS)));
						request.getSession().setAttribute(ConstantController.Attribute.FLIGHTS, flights);
					} catch (ServiceException e2) {
						logger.error(LoggerMessageConstant.ERROR_GET_FLIGHTS, e2);
						request.setAttribute(ConstantController.Attribute.ERROR, ConstantController.Attribute.SOMETHING_GOES_WRONG);
					}
				} else {
					logger.info(LoggerMessageConstant.GO_TO_PAGE_CHOOSE_FLIGHT_STATUS);
					request.getSession().setAttribute(ConstantController.Attribute.CURRENT_PAGE,
							ConstantController.PathToPage.PATH_TO_GET_USERS_BY_FLIGHT);
				}
			} else {
				int idSelectedFlight = Integer.parseInt(selectedFlight);
				UserService userService = serviceFactory.getUserService();

				Flight flight = null;
				try {
					flight = flightService.getFlight(idSelectedFlight);
				} catch (ServiceException e) {
					logger.error(LoggerMessageConstant.ERROR_GET_FLIGHT, e);
					request.setAttribute(ConstantController.Attribute.ERROR, ConstantController.Attribute.SOMETHING_GOES_WRONG);
				}
				request.getSession().setAttribute(ConstantController.Attribute.SELECTED_FLIGHT, flight);
				List<Crew> team = null;
				try {
					team = userService.getUsers(idSelectedFlight);
				} catch (ServiceException e) {
					logger.error(LoggerMessageConstant.ERROR_GET_USERS_BY_FLIGHT_ID, e);
					request.setAttribute(ConstantController.Attribute.ERROR, ConstantController.Attribute.SOMETHING_GOES_WRONG);
				}
				request.getSession().setAttribute(ConstantController.Attribute.TEAM_BY_FLIGHT, team);

				UserRole userCurrentRole = ((User) request.getSession()
						.getAttribute(ConstantController.Attribute.SIGNED_IN_USER)).getRole();
				switch (userCurrentRole) {
				case DISPATCHER:
				case ADMINISTRATOR:
					request.setAttribute(ConstantController.Attribute.CURRENT_PAGE,
							ConstantController.PathToPage.PATH_TO_GET_CREW_BY_FLIGHT);
					break;
				case MANAGER:
				case PILOT:
				case ENGINEER:
				case ATTENDANT:
					request.getSession().setAttribute(ConstantController.Attribute.CURRENT_PAGE,
							ConstantController.PathToPage.PATH_TO_GET_CREW_BY_FLIGHT_FOR_CREW);
					break;
				}
			}
		} else {
			request.getSession().setAttribute(ConstantController.Attribute.CURRENT_PAGE,
					ConstantController.PathToPage.PATH_TO_GET_CREW_BY_FLIGHT);
		}
		try {
			request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request, response);
		} catch (ServletException | IOException e) {
			logger.error(LoggerMessageConstant.ERROR_GO_TO_MAIN_PAGE, e);
		}
	}

}
