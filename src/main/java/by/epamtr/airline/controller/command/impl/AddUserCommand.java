package by.epamtr.airline.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.epamtr.airline.controller.ConstantController;
import by.epamtr.airline.controller.LoggerMessageConstant;
import by.epamtr.airline.controller.command.Command;
import by.epamtr.airline.entity.User;
import by.epamtr.airline.entity.UserInfo;
import by.epamtr.airline.entity.UserRole;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.UserService;
import by.epamtr.airline.service.exception.ServiceException;
import by.epamtr.airline.service.validator.UserValidation;

public class AddUserCommand implements Command {
	private static final Logger LOGGER = Logger.getLogger(AddUserCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();
		request.getSession().setAttribute(ConstantController.Attribute.CURRENT_PAGE,
				ConstantController.PathToPage.PATH_TO_ADD_USER_PAGE);
		String page = request.getParameter(ConstantController.Parameter.PAGE);
		if (page != null) {
			User user = new User();
			user.setName(request.getParameter(ConstantController.Parameter.NAME));
			user.setSurname(request.getParameter(ConstantController.Parameter.SURNAME));
			user.setPatronimic(request.getParameter(ConstantController.Parameter.PATRONIMIC));
			user.setEmail(request.getParameter(ConstantController.Parameter.EMAIL));
			user.setRole(UserRole.valueOf(request.getParameter(ConstantController.Parameter.ROLE)));
			UserInfo userInfo = new UserInfo();
			userInfo.setLogin(request.getParameter(ConstantController.Parameter.LOGIN));
			userInfo.setPassword(request.getParameter(ConstantController.Parameter.PASSWORD));
			boolean result = false;

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
				try {
					result = userService.addUser(user, userInfo);
				} catch (ServiceException e) {
					LOGGER.error(LoggerMessageConstant.ERROR_ADD_USER, e);
					request.setAttribute(ConstantController.Attribute.ERROR, e);
				}
				if (result) {
					LOGGER.info(LoggerMessageConstant.USER_ADDED);
					request.setAttribute(ConstantController.Attribute.RESULT_ATTR,
							ConstantController.Attribute.SUCCESSFUL_OPERATION);
				} else {
					LOGGER.info(LoggerMessageConstant.USER_NOT_ADDED);
					request.setAttribute(ConstantController.Attribute.RESULT_ATTR,
							ConstantController.Attribute.FAILED_OPERATION);
				}
			} else {
				LOGGER.info(LoggerMessageConstant.USER_NOT_VALID);
			}

		} else {
			LOGGER.info(LoggerMessageConstant.GO_TO_ADD_USER_PAGE);
		}
		try {
			request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request, response);
		} catch (ServletException | IOException e) {
			LOGGER.error(LoggerMessageConstant.ERROR_GO_TO_MAIN_PAGE, e);
		}
	}
}
