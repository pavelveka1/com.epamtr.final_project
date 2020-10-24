package by.epamtr.airline.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtr.airline.controller.command.Command;
import by.epamtr.airline.entity.Crew;
import by.epamtr.airline.entity.CrewPosition;
import by.epamtr.airline.entity.Flight;
import by.epamtr.airline.entity.User;
import by.epamtr.airline.service.FlightService;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.UserService;
import by.epamtr.airline.service.exception.ServiceException;

public class AddCrewToFlightCommand implements Command {
	private static final String PATH_TO_ADD_CREW_TO_FLIGHT = "/WEB-INF/jsp/dispatcher_action/add_crew_item_to_flight.jsp";
	private static final String PATH_TO_CREW_BY_FLIGHT = "/WEB-INF/jsp/administrator_action/crew_by_flight.jsp";
	private static final String SELECTED_POSITION_PARAM = "position";
	private static final String SELECTED_USER_PARAM = "selected_user";
	private static final String FLIGHT_ID_PARAM = "flight_id";
	private static final String FREE_USERS_BY_POSITION_ATTR = "free_users_by_position";
	private static final String FREE_POSITIONS_BY_FLIGHT_ATTR="free_positions";
	private static final String SELECTED_FLIGHT_ATTR="selected_flight";
	private static final String TEAM_BY_FLIGHT_ATTR="flight_team";
	private static final String FLIGHT_ID_ATTR = "flight_id";
	private static final String ID_CREW_POSITION_ATTR="id_crew_position";
	private static final String SELECTED_POSITION_ATTR="selected_position";
	private static final String PATH_TO_ADMIN_PAGE="/WEB-INF/jsp/administrator_page.jsp";
	private static final String CURRENT_PAGE="current_page";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		FlightService flightService = serviceFactory.getFlightService();
		String selectedUserId = request.getParameter(SELECTED_USER_PARAM);
		
		String selectedPosition=(String)request.getSession().getAttribute(SELECTED_POSITION_ATTR);
		if(selectedPosition==null) {
			selectedPosition = request.getParameter(SELECTED_POSITION_PARAM);
		}
		String flightIdParam = request.getParameter(FLIGHT_ID_PARAM);
		List<User> freeUsers = null;
		int idFlight=0;
		if ((flightIdParam != null) && (!(flightIdParam.equals("")))) {
			request.getSession().setAttribute(FLIGHT_ID_ATTR, flightIdParam);
			idFlight=Integer.parseInt(flightIdParam);
			List<CrewPosition> freeCrewPositions = null;
			try {
				freeCrewPositions = flightService.getFreeCrewPositions(idFlight);
			} catch (ServiceException e1) {
				// rootLogger.error(e1);
				e1.printStackTrace();
			}
			request.getSession().setAttribute(FREE_POSITIONS_BY_FLIGHT_ATTR, freeCrewPositions);
		}else {
			idFlight=Integer.parseInt((String)request.getSession().getAttribute(FLIGHT_ID_ATTR));
		}
		
		if (selectedPosition == null) {
			try {
				request.setAttribute(CURRENT_PAGE, PATH_TO_ADD_CREW_TO_FLIGHT);
				request.getRequestDispatcher(PATH_TO_ADMIN_PAGE).forward(request, response);
			} catch (ServletException | IOException e) {
				rootLogger.error(e);
			}
		} else {
			String selectedPositionAttr=(String)request.getSession().getAttribute(SELECTED_POSITION_ATTR);
			if(selectedPositionAttr==null) {
				request.getSession().setAttribute(SELECTED_POSITION_ATTR, selectedPosition);
			}
			int idCrewPosition=getIdSelectedPosition(request);
			UserService userService = serviceFactory.getUserService();
			if (selectedUserId == null) {
				try {
					freeUsers = userService.getFreeUsers(idFlight, selectedPosition);
					request.setAttribute(FREE_USERS_BY_POSITION_ATTR, freeUsers);
					request.setAttribute(CURRENT_PAGE, PATH_TO_ADD_CREW_TO_FLIGHT);
					request.getRequestDispatcher(PATH_TO_ADMIN_PAGE).forward(request, response);
				} catch (ServiceException | ServletException | IOException e) {
					// rootLogger.error(e);
					e.printStackTrace();
				}
			} else {
				try {
					request.setAttribute(CURRENT_PAGE,PATH_TO_CREW_BY_FLIGHT);
					userService.addCrewToFlight(idCrewPosition,idFlight, Integer.parseInt(selectedUserId));
					request.getSession().removeAttribute(SELECTED_POSITION_ATTR);
					Flight flight=flightService.getFlight(idFlight);
					request.getSession().setAttribute(SELECTED_FLIGHT_ATTR, flight);
					List<Crew> team=userService.getUsers(idFlight);
					request.setAttribute(TEAM_BY_FLIGHT_ATTR, team);
					request.getRequestDispatcher(PATH_TO_ADMIN_PAGE).forward(request, response);
				} catch (ServiceException | ServletException | IOException e) {
					// rootLogger.error(e);
					e.printStackTrace();
				}

			}

		}
	}
	
	private int getIdSelectedPosition( HttpServletRequest request) {
		String selectedPosition=(String)request.getSession().getAttribute(SELECTED_POSITION_ATTR);
		List<CrewPosition> freeCrewPositions=(List<CrewPosition>)request.getSession().getAttribute(FREE_POSITIONS_BY_FLIGHT_ATTR);
		for(CrewPosition crewPosition: freeCrewPositions) {
			if(crewPosition.getCrewPosition().equals(selectedPosition)) {
				return crewPosition.getIdCrewPosition();
			}
		}
		return 0;
		
	}
}
