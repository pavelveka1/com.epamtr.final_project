package by.epamtr.airline.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtr.airline.controller.command.Command;

public class GoToDispatcherPage implements Command {
	private static final String PATH_TO_DISPATCHER_PAGE="/WEB-INF/jsp/dispatcher_page.jsp";
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher(PATH_TO_DISPATCHER_PAGE).forward(request, response);
		} catch (ServletException | IOException e) {
			rootLogger.error(e);
		}
		
	}

}
