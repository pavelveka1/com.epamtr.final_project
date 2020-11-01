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
import by.epamtr.airline.entity.Flight;
import by.epamtr.airline.service.AircraftService;
import by.epamtr.airline.service.FlightService;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.exception.ServiceException;

public class AddFlightCommand implements Command {
	
	private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private final AircraftService aircraftService=serviceFactory.getAircraftService();
	private final FlightService flightService = serviceFactory.getFlightService();
	private  List<Aircraft> aircrafts=new ArrayList<Aircraft>();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String currentCity=request.getParameter(ConstantController.Parameter.CURRENT_CITY);
		request.setAttribute(ConstantController.Attribute.CURRENT_PAGE, ConstantController.PathToPage.PATH_TO_ADD_FLIGHT);
		if(currentCity==null) {
			try {
				try {
					aircrafts=aircraftService.getAircraftrs();
					request.setAttribute(ConstantController.Attribute.AIRCRAFTS, aircrafts);
				} catch (ServiceException e) {
					rootLogger.error("Error while getting registration number of aircrafts", e);
				}
				request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request, response);
			} catch (ServletException | IOException e) {
				rootLogger.error("Error while going to add_flight.jsp page", e);
			}
		}else {
			try {
				String currentTown = request.getParameter(ConstantController.Parameter.CURRENT_CITY);
				String destinationCity = request.getParameter(ConstantController.Parameter.DESTINATION_CITY);
				int flightRange = Integer.parseInt(request.getParameter(ConstantController.Parameter.FLIGHT_RANGE));
				int flightTime = Integer.parseInt(request.getParameter(ConstantController.Parameter.FLIGHT_TIME));
				String timeDeparture = request.getParameter(ConstantController.Parameter.TIME_DEPARTURE);
				String status = request.getParameter(ConstantController.Parameter.STATUS);
				int idAircraft = (Integer) request.getSession().getAttribute(ConstantController.Attribute.ID_AIRCRAFT);
				Flight flight=new Flight();
				flight.setCurrentCity(currentTown);
				flight.setDestinationCity(destinationCity);
				flight.setFlightRange(flightRange);
				flight.setFlightTime(flightTime);
				flight.setTimeDeparture(timeDeparture);
				flight.setStatus(status);
				Aircraft aircraft=new Aircraft();
				aircraft.setIdAircraft(idAircraft);
				boolean result=flightService.addFlight(flight, aircraft);
				if (result) {
					request.setAttribute(ConstantController.Attribute.RESULT_ATTR, ConstantController.Attribute.SUCCESSFUL_OPERATION);
				} else {
					request.setAttribute(ConstantController.Attribute.RESULT_ATTR, ConstantController.Attribute.FAILED_OPERATION);
				}
				request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request, response);
			} catch (ServiceException | ServletException | IOException e) {
				rootLogger.error("Error while adding new flght",e);
			}
			
		}
		
	}

}
