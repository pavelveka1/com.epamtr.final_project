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

public class ChangeAircraftStatusCommand implements Command {
	
	private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private final AircraftService aircraftService = serviceFactory.getAircraftService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String idAircraftParam = request.getParameter(ConstantController.Parameter.ID_AIRCRAFT);
		List<Aircraft> aircrafts = new ArrayList<Aircraft>();
		request.setAttribute(ConstantController.Attribute.CURRENT_PAGE, ConstantController.PathToPage.PATH_TO_CHANGE_AIRCRAFT_STATUS);
		if (idAircraftParam == null) {
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
				String status=request.getParameter(ConstantController.Parameter.AIRCRAFT_STATUS);
				int idAircraft=Integer.parseInt(idAircraftParam);
				aircraftService.changeAircraftStatus(idAircraft, status);
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
