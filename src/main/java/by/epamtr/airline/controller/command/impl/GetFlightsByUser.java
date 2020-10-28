package by.epamtr.airline.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtr.airline.controller.command.Command;
import by.epamtr.airline.entity.Flight;
import by.epamtr.airline.entity.User;
import by.epamtr.airline.entity.UserRole;
import by.epamtr.airline.service.FlightService;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.UserService;
import by.epamtr.airline.service.exception.ServiceException;

public class GetFlightsByUser implements Command {
	private static final String PATH_TO_USERS_BY_ROLE = "/WEB-INF/jsp/user_action/users_by_role.jsp";
	private static final String PATH_TO_FLIGHTS_BY_USER = "/WEB-INF/jsp/user_action/flights_by_user.jsp";
	private static final String PATH_TO_MAIN_PAGE="/WEB-INF/jsp/main_page.jsp";
	private static final String CURRENT_PAGE="current_page";

	private static final String USER_ROLE_PARAM = "role";
	private static final String USER_ID_PARAM = "id_selected_user";

	private static final String USERS_BY_ROLE_ATTR="users_by_role";
	private static final String SELECTED_USER_ATTR = "selected_user";
	private static final String SELECTED_ROLE_ATTR="selected_role";
	private static final String FOUND_FLIGHTS_ATTR = "flights";
	ServiceFactory serviceFactory = ServiceFactory.getInstance();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String userRole = request.getParameter(USER_ROLE_PARAM);
		String selectedUserId = request.getParameter(USER_ID_PARAM);
		if (selectedUserId != null) {
			request.setAttribute(CURRENT_PAGE, PATH_TO_FLIGHTS_BY_USER);
				UserService userService = serviceFactory.getUserService();
				FlightService flightService = serviceFactory.getFlightService();
				try {
					int idUser = Integer.parseInt(selectedUserId);
					User user = userService.getUser(idUser);
					List<Flight> flights = flightService.getFlights(idUser);
					request.setAttribute(SELECTED_USER_ATTR, user);
					request.setAttribute(FOUND_FLIGHTS_ATTR, flights);
					request.getRequestDispatcher(PATH_TO_MAIN_PAGE).forward(request, response);
				} catch (NumberFormatException | ServiceException | ServletException | IOException e) {
					// rootLogger.error(e);
					e.printStackTrace();
				
			}
		} else {
			if (userRole != null) {
				request.setAttribute(CURRENT_PAGE, PATH_TO_USERS_BY_ROLE);
				request.setAttribute(SELECTED_ROLE_ATTR, userRole);
				try {
					List<User> users=serviceFactory.getUserService().getUsers(UserRole.valueOf(userRole));
					request.setAttribute(USERS_BY_ROLE_ATTR, users);
					request.getRequestDispatcher(PATH_TO_MAIN_PAGE).forward(request, response);
				} catch (ServiceException |ServletException | IOException e) {
					// rootLogger.error(e);
					e.printStackTrace();
				}
			}else {
				request.setAttribute(CURRENT_PAGE, PATH_TO_USERS_BY_ROLE);
				try {
					request.getRequestDispatcher(PATH_TO_MAIN_PAGE).forward(request, response);
				} catch (ServletException | IOException e) {
					// rootLogger.error(e);
					e.printStackTrace();
				}
			}
		}
	}

}
