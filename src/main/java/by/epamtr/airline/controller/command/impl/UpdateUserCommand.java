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

public class UpdateUserCommand implements Command {
	private static final String PATH_TO_UPDATE_USER_PAGE = "/WEB-INF/jsp/administrator_action/update_user.jsp";
	private static final String USER_ATTRIBUTE = "selected_user";
	private static final String USER_INFO_ATTRIBUTE = "user_info";
	private static final String PATH_TO_MAIN_PAGE = "/WEB-INF/jsp/main_page.jsp";
	private static final String CURRENT_PAGE = "current_page";
	private static final String ID_USER_PARAM = "id_user";
	private static final String CHANGES_PARAM = "changes";
	private static final String ID_USER_ATTR = "id_user_attr";
	private final String RESULT_ATTR = "result_attr";
	private final String SUCCESSFUL_OPERATION = "success";
	private final String FAILED_OPERATION = "fail";
	private final String EMAIL_PARAM = "email";
	private final String NAME_PARAM = "name";
	private final String SURNAME_PARAM = "surname";
	private final String PATRONIMIC_PARAM = "patronimic";
	private final String ROLE_PARAM = "role";
	private final String PASSWORD_PARAM = "password";
	private final String LOGIN_PARAM = "login";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String idUserParam = request.getParameter(ID_USER_PARAM);
		String changes = request.getParameter(CHANGES_PARAM);
		request.setAttribute(CURRENT_PAGE, PATH_TO_UPDATE_USER_PAGE);
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();
		int idUser = 0;

		if (changes == null) {
			if (idUserParam == null) {
				idUserParam = (String) request.getSession().getAttribute(ID_USER_ATTR);
				idUser = Integer.parseInt(idUserParam);
			} else {
				idUser = Integer.parseInt(request.getParameter(ID_USER_PARAM));
			}
			if (idUser > 0) {
				request.getSession().setAttribute(ID_USER_ATTR, String.valueOf(idUser));
				try {
					User user = userService.getUser(idUser);
					UserInfo userInfo = userService.getUserInfo(idUser);
					request.getSession().setAttribute(USER_ATTRIBUTE, user);
					request.getSession().setAttribute(USER_INFO_ATTRIBUTE, userInfo);
					request.getRequestDispatcher(PATH_TO_MAIN_PAGE).forward(request, response);
				} catch (ServletException | ServiceException | IOException e) {
					// rootLogger.error(e);
					e.printStackTrace();
				}
			}
		} else {
			try {
				String idUserAttr = (String) request.getSession().getAttribute(ID_USER_ATTR);
				idUser = Integer.parseInt(idUserAttr);
				User user=makeUser(request);
				UserInfo userInfo=makeUserInfo(request);
				boolean result = userService.updateUser(user, userInfo);
				if (result) {
					request.setAttribute(RESULT_ATTR, SUCCESSFUL_OPERATION);
				} else {
					request.setAttribute(RESULT_ATTR, FAILED_OPERATION);
				}
				user = userService.getUser(idUser);
				userInfo = userService.getUserInfo(idUser);
				request.getSession().setAttribute(USER_ATTRIBUTE, user);
				request.getSession().setAttribute(USER_INFO_ATTRIBUTE, userInfo);
				request.getRequestDispatcher(PATH_TO_MAIN_PAGE).forward(request, response);
			} catch (ServletException | IOException | ServiceException e) {
				// rootLogger.error(e);
				e.printStackTrace();
			}
		}
	}
	
	private User makeUser(HttpServletRequest request) {
		User user=new User();
		user.setIdUser(Integer.parseInt((String)request.getSession().getAttribute(ID_USER_ATTR)));
		user.setName(request.getParameter(NAME_PARAM));
		user.setSurname(request.getParameter(SURNAME_PARAM));
		user.setPatronimic(request.getParameter(PATRONIMIC_PARAM));
		user.setEmail(request.getParameter(EMAIL_PARAM));
		user.setRole(UserRole.valueOf(request.getParameter(ROLE_PARAM)));
		return user;
	}
	
	private UserInfo makeUserInfo(HttpServletRequest request) {
		UserInfo userInfo=new UserInfo();
		userInfo.setIdUserInfo(Integer.parseInt((String)request.getSession().getAttribute(ID_USER_ATTR)));
		userInfo.setLogin(request.getParameter(LOGIN_PARAM));
		userInfo.setPassword(request.getParameter(PASSWORD_PARAM));
		return userInfo;
	}
}
