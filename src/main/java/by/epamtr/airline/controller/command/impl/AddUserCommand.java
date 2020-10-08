package by.epamtr.airline.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.epamtr.airline.controller.command.Command;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.UserService;
import by.epamtr.airline.service.exception.ServiceException;

public class AddUserCommand implements Command {
	private static final String PATH_TO_ADD_USER_PAGE="/WEB-INF/jsp/administrator_action/add_user.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)  {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();
		String page=request.getParameter("page");
		if(page!=null) {
			try {
				userService.addUser(request, response);
			} catch (ServiceException e2) {
			//	rootLogger.error(e2);
				e2.printStackTrace();
			}
		}else {
			try {
				request.getRequestDispatcher(PATH_TO_ADD_USER_PAGE).forward(request, response);
			} catch (ServletException | IOException e) {
				rootLogger.error(e);
			}
		}
		
		
	}

}
