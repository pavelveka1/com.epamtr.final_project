package by.epamtr.airline.controller.command.impl;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.epamtr.airline.controller.command.Command;
import by.epamtr.airline.entity.Crew;
import by.epamtr.airline.entity.Flight;
import by.epamtr.airline.entity.FlightStatus;
import by.epamtr.airline.entity.User;
import by.epamtr.airline.service.FlightService;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.UserService;
import by.epamtr.airline.service.exception.ServiceException;

public class GetUsersByFlightIdCommand implements Command {
	private static final String PATH_TO_GET_USERS_BY_FLIGHT = "/WEB-INF/jsp/administrator_action/users_by_flight.jsp";
	private static final String PATH_TO_ADD_CREW_ITEM_TO_FLIGHT = "/WEB-INF/jsp/dispatcher_action/add_crew_item_to_flight.jsp";
	private static final String PATH_TO_GET_CREW_BY_FLIGHT = "/WEB-INF/jsp/administrator_action/crew_by_flight.jsp";
	private static final String FLIGHT_STATUS_PARAM = "flight_status";
	private static final String ID_FLIGHT_PARAM = "radio_id_flight";
	private static final String ADD_CREW_TO_FLIGHT_PARAM="add_crew";
	private static final String SELECTED_FLIGHT_ATTR="selected_flight";
	private static final String TEAM_BY_FLIGHT_ATTR="flight_team";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		FlightService flightService = serviceFactory.getFlightService();
		List<Flight> flights;
		String flightStatus = request.getParameter(FLIGHT_STATUS_PARAM);
		String selectedFlight=request.getParameter(ID_FLIGHT_PARAM);
		String addCrewParam=request.getParameter(ADD_CREW_TO_FLIGHT_PARAM);
		if(addCrewParam==null) {
			if(selectedFlight==null) {
				if (flightStatus != null) {
					
					try {
						flights = flightService.getFlights(FlightStatus.valueOf(request.getParameter(FLIGHT_STATUS_PARAM)));
						request.getSession().setAttribute("flights", flights);
						try {
							request.getRequestDispatcher(PATH_TO_GET_USERS_BY_FLIGHT).forward(request, response);
						} catch (ServletException | IOException e) {
							rootLogger.error(e);
						}
					} catch (ServiceException e2) {
						// rootLogger.error(e2);
						e2.printStackTrace();
					}

				} else {
					try {
						request.getRequestDispatcher(PATH_TO_GET_USERS_BY_FLIGHT).forward(request, response);
					} catch (ServletException | IOException e) {
						rootLogger.error(e);
					}
				}
			}else {
				int idSelectedFlight=Integer.parseInt(selectedFlight);
				UserService userService = serviceFactory.getUserService();
				try {
					Flight flight=flightService.getFlight(idSelectedFlight);
					request.getSession().setAttribute(SELECTED_FLIGHT_ATTR, flight);
					List<Crew> team=userService.getUsers(idSelectedFlight);
					request.setAttribute(TEAM_BY_FLIGHT_ATTR, team);
					request.getRequestDispatcher(PATH_TO_GET_CREW_BY_FLIGHT).forward(request, response);
				} catch (ServiceException | ServletException | IOException e) {
					// rootLogger.error(e2);
					e.printStackTrace();
				}
			}
		}else {
			
			try {
				request.getRequestDispatcher(PATH_TO_GET_CREW_BY_FLIGHT).forward(request, response);
			} catch (ServletException | IOException e) {
				// rootLogger.error(e2);
				e.printStackTrace();
			}
		
			//получить всех юзеров которые не задействованы в ближайших рейсах по фильтру
			// выбрать им роль, на которую они могут быть назначены и которая отсутствует у данного рейса
			//положить это в аттрибуты
			//при нажатии на Apply добавить crew к flight и вывести новый список экипажа 
		}
		
		

	}

}
