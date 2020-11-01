package by.epamtr.airline.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtr.airline.controller.ConstantController;
import by.epamtr.airline.controller.command.Command;

public class GoToMainPage implements Command {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)  {
		try {
			request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request, response);
		} catch (ServletException | IOException e) {
			rootLogger.error(e);
		}
		
	}

}
