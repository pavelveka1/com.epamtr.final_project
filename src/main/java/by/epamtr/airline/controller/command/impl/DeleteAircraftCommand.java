package by.epamtr.airline.controller.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtr.airline.controller.ConstantController;
import by.epamtr.airline.controller.command.Command;
import by.epamtr.airline.entity.Aircraft;
import by.epamtr.airline.service.AircraftService;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.exception.ServiceException;

public class DeleteAircraftCommand implements Command {
	
	private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private final AircraftService aircraftService = serviceFactory.getAircraftService();
	 private List<Aircraft> aircrafts = new ArrayList<Aircraft>();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		String aircraftsParameter = request.getParameter(ConstantController.Parameter.AIRCRAFT_NUMBER);
		request.setAttribute(ConstantController.Attribute.CURRENT_PAGE, ConstantController.PathToPage.PATH_TO_DELETE_AIRCRAFT);
		if (aircraftsParameter == null) {
			try {
				aircrafts = aircraftService.getAircraftrs();
				request.setAttribute(ConstantController.Attribute.AIRCRAFTS, aircrafts);
				request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request, response);
			} catch (ServletException | IOException | ServiceException e) {
				// rootLogger.error(e);
				e.printStackTrace();
			}
		} else {
			try {
				aircraftService.deleteAircraft(request.getParameter(ConstantController.Parameter.AIRCRAFT_NUMBER));
				aircrafts = aircraftService.getAircraftrs();
				request.setAttribute(ConstantController.Attribute.AIRCRAFTS, aircrafts);
				request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request, response);
			} catch (ServletException | IOException | ServiceException e) {
				// rootLogger.error(e);
				e.printStackTrace();
			}
		}

	}

}
