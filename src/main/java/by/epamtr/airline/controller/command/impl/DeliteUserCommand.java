package by.epamtr.airline.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.epamtr.airline.controller.command.Command;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.UserService;
import by.epamtr.airline.service.exception.ServiceException;

public class DeliteUserCommand implements Command {
	private static final String PATH_TO_DELITE_USER_PAGE = "/WEB-INF/jsp/administrator_action/delite_user.jsp";
	private static final String LOGIN_PARAM="login";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();
		int idUser = 0;
		String login = request.getParameter(LOGIN_PARAM);
		if (login != null) {
			try {
				userService.deliteUser(login);

			} catch (ServiceException e2) {
				// rootLogger.error(e2);
				e2.printStackTrace();
			}

			try {
				request.getRequestDispatcher(PATH_TO_DELITE_USER_PAGE).forward(request, response);
			} catch (ServletException | IOException e) {
				rootLogger.error(e);
			}

		}else {
			try {
				request.getRequestDispatcher(PATH_TO_DELITE_USER_PAGE).forward(request, response);
			} catch (ServletException | IOException e) {
				rootLogger.error(e);
			}
		}

	}
}
