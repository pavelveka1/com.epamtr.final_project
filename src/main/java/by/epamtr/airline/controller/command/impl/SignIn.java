package by.epamtr.airline.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.epamtr.airline.controller.command.Command;
import by.epamtr.airline.dao.exception.DAOException;
import by.epamtr.airline.entity.User;
import by.epamtr.airline.entity.UserRole;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.UserService;
import by.epamtr.airline.service.exception.ServiceException;

public class SignIn implements Command {
	private final String CURRENT_PAGE="current_page";
	
	private final String FLIGHTS_BY_STATUS_PAGE="/WEB-INF/jsp/user_action/get_flights_by_status.jsp";

	private final String PATH_TO_CONTROLLER = "/Controller?command=GO_TO_LOGIN_PAGE";
	private final String PATH_TO_ADD_USER_PAGE = "/WEB-INF/jsp/administrator_action/add_user.jsp";
	private static final String GO_TO_MAIN_PAGE_COMMAND = "/Controller?command=GO_TO_MAIN_PAGE";
	private static final String PATH_TO_ADMIN_PAGE="/WEB-INF/jsp/administrator_page.jsp";
	private static final String PATH_TO_UPDATE_USER="/WEB-INF/jsp/administrator_action/update_user.jsp";
	private static final String CURRENT_MENU="menu";
	private static final String ADMIN_MENU="admin_menu";
	private static final String DISPATCHER_MENU="dispatcher_menu";
	private static final String MANAGER_MENU="manager_menu";
	private static final String CREW_MENU="crew_menu";
	private final String SIGNED_IN_USER_ATTRIBUTE = "user";
	private final String PASSWORD_PARAM = "password";
	private final String LOGIN_PARAM = "login";
	private final String SIGN_IN_FAIL_ATTR="sign_in_fail_attr";
	private final String SIGN_IN_FAIL="sign_in_fail";
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)  {
		User user = null;
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();
		String login=request.getParameter(LOGIN_PARAM);
		String password=request.getParameter(PASSWORD_PARAM);
		try {
			user=userService.signIn(login, password);
		} catch (ServiceException e2) {
			//rootLogger.error(e2);
			e2.printStackTrace();
		}
		if(user==null) {
			request.setAttribute(SIGN_IN_FAIL_ATTR, SIGN_IN_FAIL);
			try {
				request.getRequestDispatcher(PATH_TO_CONTROLLER).forward(request, response);
			} catch (ServletException | IOException e) {
				// rootLogger.error(e);
				e.printStackTrace();
			}
		}else {
			request.getSession().setAttribute(SIGNED_IN_USER_ATTRIBUTE, user);
			request.getSession().setAttribute(CURRENT_PAGE, FLIGHTS_BY_STATUS_PAGE);
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
				request.getRequestDispatcher(GO_TO_MAIN_PAGE_COMMAND).forward(request, response);
			} catch (ServletException | IOException e) {
				// rootLogger.error(e);
				e.printStackTrace();
			}
	}

}
