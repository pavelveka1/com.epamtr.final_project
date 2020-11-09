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

public class DeliteUserCommand implements Command {
	private static final Logger LOGGER = Logger.getLogger(DeliteUserCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		List<User> users = null;
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();
		int idUser = Integer.parseInt(request.getParameter(ConstantController.Parameter.ID_USER));
		String roleCurrentUser = ((User) request.getSession().getAttribute(ConstantController.Attribute.SIGNED_IN_USER))
				.getRole().getRole();
		if (roleCurrentUser.equalsIgnoreCase(UserRole.ADMINISTRATOR.getRole())) {
			request.setAttribute(ConstantController.Attribute.CURRENT_PAGE,
					ConstantController.PathToPage.ADMIN_PATH_TO_GET_USERS_BY_ROLE);
		} else {
			request.setAttribute(ConstantController.Attribute.CURRENT_PAGE,
					ConstantController.PathToPage.PATH_TO_GET_USERS_BY_ROLE);
		}
		if (idUser > 0) {

			boolean result = false;
			try {
				result = userService.deliteUser(idUser);
			} catch (ServiceException e) {
				LOGGER.error(LoggerMessageConstant.ERROR_DELETE_USER, e);
				request.setAttribute(ConstantController.Attribute.ERROR, e);
			}
			if (result) {
				UserRole userRole = (UserRole) request.getSession()
						.getAttribute(ConstantController.Attribute.CURRENT_ROLE);
				try {
					users = userService.getUsers(userRole);
				} catch (ServiceException e2) {
					LOGGER.error(LoggerMessageConstant.ERROR_GET_USERS_BY_USER_ROLE, e2);
					request.setAttribute(ConstantController.Attribute.ERROR, e2);
				}
				request.setAttribute(ConstantController.Attribute.USER_BY_ROLE, users);
				LOGGER.info(LoggerMessageConstant.USER_IS_DELETED);
				request.setAttribute(ConstantController.Attribute.DELETED_USER,
						ConstantController.Attribute.SUCCESSFUL_OPERATION);
			} else {
				LOGGER.info(LoggerMessageConstant.USER_IS_NOT_DELETED);
				request.setAttribute(ConstantController.Attribute.DELETED_USER,
						ConstantController.Attribute.FAILED_OPERATION);
			}

		}
		try {
			request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request, response);
		} catch (ServletException | IOException e) {
			LOGGER.error(LoggerMessageConstant.ERROR_GO_TO_MAIN_PAGE, e);
		}
	}
}
