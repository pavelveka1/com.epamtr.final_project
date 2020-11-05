package by.epamtr.airline.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.epamtr.airline.controller.ConstantController;
import by.epamtr.airline.controller.LoggerMessageConstant;
import by.epamtr.airline.controller.command.Command;

public class SignOut implements Command {
	private static final Logger LOGGER = Logger.getLogger(SignOut.class);
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		try {
			request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_CONTROLLER).forward(request, response);
		} catch (ServletException | IOException e) {
			LOGGER.error(LoggerMessageConstant.ERROR_GO_TO_MAIN_PAGE, e);
		}
	}

}
