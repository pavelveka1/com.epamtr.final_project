package by.epamtr.airline.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtr.airline.controller.command.Command;
import by.epamtr.airline.entity.AircraftType;
import by.epamtr.airline.service.AircraftService;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.exception.ServiceException;

public class DeleteAircraftTypeCommand implements Command {
	private static final String PATH_TO_DELETE_AIRCRAFT_TYPE = "/WEB-INF/jsp/administrator_action/delete_aircraft_type.jsp";
	private static final String PATH_TO_MAIN_PAGE="/WEB-INF/jsp/main_page.jsp";
	private static final String CURRENT_PAGE="current_page";
	ServiceFactory serviceFactory = ServiceFactory.getInstance();
	AircraftService aircraftService = serviceFactory.getAircraftService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		List<AircraftType> aircraftTypes;
		String deleteParameter = request.getParameter("delete");
		request.setAttribute(CURRENT_PAGE, PATH_TO_DELETE_AIRCRAFT_TYPE);
		if (deleteParameter == null) {
			try {
				aircraftTypes = aircraftService.getAircraftTypes();
				request.setAttribute("aircraftTypes", aircraftTypes);
				request.getRequestDispatcher(PATH_TO_MAIN_PAGE).forward(request, response);
			} catch (ServletException | IOException | ServiceException e) {
				//rootLogger.error(e);
				e.printStackTrace();
			}
		} else {
			 try {
				 int id=(Integer)request.getSession().getAttribute("id_iarcraft_type");
				aircraftService.deliteAircraftType(id);
				aircraftTypes = aircraftService.getAircraftTypes();
				request.setAttribute("aircraftTypes", aircraftTypes);
				request.getRequestDispatcher(PATH_TO_MAIN_PAGE).forward(request, response);
			} catch (NumberFormatException | ServiceException | ServletException | IOException e) {
				//rootLogger.error(e);
				e.printStackTrace();
			}
		}

	}

}
