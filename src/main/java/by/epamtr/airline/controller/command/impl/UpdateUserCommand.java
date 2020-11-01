package by.epamtr.airline.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtr.airline.controller.ConstantController;
import by.epamtr.airline.controller.command.Command;
import by.epamtr.airline.entity.User;
import by.epamtr.airline.entity.UserInfo;
import by.epamtr.airline.entity.UserRole;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.UserService;
import by.epamtr.airline.service.exception.ServiceException;

public class UpdateUserCommand implements Command {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String idUserParam = request.getParameter(ConstantController.Parameter.ID_USER);
		String changes = request.getParameter(ConstantController.Parameter.CHANGES);
		request.setAttribute(ConstantController.Attribute.CURRENT_PAGE, ConstantController.PathToPage.PATH_TO_UPDATE_USER_PAGE);
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();
		int idUser = 0;

		if (changes == null) {
			if (idUserParam == null) {
				idUserParam = (String) request.getSession().getAttribute(ConstantController.Attribute.ID_USER);
				idUser = Integer.parseInt(idUserParam);
			} else {
				idUser = Integer.parseInt(request.getParameter(ConstantController.Parameter.ID_USER));
			}
			if (idUser > 0) {
				request.getSession().setAttribute(ConstantController.Attribute.ID_USER, String.valueOf(idUser));
				try {
					User user = userService.getUser(idUser);
					UserInfo userInfo = userService.getUserInfo(idUser);
					request.getSession().setAttribute(ConstantController.Attribute.SELECTED_USER, user);
					request.getSession().setAttribute(ConstantController.Attribute.USER_INFO, userInfo);
					request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request, response);
				} catch (ServletException | ServiceException | IOException e) {
					// rootLogger.error(e);
					e.printStackTrace();
				}
			}
		} else {
			try {
				String idUserAttr = (String) request.getSession().getAttribute(ConstantController.Attribute.ID_USER);
				idUser = Integer.parseInt(idUserAttr);
				User user=makeUser(request);
				UserInfo userInfo=makeUserInfo(request);
				boolean result = userService.updateUser(user, userInfo);
				if (result) {
					request.setAttribute(ConstantController.Attribute.RESULT_ATTR, ConstantController.Attribute.SUCCESSFUL_OPERATION);
				} else {
					request.setAttribute(ConstantController.Attribute.RESULT_ATTR, ConstantController.Attribute.FAILED_OPERATION);
				}
				user = userService.getUser(idUser);
				userInfo = userService.getUserInfo(idUser);
				request.getSession().setAttribute(ConstantController.Attribute.SELECTED_USER, user);
				request.getSession().setAttribute(ConstantController.Attribute.USER_INFO, userInfo);
				request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request, response);
			} catch (ServletException | IOException | ServiceException e) {
				// rootLogger.error(e);
				e.printStackTrace();
			}
		}
	}
	
	private User makeUser(HttpServletRequest request) {
		User user=new User();
		user.setIdUser(Integer.parseInt((String)request.getSession().getAttribute(ConstantController.Attribute.ID_USER)));
		user.setName(request.getParameter(ConstantController.Parameter.NAME));
		user.setSurname(request.getParameter(ConstantController.Parameter.SURNAME));
		user.setPatronimic(request.getParameter(ConstantController.Parameter.PATRONIMIC));
		user.setEmail(request.getParameter(ConstantController.Parameter.EMAIL));
		user.setRole(UserRole.valueOf(request.getParameter(ConstantController.Parameter.ROLE)));
		return user;
	}
	
	private UserInfo makeUserInfo(HttpServletRequest request) {
		UserInfo userInfo=new UserInfo();
		userInfo.setIdUserInfo(Integer.parseInt((String)request.getSession().getAttribute(ConstantController.Attribute.ID_USER)));
		userInfo.setLogin(request.getParameter(ConstantController.Parameter.LOGIN));
		userInfo.setPassword(request.getParameter(ConstantController.Parameter.PASSWORD));
		return userInfo;
	}
}
