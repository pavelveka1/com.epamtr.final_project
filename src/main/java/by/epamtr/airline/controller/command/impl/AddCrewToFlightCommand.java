package by.epamtr.airline.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtr.airline.controller.ConstantController;
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
				// rootLogger.error(e1);
				e1.printStackTrace();
			}
			request.getSession().setAttribute(ConstantController.Attribute.FREE_POSITIONS_BY_FLIGHT, freeCrewPositions);
		} else {
			idFlight = Integer
					.parseInt((String) request.getSession().getAttribute(ConstantController.Attribute.FLIGHT_ID));
		}

		if (selectedPosition == null) {
			try {
				request.setAttribute(ConstantController.Attribute.CURRENT_PAGE,
						ConstantController.PathToPage.PATH_TO_ADD_CREW_TO_FLIGHT);
				request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request,
						response);
			} catch (ServletException | IOException e) {
				rootLogger.error(e);
			}
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
					request.setAttribute(ConstantController.Attribute.FREE_USERS_BY_POSITION, freeUsers);
					request.setAttribute(ConstantController.Attribute.CURRENT_PAGE,
							ConstantController.PathToPage.PATH_TO_ADD_CREW_TO_FLIGHT);
					request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request,
							response);
				} catch (ServiceException | ServletException | IOException e) {
					// rootLogger.error(e);
					e.printStackTrace();
				}
			} else {
				try {
					request.setAttribute(ConstantController.Attribute.CURRENT_PAGE,
							ConstantController.PathToPage.PATH_TO_CREW_BY_FLIGHT);
					userService.addCrewToFlight(idCrewPosition, idFlight, Integer.parseInt(selectedUserId));
					request.getSession().removeAttribute(ConstantController.Attribute.SELECTED_POSITION);
					Flight flight = flightService.getFlight(idFlight);
					request.getSession().setAttribute(ConstantController.Attribute.SELECTED_FLIGHT, flight);
					List<Crew> team = userService.getUsers(idFlight);
					request.setAttribute(ConstantController.Attribute.TEAM_BY_FLIGHT, team);
					request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request,
							response);
				} catch (ServiceException | ServletException | IOException e) {
					// rootLogger.error(e);
					e.printStackTrace();
				}

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
