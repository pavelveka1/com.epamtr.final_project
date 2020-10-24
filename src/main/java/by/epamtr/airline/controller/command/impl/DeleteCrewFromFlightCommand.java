package by.epamtr.airline.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtr.airline.controller.command.Command;
import by.epamtr.airline.entity.Crew;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.UserService;
import by.epamtr.airline.service.exception.ServiceException;

public class DeleteCrewFromFlightCommand implements Command{
	private static final String PATH_TO_GET_CREW_BY_FLIGHT = "/WEB-INF/jsp/administrator_action/crew_by_flight.jsp";
	private static final String FLIGHT_ID_PARAM="flight_id";
	private static final String USER_ID_PARAM="radio_id_crew";
	private static final String TEAM_BY_FLIGHT_ATTR="flight_team";
	private static final String PATH_TO_ADMIN_PAGE="/WEB-INF/jsp/administrator_page.jsp";
	private static final String CURRENT_PAGE="current_page";
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();
		int flightId=Integer.parseInt(request.getParameter(FLIGHT_ID_PARAM));
		String userId=request.getParameter(USER_ID_PARAM);
		request.setAttribute(CURRENT_PAGE, PATH_TO_GET_CREW_BY_FLIGHT);
		if(userId!=null) {
			try {
				userService.deliteCrewFromFlight(flightId, Integer.parseInt(userId));
				List<Crew> team=userService.getUsers(flightId);
				request.setAttribute(TEAM_BY_FLIGHT_ATTR, team);
				request.getRequestDispatcher(PATH_TO_ADMIN_PAGE).forward(request, response);
			} catch (NumberFormatException | ServiceException | ServletException | IOException e) {
				// rootLogger.error(e);
				e.printStackTrace();
			}
		}
		
	}

}
