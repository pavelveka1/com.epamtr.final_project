package by.epamtr.airline.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epamtr.airline.controller.command.Command;
import by.epamtr.airline.controller.exception.ControllerException;
import by.epamtr.airline.dao.connection_pool.ConnectionPool;
import by.epamtr.airline.dao.connection_pool.impl.ConnectionPoolImpl;
import by.epamtr.airline.dao.exception.DAOException;

/**
 * Servlet implementation class Controller
 */
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String COMMAND = "command";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MainController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Logger rootLogger = LogManager.getRootLogger();
		rootLogger.info("Root Logger: " + request.getParameter(COMMAND));
		String commandName = request.getParameter(COMMAND);
		if (commandName == null) {
			request.getRequestDispatcher("/WEB-INF/jsp/login_page.jsp").forward(request, response);
		} else {
			CommandProvider commandProvider = CommandProvider.getInstance();
			Command command = commandProvider.getCommand(commandName.toUpperCase());
			command.execute(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
