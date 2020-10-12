package by.epamtr.airline.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtr.airline.controller.command.Command;
import by.epamtr.airline.service.AircraftService;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.UserService;
import by.epamtr.airline.service.exception.ServiceException;

public class AddAircraftTypeCommand implements Command {

		private static final String PATH_TO_ADD_AIRCRAFT_TYPE="/WEB-INF/jsp/administrator_action/add_aircraft_type.jsp";

		@Override
		public void execute(HttpServletRequest request, HttpServletResponse response)  {
			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			AircraftService aircraftService = serviceFactory.getAircraftService();
			String page=request.getParameter("aircraft_type");
			if(page!=null) {
				try {
					aircraftService.addAircraftType(request, response);
				} catch (ServiceException e2) {
				//	rootLogger.error(e2);
					e2.printStackTrace();
				}
			}else {
				try {
					request.getRequestDispatcher(PATH_TO_ADD_AIRCRAFT_TYPE).forward(request, response);
				} catch (ServletException | IOException e) {
					rootLogger.error(e);
				}
			}
			
			
		}

}
