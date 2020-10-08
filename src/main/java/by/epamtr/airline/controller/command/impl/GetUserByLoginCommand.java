package by.epamtr.airline.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.epamtr.airline.controller.command.Command;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.UserService;
import by.epamtr.airline.service.exception.ServiceException;

public class GetUserByLoginCommand implements Command {
	private static final String PATH_TO_ADD_USER_PAGE = "/WEB-INF/jsp/administrator_action/update_user.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();

		try {
			userService.findUser(request, response);
		} catch (ServiceException e) {
			// rootLogger.error(e2);
			e.printStackTrace();
		}

	}

}
