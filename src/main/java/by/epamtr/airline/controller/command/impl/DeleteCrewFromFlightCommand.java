package by.epamtr.airline.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.epamtr.airline.controller.ConstantController;
import by.epamtr.airline.controller.LoggerMessageConstant;
import by.epamtr.airline.controller.command.Command;
import by.epamtr.airline.entity.Crew;
import by.epamtr.airline.entity.Flight;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.UserService;
import by.epamtr.airline.service.exception.ServiceException;

public class DeleteCrewFromFlightCommand implements Command {
	private static final Logger logger = Logger.getLogger(DeleteCrewFromFlightCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();
		int flightId = Integer.parseInt(request.getParameter(ConstantController.Parameter.FLIGHT_ID));
		String userId = request.getParameter(ConstantController.Parameter.USER_ID);
		request.getSession().setAttribute(ConstantController.Attribute.CURRENT_PAGE,
				ConstantController.PathToPage.PATH_TO_CREW_BY_FLIGHT);
		if (userId != null) {

			boolean result = false;
			try {
				result = userService.deliteCrewFromFlight(flightId, Integer.parseInt(userId));
			} catch (NumberFormatException | ServiceException e1) {
				logger.error(LoggerMessageConstant.ERROR_DELETE_CREW_FROM_FLIGHT, e1);
				request.setAttribute(ConstantController.Attribute.ERROR, ConstantController.Attribute.SOMETHING_GOES_WRONG);
			}
			if (result) {
				logger.info(LoggerMessageConstant.CREW_IS_DELETED_FROM_FLIGHT);
			} else {
				logger.info(LoggerMessageConstant.CREW_IS_NOT_DELETED_FROM_FLIGHT);
			}
			List<Crew> team = null;
			try {
				team = userService.getUsers(flightId);
			} catch (ServiceException e1) {
				logger.error(LoggerMessageConstant.ERROR_GET_USERS_BY_FLIGHT_ID, e1);
				request.setAttribute(ConstantController.Attribute.ERROR, ConstantController.Attribute.SOMETHING_GOES_WRONG);
			}
			Flight flight = null;
			try {
				flight = serviceFactory.getFlightService().getFlight(flightId);
			} catch (ServiceException e1) {
				logger.error(LoggerMessageConstant.ERROR_GET_FLIGHT_BY_FLIGHT_ID, e1);
				request.setAttribute(ConstantController.Attribute.ERROR, ConstantController.Attribute.SOMETHING_GOES_WRONG);
			}
			request.getSession().setAttribute(ConstantController.Attribute.SELECTED_FLIGHT, flight);
			request.getSession().setAttribute(ConstantController.Attribute.TEAM_BY_FLIGHT, team);
			try {
				request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request,
						response);
			} catch (ServletException | IOException e) {
				logger.error(LoggerMessageConstant.ERROR_GO_TO_MAIN_PAGE, e);
			}
		}

	}

}
