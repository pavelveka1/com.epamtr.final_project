package by.epamtr.airline.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtr.airline.controller.command.Command;
import by.epamtr.airline.entity.User;
import by.epamtr.airline.entity.UserRole;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.UserService;
import by.epamtr.airline.service.exception.ServiceException;

public class GetUsersByRoleCommand implements Command{
	private static final String PATH_TO_GET_USERS_BY_ROLE="/WEB-INF/jsp/administrator_action/users_by_role.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String form=request.getParameter("form");
		if(form!=null) {
			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			UserService userService = serviceFactory.getUserService();
			UserRole role=UserRole.valueOf(request.getParameter("role"));
			try {
				List<User> users=userService.getUsers(role);
				request.getSession().setAttribute("users_by_role", users);
				try {
					request.getRequestDispatcher(PATH_TO_GET_USERS_BY_ROLE).forward(request, response);
				} catch (ServletException | IOException e) {
					rootLogger.error(e);
				}
			} catch (ServiceException e2) {
			//	rootLogger.error(e2);
				e2.printStackTrace();
			}
		}else {
			try {
				request.getRequestDispatcher(PATH_TO_GET_USERS_BY_ROLE).forward(request, response);
			} catch (ServletException | IOException e) {
				rootLogger.error(e);
			}
		}
		
		
	}

}
