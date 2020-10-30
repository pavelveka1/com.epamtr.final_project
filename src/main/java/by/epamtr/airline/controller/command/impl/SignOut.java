package by.epamtr.airline.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.epamtr.airline.controller.command.Command;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.UserService;
import by.epamtr.airline.service.exception.ServiceException;

public class SignOut implements Command {
	private static final String PATH_TO_CONTROLLER = "/Controller?command=GO_TO_LOGIN_PAGE";
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		try {
			request.getRequestDispatcher(PATH_TO_CONTROLLER).forward(request, response);
		} catch (ServletException | IOException e) {
			rootLogger.error(e);
		}
	}

}
