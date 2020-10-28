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

public class GetUsersByRoleCommand implements Command {
	private static final String ADMIN_PATH_TO_GET_USERS_BY_ROLE = "/WEB-INF/jsp/administrator_action/users_by_role.jsp";
	private static final String PATH_TO_GET_USERS_BY_ROLE="/WEB-INF/jsp/dispatcher_action/users_by_role.jsp";
	private static final String FORM_PARAM = "form";
	private static final String ROLE_PARAM = "role";
	private static final String USER_BY_ROLE_ATTR="users_by_role";
	private static final String CURRENT_ROLE_ATTR="current_role";
	private static final String PATH_TO_MAIN_PAGE="/WEB-INF/jsp/main_page.jsp";
	private static final String CURRENT_PAGE="current_page";
	private static final String SIGNED_IN_USER_ATTRIBUTE="user";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String role = request.getParameter(ROLE_PARAM);
		String roleCurrentUser=((User)request.getSession().getAttribute(SIGNED_IN_USER_ATTRIBUTE)).getRole().getRole();
		if(roleCurrentUser.equalsIgnoreCase(UserRole.ADMINISTRATOR.getRole())) {
			request.setAttribute(CURRENT_PAGE, ADMIN_PATH_TO_GET_USERS_BY_ROLE);
		}else {
			request.setAttribute(CURRENT_PAGE, PATH_TO_GET_USERS_BY_ROLE);
		}
		
		if (role != null) {
			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			UserService userService = serviceFactory.getUserService();
			UserRole userRole = UserRole.valueOf(request.getParameter(ROLE_PARAM));
			try {
				List<User> users = userService.getUsers(userRole);
				request.setAttribute(USER_BY_ROLE_ATTR, users);
				request.setAttribute(CURRENT_ROLE_ATTR, userRole);
				try {
					request.getRequestDispatcher(PATH_TO_MAIN_PAGE).forward(request, response);
				} catch (ServletException | IOException e) {
					rootLogger.error(e);
				}
			} catch (ServiceException e2) {
				// rootLogger.error(e2);
				e2.printStackTrace();
			}
		} else {
			try {
				request.getRequestDispatcher(PATH_TO_MAIN_PAGE).forward(request, response);
			} catch (ServletException | IOException e) {
				rootLogger.error(e);
			}
		}
	}
}
