package by.epamtr.airline.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtr.airline.controller.command.Command;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.UserService;
import by.epamtr.airline.service.exception.ServiceException;

public class UpdateUserCommand implements Command {
	private static final String PATH_TO_UPDATE_USER_PAGE="/WEB-INF/jsp/administrator_action/update_user.jsp";
	private static final String USER_ATTRIBUTE = "user";
	private static final String USER_INFO_ATTRIBUTE = "user_info";
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)  {
		if(request.getSession().getAttribute("user")==null) {
			try {
				request.getRequestDispatcher(PATH_TO_UPDATE_USER_PAGE).forward(request, response);
			} catch (ServletException | IOException e) {
				// rootLogger.error(e);
				e.printStackTrace();
			}
		}else {
			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			UserService userService = serviceFactory.getUserService();

				try {
					userService.updateUser(request, response);
					request.getSession().removeAttribute(USER_ATTRIBUTE);
					request.getSession().removeAttribute(USER_INFO_ATTRIBUTE);
					request.getRequestDispatcher(PATH_TO_UPDATE_USER_PAGE).forward(request, response);
				} catch (ServletException | IOException | ServiceException e) {
					// rootLogger.error(e);
					e.printStackTrace();
				}
		
		}	
	}
}
