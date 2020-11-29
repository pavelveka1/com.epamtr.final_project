package by.epamtr.airline.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.epamtr.airline.controller.ConstantController;
import by.epamtr.airline.controller.LoggerMessageConstant;
import by.epamtr.airline.controller.command.Command;
import by.epamtr.airline.entity.User;
import by.epamtr.airline.entity.UserRole;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.UserService;
import by.epamtr.airline.service.exception.ServiceException;

public class GetUsersByRoleCommand implements Command {
	private static final Logger logger = Logger.getLogger(GetUsersByFlightIdCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String role = request.getParameter(ConstantController.Parameter.ROLE);
		String roleCurrentUser = ((User) request.getSession().getAttribute(ConstantController.Attribute.SIGNED_IN_USER))
				.getRole().getRole();
		if (roleCurrentUser.equalsIgnoreCase(UserRole.ADMINISTRATOR.getRole())) {
			request.getSession().setAttribute(ConstantController.Attribute.CURRENT_PAGE,
					ConstantController.PathToPage.ADMIN_PATH_TO_GET_USERS_BY_ROLE);
		} else {
			request.getSession().setAttribute(ConstantController.Attribute.CURRENT_PAGE,
					ConstantController.PathToPage.PATH_TO_GET_USERS_BY_ROLE);
		}

		if (role != null) {
			logger.info(LoggerMessageConstant.GO_TO_PAGE_USERS_BY_ROLE);
			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			UserService userService = serviceFactory.getUserService();
			UserRole userRole = UserRole.valueOf(request.getParameter(ConstantController.Parameter.ROLE));
			try {
				List<User> users = userService.getUsers(userRole);
				request.getSession().setAttribute(ConstantController.Attribute.USER_BY_ROLE, users);
				request.getSession().setAttribute(ConstantController.Attribute.CURRENT_ROLE, userRole);
			} catch (ServiceException e2) {
				logger.error(LoggerMessageConstant.ERROR_GET_USERS_BY_ROLE, e2);
				request.setAttribute(ConstantController.Attribute.ERROR, ConstantController.Attribute.SOMETHING_GOES_WRONG);
			}
		} else {
			logger.info(LoggerMessageConstant.GO_TO_PAGE_CHOOSE_USERS_ROLE);
		}
		try {
			request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request, response);
		} catch (ServletException | IOException e) {
			logger.error(LoggerMessageConstant.ERROR_GO_TO_MAIN_PAGE, e);
		}
	}
}
