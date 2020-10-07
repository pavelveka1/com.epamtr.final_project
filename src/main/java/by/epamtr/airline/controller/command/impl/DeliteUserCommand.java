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

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();
		int idUser = 0;
		String login = request.getParameter("login");
		if (login != null) {
			try {
				userService.deliteUser(login);
			} catch (ServiceException e2) {
				// rootLogger.error(e2);
				e2.printStackTrace();
			}

		} else {
			try {
				request.getRequestDispatcher("/WEB-INF/jsp/administrator_action/delite_user.jsp").forward(request,
						response);
			} catch (ServletException | IOException e) {
				rootLogger.error(e);
			}

		}

	}

}