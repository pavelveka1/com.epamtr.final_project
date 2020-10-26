package by.epamtr.airline.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.epamtr.airline.controller.command.Command;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.UserService;
import by.epamtr.airline.service.exception.ServiceException;

public class DeliteUserCommand implements Command {
	private static final String PATH_TO_GET_USERS_BY_ROLE = "/WEB-INF/jsp/administrator_action/users_by_role.jsp";
	private static final String PATH_TO_DELITE_USER_PAGE = "/WEB-INF/jsp/administrator_action/delite_user.jsp";
	private static final String LOGIN_PARAM = "login";
	private static final String DELETED_USER_ATTR = "deleted_user";
	private static final String RESULT_OK = "success";
	private static final String RESULT_FAIL = "fail";
	private static final String ID_USER_PARAM="id_user";
	private static final String PATH_TO_ADMIN_PAGE="/WEB-INF/jsp/administrator_page.jsp";
	private static final String CURRENT_PAGE="current_page";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();
		int idUser = Integer.parseInt(request.getParameter(ID_USER_PARAM));
		String login = request.getParameter(LOGIN_PARAM);
		request.setAttribute(CURRENT_PAGE, PATH_TO_GET_USERS_BY_ROLE);
		if (idUser>0) {
			try {
				boolean result = userService.deliteUser(idUser);
				if(result) {
					request.setAttribute(DELETED_USER_ATTR, RESULT_OK);
				}else {
					request.setAttribute(DELETED_USER_ATTR, RESULT_FAIL);
				}
				
			} catch (ServiceException e2) {
				// rootLogger.error(e2);
				e2.printStackTrace();
			}

			try {
				request.getRequestDispatcher(PATH_TO_ADMIN_PAGE).forward(request, response);
			} catch (ServletException | IOException e) {
				rootLogger.error(e);
			}

		} else {
			try {
				request.getRequestDispatcher(PATH_TO_ADMIN_PAGE).forward(request, response);
			} catch (ServletException | IOException e) {
				rootLogger.error(e);
			}
		}

	}
}
