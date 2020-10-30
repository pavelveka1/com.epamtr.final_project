package by.epamtr.airline.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.epamtr.airline.controller.command.Command;
import by.epamtr.airline.entity.User;
import by.epamtr.airline.entity.UserInfo;
import by.epamtr.airline.entity.UserRole;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.UserService;
import by.epamtr.airline.service.exception.ServiceException;

public class AddUserCommand implements Command {
	private static final String PATH_TO_ADD_USER_PAGE="/WEB-INF/jsp/administrator_action/add_user.jsp";
	private static final String PATH_TO_MAIN_PAGE="/WEB-INF/jsp/main_page.jsp";
	private static final String CURRENT_PAGE="current_page";
	private final String EMAIL_PARAM = "e_mail";
	private final String NAME_PARAM = "name";
	private final String SURNAME_PARAM = "surname";
	private final String PATRONIMIC_PARAM = "patronimic";
	private final String ROLE_PARAM = "role";
	private final String PASSWORD_PARAM = "password";
	private final String LOGIN_PARAM = "login";
	private final String RESULT_ATTR = "result_attr";
	private final String SUCCESSFUL_OPERATION = "success";
	private final String FAILED_OPERATION = "fail";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)  {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();
	request.getSession().setAttribute(CURRENT_PAGE, PATH_TO_ADD_USER_PAGE);
		String page=request.getParameter("page");
		if(page!=null) {
			User user=new User();
			user.setName(request.getParameter(NAME_PARAM));
			user.setSurname(request.getParameter(SURNAME_PARAM));
			user.setPatronimic(request.getParameter(PATRONIMIC_PARAM));
			user.setEmail(request.getParameter(EMAIL_PARAM));
			user.setRole(UserRole.valueOf(request.getParameter(ROLE_PARAM)));
			UserInfo userInfo=new UserInfo();
			userInfo.setLogin(request.getParameter(LOGIN_PARAM));
			userInfo.setPassword(request.getParameter(PASSWORD_PARAM));
			boolean result=false;
			try {
				result=userService.addUser(user, userInfo);
				if(result) {
					request.setAttribute(RESULT_ATTR, SUCCESSFUL_OPERATION);
				}else {
					request.setAttribute(RESULT_ATTR, FAILED_OPERATION);
				}
				request.getRequestDispatcher(PATH_TO_MAIN_PAGE).forward(request, response);
			} catch (ServiceException | ServletException | IOException e2) {
			//	rootLogger.error(e2);
				e2.printStackTrace();
			}
		}else {
			try {
				request.getRequestDispatcher(PATH_TO_MAIN_PAGE).forward(request, response);
			} catch (ServletException | IOException e) {
				rootLogger.error(e);
			}
		}
	}
}
