package by.epamtr.airline.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LocaleController
 */
public class LocaleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String LOCAL = "local";
	private static final String JSP = "jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LocaleController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		request.getSession(true).setAttribute(LOCAL, request.getParameter(LOCAL));
		request.getRequestDispatcher(request.getParameter(JSP)).forward(request, response);

	}

}
