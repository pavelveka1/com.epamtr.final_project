package by.epamtr.airline.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtr.airline.controller.ConstantController;
import by.epamtr.airline.controller.command.Command;
import by.epamtr.airline.entity.Crew;
import by.epamtr.airline.entity.Flight;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.UserService;
import by.epamtr.airline.service.exception.ServiceException;

public class DeleteCrewFromFlightCommand implements Command{
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();
		int flightId=Integer.parseInt(request.getParameter(ConstantController.Parameter.FLIGHT_ID));
		String userId=request.getParameter(ConstantController.Parameter.USER_ID);
		request.setAttribute(ConstantController.Attribute.CURRENT_PAGE, ConstantController.PathToPage.PATH_TO_CREW_BY_FLIGHT);
		if(userId!=null) {
			try {
				userService.deliteCrewFromFlight(flightId, Integer.parseInt(userId));
				List<Crew> team=userService.getUsers(flightId);
				Flight flight=serviceFactory.getFlightService().getFlight(flightId);
				request.setAttribute(ConstantController.Attribute.SELECTED_FLIGHT, flight);
				request.setAttribute(ConstantController.Attribute.TEAM_BY_FLIGHT, team);
				request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request, response);
			} catch (NumberFormatException | ServiceException | ServletException | IOException e) {
				// rootLogger.error(e);
				e.printStackTrace();
			}
		}
		
	}

}
