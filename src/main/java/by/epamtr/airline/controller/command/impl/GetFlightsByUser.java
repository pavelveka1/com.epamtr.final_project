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
import by.epamtr.airline.entity.User;
import by.epamtr.airline.entity.UserRole;
import by.epamtr.airline.service.FlightService;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.UserService;
import by.epamtr.airline.service.exception.ServiceException;

public class GetFlightsByUser implements Command {
	private static final Logger LOGGER = Logger.getLogger(GetFlightsByUser.class);
	ServiceFactory serviceFactory = ServiceFactory.getInstance();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String userRole = request.getParameter(ConstantController.Parameter.USER_ROLE);
		String selectedUserId = request.getParameter(ConstantController.Parameter.USER_ID);

		if (userRole != null) {

			LOGGER.info(LoggerMessageConstant.GO_TO_PAGE_USERS_BY_ROLE);
			request.setAttribute(ConstantController.Attribute.CURRENT_PAGE,
					ConstantController.PathToPage.PATH_TO_USERS_BY_ROLE);
			request.setAttribute(ConstantController.Attribute.SELECTED_ROLE, userRole);
			try {
				List<User> users = serviceFactory.getUserService().getUsers(UserRole.valueOf(userRole));
				request.setAttribute(ConstantController.Attribute.USERS_BY_ROLE, users);
			} catch (ServiceException e) {
				LOGGER.error(LoggerMessageConstant.ERROR_GET_USERS_BY_ROLE, e);
				request.setAttribute(ConstantController.Attribute.ERROR, e);
			}

		} else {
			if (selectedUserId != null) {
				LOGGER.info(LoggerMessageConstant.GO_TO_PAGE_FLIGHTS_BY_USER);
				request.setAttribute(ConstantController.Attribute.CURRENT_PAGE,
						ConstantController.PathToPage.PATH_TO_FLIGHTS_BY_USER);
				UserService userService = serviceFactory.getUserService();
				FlightService flightService = serviceFactory.getFlightService();
				try {
					int idUser = Integer.parseInt(selectedUserId);
					User user = userService.getUser(idUser);
					List<Flight> flights = flightService.getFlights(idUser);
					request.setAttribute(ConstantController.Attribute.SELECTED_USER, user);
					request.setAttribute(ConstantController.Attribute.FLIGHTS, flights);
				} catch (ServiceException e) {
					LOGGER.error(LoggerMessageConstant.ERROR_GET_FLIGHTS_BY_USER_ID, e);
					request.setAttribute(ConstantController.Attribute.ERROR, e);
				}
			} else {
				LOGGER.info(LoggerMessageConstant.GO_TO_PAGE_CHOOSE_USERS_ROLE);
				request.setAttribute(ConstantController.Attribute.CURRENT_PAGE,
						ConstantController.PathToPage.PATH_TO_USERS_BY_ROLE);
			}

		}
		try {
			request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request, response);
		} catch (ServletException | IOException e) {
			LOGGER.error(LoggerMessageConstant.ERROR_GO_TO_MAIN_PAGE, e);
		}
	}
}
