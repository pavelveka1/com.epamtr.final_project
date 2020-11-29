package by.epamtr.airline.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.epamtr.airline.controller.ConstantController;
import by.epamtr.airline.controller.LoggerMessageConstant;
import by.epamtr.airline.controller.command.Command;
import by.epamtr.airline.controller.validation.UserValidation;
import by.epamtr.airline.entity.User;
import by.epamtr.airline.entity.UserInfo;
import by.epamtr.airline.entity.UserRole;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.UserService;
import by.epamtr.airline.service.exception.ServiceException;

public class UpdateUserCommand implements Command {
	private static final Logger logger = Logger.getLogger(UpdateUserCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String idUserParam = request.getParameter(ConstantController.Parameter.ID_USER);
		String changes = request.getParameter(ConstantController.Parameter.CHANGES);
		request.getSession().setAttribute(ConstantController.Attribute.CURRENT_PAGE,
				ConstantController.PathToPage.PATH_TO_UPDATE_USER_PAGE);
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
				User user = null;
				UserInfo userInfo = null;
				try {
					user = userService.getUser(idUser);
					userInfo = userService.getUserInfo(idUser);
				} catch (ServiceException e) {
					logger.error(LoggerMessageConstant.ERROR_GET_USER_DATA, e);
					request.setAttribute(ConstantController.Attribute.ERROR, ConstantController.Attribute.SOMETHING_GOES_WRONG);
				}

				request.getSession().setAttribute(ConstantController.Attribute.SELECTED_USER, user);
				request.getSession().setAttribute(ConstantController.Attribute.USER_INFO, userInfo);
			}
		} else {
			String idUserAttr = (String) request.getSession().getAttribute(ConstantController.Attribute.ID_USER);
			idUser = Integer.parseInt(idUserAttr);
			User user = makeUser(request);
			UserInfo userInfo = makeUserInfo(request);
			boolean dataIsValid = true;
			if (!UserValidation.nameValidation(user.getName())) {
				request.setAttribute(ConstantController.Attribute.NAME_VALID, false);
				dataIsValid = false;
			}
			if (!UserValidation.nameValidation(user.getSurname())) {
				request.setAttribute(ConstantController.Attribute.SURNAME_VALID, false);
				dataIsValid = false;
			}
			if (!UserValidation.nameValidation(user.getPatronimic())) {
				request.setAttribute(ConstantController.Attribute.PATRONIMIC_VALID, false);
				dataIsValid = false;
			}

			if (!UserValidation.emailValidation(user.getEmail())) {
				request.setAttribute(ConstantController.Attribute.EMAIL_VALID, false);
				dataIsValid = false;
			}
			if (!UserValidation.loginValidation(userInfo.getLogin())) {
				request.setAttribute(ConstantController.Attribute.LOGIN_VALID, false);
				dataIsValid = false;
			}
			if (!UserValidation.passwordValidation(userInfo.getPassword())) {
				request.setAttribute(ConstantController.Attribute.PASSWORD_VALID, false);
				dataIsValid = false;
			}
			if (dataIsValid == true) {
				boolean result = false;
				try {
					result = userService.updateUser(user, userInfo);
				} catch (ServiceException e) {
					logger.error(LoggerMessageConstant.ERROR_UPDATE_USER, e);
					request.setAttribute(ConstantController.Attribute.ERROR, ConstantController.Attribute.SOMETHING_GOES_WRONG);
				}
				if (result) {
					logger.info(LoggerMessageConstant.USER_IS_UPDATED);
					request.setAttribute(ConstantController.Attribute.RESULT_ATTR,
							ConstantController.Attribute.SUCCESSFUL_OPERATION);
				} else {
					logger.info(LoggerMessageConstant.USER_IS_NOT_UPDATED);
					request.setAttribute(ConstantController.Attribute.RESULT_ATTR,
							ConstantController.Attribute.FAILED_OPERATION);
				}
			} else {
				logger.info(LoggerMessageConstant.USER_UPDATE_DATA_NOT_VALID);
			}

			try {
				user = userService.getUser(idUser);
				userInfo = userService.getUserInfo(idUser);
			} catch (ServiceException e) {
				logger.error(LoggerMessageConstant.ERROR_GET_USER_DATA, e);
				request.setAttribute(ConstantController.Attribute.ERROR, ConstantController.Attribute.SOMETHING_GOES_WRONG);
			}

			request.getSession().setAttribute(ConstantController.Attribute.SELECTED_USER, user);
			request.getSession().setAttribute(ConstantController.Attribute.USER_INFO, userInfo);
		}
		try {
			request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request, response);
		} catch (ServletException | IOException e) {
			logger.error(LoggerMessageConstant.ERROR_GO_TO_MAIN_PAGE, e);
		}
	}

	private User makeUser(HttpServletRequest request) {
		User user = new User();
		user.setIdUser(
				Integer.parseInt((String) request.getSession().getAttribute(ConstantController.Attribute.ID_USER)));
		user.setName(request.getParameter(ConstantController.Parameter.NAME));
		user.setSurname(request.getParameter(ConstantController.Parameter.SURNAME));
		user.setPatronimic(request.getParameter(ConstantController.Parameter.PATRONIMIC));
		user.setEmail(request.getParameter(ConstantController.Parameter.EMAIL));
		user.setRole(UserRole.valueOf(request.getParameter(ConstantController.Parameter.ROLE)));
		return user;
	}

	private UserInfo makeUserInfo(HttpServletRequest request) {
		UserInfo userInfo = new UserInfo();
		userInfo.setIdUserInfo(
				Integer.parseInt((String) request.getSession().getAttribute(ConstantController.Attribute.ID_USER)));
		userInfo.setLogin(request.getParameter(ConstantController.Parameter.LOGIN));
		userInfo.setPassword(request.getParameter(ConstantController.Parameter.PASSWORD));
		return userInfo;
	}
}
