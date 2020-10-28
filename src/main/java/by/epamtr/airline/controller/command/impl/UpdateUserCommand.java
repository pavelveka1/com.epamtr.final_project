package by.epamtr.airline.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtr.airline.controller.command.Command;
import by.epamtr.airline.entity.User;
import by.epamtr.airline.entity.UserInfo;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.UserService;
import by.epamtr.airline.service.exception.ServiceException;

public class UpdateUserCommand implements Command {
	private static final String PATH_TO_UPDATE_USER_PAGE="/WEB-INF/jsp/administrator_action/update_user.jsp";
	private static final String USER_ATTRIBUTE = "selected_user";
	private static final String USER_INFO_ATTRIBUTE = "user_info";
	private static final String PATH_TO_MAIN_PAGE="/WEB-INF/jsp/main_page.jsp";
	private static final String CURRENT_PAGE="current_page";
	private static final String ID_USER_PARAM="id_user";
	private static final String CHANGES_PARAM="changes";
	private static final String ID_USER_ATTR="id_user_attr";
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)  {
		String idUserParam=request.getParameter(ID_USER_PARAM);
		String changes=request.getParameter(CHANGES_PARAM);
		request.setAttribute(CURRENT_PAGE, PATH_TO_UPDATE_USER_PAGE);
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();
		int idUser=0;
		
		if(changes==null) {
			if(idUserParam==null) {
				idUserParam=(String)request.getSession().getAttribute(ID_USER_ATTR);
				idUser=Integer.parseInt(idUserParam);
			}else {
				 idUser=Integer.parseInt(request.getParameter(ID_USER_PARAM));
			}
			if(idUser>0) {
				request.getSession().setAttribute(ID_USER_ATTR, String.valueOf(idUser));
				try {
						User user=userService.getUser(idUser);
						UserInfo userInfo=userService.getUserInfo(idUser);
						request.getSession().setAttribute(USER_ATTRIBUTE, user);
						request.getSession().setAttribute(USER_INFO_ATTRIBUTE, userInfo);
					request.getRequestDispatcher(PATH_TO_MAIN_PAGE).forward(request, response);
				} catch ( ServletException | ServiceException | IOException e) {
					// rootLogger.error(e);
					e.printStackTrace();
				}
			}
		}else {
				try {
					String idUserAttr=(String)request.getSession().getAttribute(ID_USER_ATTR);
					idUser=Integer.parseInt(idUserAttr);
					userService.updateUser(request, response);
					User user=userService.getUser(idUser);
					UserInfo userInfo=userService.getUserInfo(idUser);
					request.getSession().setAttribute(USER_ATTRIBUTE, user);
					request.getSession().setAttribute(USER_INFO_ATTRIBUTE, userInfo);
					request.getRequestDispatcher(PATH_TO_MAIN_PAGE).forward(request, response);
				} catch (ServletException | IOException | ServiceException e) {
					// rootLogger.error(e);
					e.printStackTrace();
				}
		
		}	
	}
}
