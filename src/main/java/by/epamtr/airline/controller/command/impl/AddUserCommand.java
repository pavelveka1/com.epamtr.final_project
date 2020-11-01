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

public class AddUserCommand implements Command {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)  {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();
	request.getSession().setAttribute(ConstantController.Attribute.CURRENT_PAGE, ConstantController.PathToPage.PATH_TO_ADD_USER_PAGE);
		String page=request.getParameter(ConstantController.Parameter.PAGE);
		if(page!=null) {
			User user=new User();
			user.setName(request.getParameter(ConstantController.Parameter.NAME));
			user.setSurname(request.getParameter(ConstantController.Parameter.SURNAME));
			user.setPatronimic(request.getParameter(ConstantController.Parameter.PATRONIMIC));
			user.setEmail(request.getParameter(ConstantController.Parameter.EMAIL));
			user.setRole(UserRole.valueOf(request.getParameter(ConstantController.Parameter.ROLE)));
			UserInfo userInfo=new UserInfo();
			userInfo.setLogin(request.getParameter(ConstantController.Parameter.LOGIN));
			userInfo.setPassword(request.getParameter(ConstantController.Parameter.PASSWORD));
			boolean result=false;
			try {
				result=userService.addUser(user, userInfo);
				if (result) {
					request.setAttribute(ConstantController.Attribute.RESULT_ATTR, ConstantController.Attribute.SUCCESSFUL_OPERATION);
				} else {
					request.setAttribute(ConstantController.Attribute.RESULT_ATTR, ConstantController.Attribute.FAILED_OPERATION);
				}
				request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request, response);
			} catch (ServiceException | ServletException | IOException e2) {
			//	rootLogger.error(e2);
				e2.printStackTrace();
			}
		}else {
			try {
				request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request, response);
			} catch (ServletException | IOException e) {
				rootLogger.error(e);
			}
		}
	}
}
