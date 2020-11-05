package by.epamtr.airline.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.epamtr.airline.controller.ConstantController;
import by.epamtr.airline.controller.LoggerMessageConstant;
import by.epamtr.airline.controller.command.Command;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.UserService;
import by.epamtr.airline.service.exception.ServiceException;

public class DeliteUserCommand implements Command {
	private static final Logger LOGGER = Logger.getLogger(DeliteUserCommand.class);
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();
		int idUser = Integer.parseInt(request.getParameter(ConstantController.Parameter.ID_USER));
		request.setAttribute(ConstantController.Attribute.CURRENT_PAGE, ConstantController.PathToPage.PATH_TO_GET_USERS_BY_ROLE);
		if (idUser>0) {
			try {
				boolean result = userService.deliteUser(idUser);
				if(result) {
					LOGGER.info(LoggerMessageConstant.USER_IS_DELETED);
					request.setAttribute(ConstantController.Attribute.DELETED_USER, ConstantController.Attribute.SUCCESSFUL_OPERATION);
				}else {
					LOGGER.info(LoggerMessageConstant.USER_IS_NOT_DELETED);
					request.setAttribute(ConstantController.Attribute.DELETED_USER, ConstantController.Attribute.FAILED_OPERATION);
				}
			} catch (ServiceException e2) {
				LOGGER.error(LoggerMessageConstant.ERROR_GO_TO_MAIN_PAGE, e2);
			}

			try {
				request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request, response);
			} catch (ServletException | IOException e) {
				LOGGER.error(LoggerMessageConstant.ERROR_GO_TO_MAIN_PAGE, e);
			}

		} else {
			try {
				request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request, response);
			} catch (ServletException | IOException e) {
				LOGGER.error(LoggerMessageConstant.ERROR_GO_TO_MAIN_PAGE, e);
			}
		}

	}
}
