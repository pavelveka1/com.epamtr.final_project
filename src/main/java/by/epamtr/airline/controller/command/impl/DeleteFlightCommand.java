package by.epamtr.airline.controller.command.impl;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.epamtr.airline.controller.ConstantController;
import by.epamtr.airline.controller.command.Command;
import by.epamtr.airline.entity.Flight;
import by.epamtr.airline.entity.FlightStatus;
import by.epamtr.airline.entity.User;
import by.epamtr.airline.entity.UserRole;
import by.epamtr.airline.service.FlightService;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.exception.ServiceException;

public class DeleteFlightCommand implements Command {

	private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private final FlightService flightService = serviceFactory.getFlightService();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		List<Flight> flights;
		String flightStatus = request.getParameter(ConstantController.Parameter.STATUS);
		String idFlightParam=request.getParameter(ConstantController.Parameter.ID_FLIGHT);
		String currentUserRole=((User)request.getSession().getAttribute(ConstantController.Attribute.SIGNED_IN_USER)).getRole().getRole();
		if(currentUserRole.equalsIgnoreCase(UserRole.ADMINISTRATOR.getRole())) {
			request.setAttribute(ConstantController.Attribute.CURRENT_PAGE, ConstantController.PathToPage.PATH_TO_DELETE_FLIGHT);
		}else  {
			request.setAttribute(ConstantController.Attribute.CURRENT_PAGE, ConstantController.PathToPage.PATH_TO_UPDATE_FLIGHT);
		}
		
		if(idFlightParam==null) {
			if (flightStatus != null) {
				request.getSession().setAttribute(ConstantController.Attribute.SELECTED_FLIGHT_STATUS_FOR_FLIGHTS, flightStatus);
				try {
					flights = flightService.getFlights(FlightStatus.valueOf(request.getParameter(ConstantController.Parameter.STATUS)));
					request.getSession().setAttribute(ConstantController.Attribute.FLIGHTS, flights);
					request.getSession().setAttribute(ConstantController.Attribute.FLIGHT_STATUS, flightStatus);
					try {
						request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request, response);
					} catch (ServletException | IOException e) {
						//rootLogger.error(e);
						e.printStackTrace();
					}
				} catch (ServiceException e2) {
					// rootLogger.error(e2);
					e2.printStackTrace();
				}

			} else {
				try {
					request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request, response);
				} catch (ServletException | IOException e) {
					rootLogger.error(e);
				}
			}
		}else {
			int idFlight=Integer.parseInt(idFlightParam);
			
			try {
				flightService.deliteFlight(idFlight);
				flights = flightService.getFlights(FlightStatus.valueOf((String)request.getSession().getAttribute(ConstantController.Attribute.SELECTED_FLIGHT_STATUS_FOR_FLIGHTS)));
				request.getSession().setAttribute(ConstantController.Attribute.FLIGHTS, flights);
				request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request, response);
			} catch (ServiceException | ServletException | IOException e) {
				// rootLogger.error(e);
				e.printStackTrace();
			}
		}
	}
}
