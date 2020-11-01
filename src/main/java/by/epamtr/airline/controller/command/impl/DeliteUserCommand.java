package by.epamtr.airline.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtr.airline.controller.ConstantController;
import by.epamtr.airline.controller.command.Command;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.UserService;
import by.epamtr.airline.service.exception.ServiceException;

public class DeliteUserCommand implements Command {
	
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
					request.setAttribute(ConstantController.Attribute.DELETED_USER, ConstantController.Attribute.SUCCESSFUL_OPERATION);
				}else {
					request.setAttribute(ConstantController.Attribute.DELETED_USER, ConstantController.Attribute.FAILED_OPERATION);
				}
			} catch (ServiceException e2) {
				// rootLogger.error(e2);
				e2.printStackTrace();
			}

			try {
				request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request, response);
			} catch (ServletException | IOException e) {
				rootLogger.error(e);
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
