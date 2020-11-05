package by.epamtr.airline.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.epamtr.airline.controller.ConstantController;
import by.epamtr.airline.controller.LoggerMessageConstant;
import by.epamtr.airline.controller.command.Command;
import by.epamtr.airline.dao.exception.DAOException;
import by.epamtr.airline.entity.User;
import by.epamtr.airline.entity.UserRole;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.UserService;
import by.epamtr.airline.service.exception.ServiceException;

public class SignIn implements Command {
	private static final String CURRENT_MENU="menu";
	private static final String ADMIN_MENU="admin_menu";
	private static final String DISPATCHER_MENU="dispatcher_menu";
	private static final String MANAGER_MENU="manager_menu";
	private static final String CREW_MENU="crew_menu";
	private static final Logger LOGGER = Logger.getLogger(SignIn.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)  {
		User user = null;
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();
		String login=request.getParameter(ConstantController.Parameter.LOGIN);
		String password=request.getParameter(ConstantController.Parameter.PASSWORD);
		try {
			user=userService.signIn(login, password);
		} catch (ServiceException e2) {
			LOGGER.error(LoggerMessageConstant.ERROR_SIGN_IN, e2);
		}
		if(user==null) {
			request.setAttribute(ConstantController.Attribute.SIGN_IN_FAIL_ATTR, ConstantController.Attribute.SIGN_IN_FAIL);
			try {
				request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_CONTROLLER).forward(request, response);
			} catch (ServletException | IOException e) {
				LOGGER.error(LoggerMessageConstant.ERROR_GO_TO_CONTROLLER, e);
			}
		}else {
			request.getSession().setAttribute(ConstantController.Attribute.SIGNED_IN_USER, user);
			request.getSession().setAttribute(ConstantController.Attribute.CURRENT_PAGE, ConstantController.PathToPage.FLIGHTS_BY_STATUS_PAGE);
			sendCommandToController(user.getRole(), request, response);
		}
		

	}
	
	private void sendCommandToController(UserRole userRole, HttpServletRequest request, HttpServletResponse response) {
			switch (userRole) {
			case MANAGER:
				request.getSession().setAttribute(CURRENT_MENU, MANAGER_MENU);
				break;
			case DISPATCHER:
				request.getSession().setAttribute(CURRENT_MENU, DISPATCHER_MENU);
				break;
			case ADMINISTRATOR:
				request.getSession().setAttribute(CURRENT_MENU, ADMIN_MENU);
				break;
			case PILOT:
			case ENGINEER:
			case ATTENDANT:
				request.getSession().setAttribute(CURRENT_MENU, CREW_MENU);
				break;
			}
			try {
				request.getRequestDispatcher(ConstantController.PathToPage.GO_TO_MAIN_PAGE_COMMAND).forward(request, response);
			} catch (ServletException | IOException e) {
				LOGGER.error(LoggerMessageConstant.ERROR_GO_TO_MAIN_PAGE, e);
			}
	}

}
