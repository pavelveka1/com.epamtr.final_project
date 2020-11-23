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
import by.epamtr.airline.entity.CrewPosition;
import by.epamtr.airline.entity.Flight;
import by.epamtr.airline.entity.User;
import by.epamtr.airline.service.FlightService;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.UserService;
import by.epamtr.airline.service.exception.ServiceException;

public class AddCrewToFlightCommand implements Command {
	private static final Logger LOGGER = Logger.getLogger(AddCrewToFlightCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		FlightService flightService = serviceFactory.getFlightService();
		String selectedUserId = request.getParameter(ConstantController.Parameter.SELECTED_USER);

		String selectedPosition = (String) request.getSession()
				.getAttribute(ConstantController.Attribute.SELECTED_POSITION);
		if (selectedPosition == null) {
			selectedPosition = request.getParameter(ConstantController.Parameter.SELECTED_POSITION);
		}
		String flightIdParam = request.getParameter(ConstantController.Parameter.FLIGHT_ID);
		List<User> freeUsers = null;
		int idFlight = 0;
		if ((flightIdParam != null) && (!(flightIdParam.equals("")))) {
			request.getSession().setAttribute(ConstantController.Attribute.FLIGHT_ID, flightIdParam);
			idFlight = Integer.parseInt(flightIdParam);
			List<CrewPosition> freeCrewPositions = null;
			try {
				freeCrewPositions = flightService.getFreeCrewPositions(idFlight);
			} catch (ServiceException e1) {
				LOGGER.error(LoggerMessageConstant.ERROR_GET_FREE_CREW_POSITIONS, e1);
				request.setAttribute(ConstantController.Attribute.ERROR, e1);

			}
			request.getSession().setAttribute(ConstantController.Attribute.FREE_POSITIONS_BY_FLIGHT, freeCrewPositions);
		} else {
			idFlight = Integer
					.parseInt((String) request.getSession().getAttribute(ConstantController.Attribute.FLIGHT_ID));
		}

		if (selectedPosition == null) {
			request.getSession().setAttribute(ConstantController.Attribute.CURRENT_PAGE,
					ConstantController.PathToPage.PATH_TO_ADD_CREW_TO_FLIGHT);
		} else {
			String selectedPositionAttr = (String) request.getSession()
					.getAttribute(ConstantController.Attribute.SELECTED_POSITION);
			if (selectedPositionAttr == null) {
				request.getSession().setAttribute(ConstantController.Attribute.SELECTED_POSITION, selectedPosition);
			}
			int idCrewPosition = getIdSelectedPosition(request);
			UserService userService = serviceFactory.getUserService();
			if (selectedUserId == null) {
				try {
					freeUsers = userService.getFreeUsers(idFlight, selectedPosition);
				} catch (ServiceException e) {
					LOGGER.error(LoggerMessageConstant.ERROR_GET_FREE_USERS, e);
					request.setAttribute(ConstantController.Attribute.ERROR, e);
				}
				request.setAttribute(ConstantController.Attribute.FREE_USERS_BY_POSITION, freeUsers);
				request.getSession().setAttribute(ConstantController.Attribute.CURRENT_PAGE,
						ConstantController.PathToPage.PATH_TO_ADD_CREW_TO_FLIGHT);

			} else {

				request.getSession().setAttribute(ConstantController.Attribute.CURRENT_PAGE,
						ConstantController.PathToPage.PATH_TO_CREW_BY_FLIGHT);
				boolean result = false;
				try {
					result = userService.addCrewToFlight(idCrewPosition, idFlight, Integer.parseInt(selectedUserId));
				} catch (ServiceException e) {
					LOGGER.error(LoggerMessageConstant.ERROR_ADD_CREW_TO_FLIGHT, e);
					request.setAttribute(ConstantController.Attribute.ERROR, e);
				}
				if (result) {
					LOGGER.info(LoggerMessageConstant.USER_ADDED_TO_FLIGHT);
				} else {
					LOGGER.info(LoggerMessageConstant.USER_NOT_ADDED_TO_FLIGHT);
				}

				request.getSession().removeAttribute(ConstantController.Attribute.SELECTED_POSITION);
				Flight flight = null;
				List<Crew> team = null;
				try {
					flight = flightService.getFlight(idFlight);
					team = userService.getUsers(idFlight);
				} catch (ServiceException e) {
					LOGGER.error(LoggerMessageConstant.ERROR_GET_DATA_FROM_DB, e);
					request.setAttribute(ConstantController.Attribute.ERROR, e);
				}
				request.getSession().setAttribute(ConstantController.Attribute.SELECTED_FLIGHT, flight);
				request.setAttribute(ConstantController.Attribute.TEAM_BY_FLIGHT, team);

			}

		}
		try {
			request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request, response);
		} catch (ServletException | IOException e) {
			LOGGER.error(LoggerMessageConstant.ERROR_GO_TO_MAIN_PAGE, e);
			request.setAttribute(ConstantController.Attribute.ERROR, e);
			try {
				request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request,
						response);
			} catch (ServletException | IOException e1) {
				LOGGER.error(LoggerMessageConstant.ERROR_GO_TO_MAIN_PAGE, e1);
			}
		}
	}

	private int getIdSelectedPosition(HttpServletRequest request) {
		String selectedPosition = (String) request.getSession()
				.getAttribute(ConstantController.Attribute.SELECTED_POSITION);
		List<CrewPosition> freeCrewPositions = (List<CrewPosition>) request.getSession()
				.getAttribute(ConstantController.Attribute.FREE_POSITIONS_BY_FLIGHT);
		for (CrewPosition crewPosition : freeCrewPositions) {
			if (crewPosition.getCrewPosition().equals(selectedPosition)) {
				return crewPosition.getIdCrewPosition();
			}
		}
		return 0;

	}
}
