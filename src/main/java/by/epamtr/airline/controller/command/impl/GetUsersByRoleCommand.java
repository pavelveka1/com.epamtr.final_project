package by.epamtr.airline.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtr.airline.controller.ConstantController;
import by.epamtr.airline.controller.command.Command;
import by.epamtr.airline.entity.User;
import by.epamtr.airline.entity.UserRole;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.UserService;
import by.epamtr.airline.service.exception.ServiceException;

public class GetUsersByRoleCommand implements Command {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String role = request.getParameter(ConstantController.Parameter.ROLE);
		String roleCurrentUser=((User)request.getSession().getAttribute(ConstantController.Attribute.SIGNED_IN_USER)).getRole().getRole();
		if(roleCurrentUser.equalsIgnoreCase(UserRole.ADMINISTRATOR.getRole())) {
			request.setAttribute(ConstantController.Attribute.CURRENT_PAGE, ConstantController.PathToPage.ADMIN_PATH_TO_GET_USERS_BY_ROLE);
		}else {
			request.setAttribute(ConstantController.Attribute.CURRENT_PAGE, ConstantController.PathToPage.PATH_TO_GET_USERS_BY_ROLE);
		}
		
		if (role != null) {
			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			UserService userService = serviceFactory.getUserService();
			UserRole userRole = UserRole.valueOf(request.getParameter(ConstantController.Parameter.ROLE));
			try {
				List<User> users = userService.getUsers(userRole);
				request.setAttribute(ConstantController.Attribute.USER_BY_ROLE, users);
				request.setAttribute(ConstantController.Attribute.CURRENT_ROLE, userRole);
				try {
					request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request, response);
				} catch (ServletException | IOException e) {
					rootLogger.error(e);
				}
			} catch (ServiceException e2) {
				// rootLogger.error(e2);
				e2.printStackTrace();
			}
		} else {
			try {
				request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request, response);
			} catch (ServletException | IOException e) {
				rootLogger.error(e);
			}
		}
	}
}
