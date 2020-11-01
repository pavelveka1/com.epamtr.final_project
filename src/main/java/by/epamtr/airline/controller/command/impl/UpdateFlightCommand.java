package by.epamtr.airline.controller.command.impl;

import java.io.IOException;
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

public class UpdateFlightCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		FlightService flightService = serviceFactory.getFlightService();
		String selectedFlight = request.getParameter(ConstantController.Parameter.ID_FLIGHT);
		String flightDataStatus = request.getParameter(ConstantController.Parameter.FLIGHT_DATA_STATUS);
		request.setAttribute(ConstantController.Attribute.CURRENT_PAGE, ConstantController.PathToPage.PATH_TO_UPDATE_FLIGHT_DATA);
		if (flightDataStatus != null) {
			try {
				String currentCity = request.getParameter(ConstantController.Parameter.CURRENT_CITY);
				String destinationCity = request.getParameter(ConstantController.Parameter.DESTINATION_CITY);
				int flightRange = Integer.parseInt(request.getParameter(ConstantController.Parameter.FLIGHT_RANGE));
				int flightTime = Integer.parseInt(request.getParameter(ConstantController.Parameter.FLIGHT_TIME));
				String aircraftNumber = request.getParameter(ConstantController.Parameter.AIRCRAFT_NUMBER);
				String timeDeparture = request.getParameter(ConstantController.Parameter.TIME_DEPARTURE);
				String flightStatus = request.getParameter(ConstantController.Parameter.FLIGHT_STATUS);
				Flight flight=new Flight();
				flight.setCurrentCity(currentCity);
				flight.setDestinationCity(destinationCity);
				flight.setFlightRange(flightRange);
				flight.setFlightTime(flightTime);
				flight.setAircraftNumber(aircraftNumber);
				flight.setTimeDeparture(timeDeparture);
				flight.setStatus(flightStatus);
				Flight updatedFlight = flightService.updateFlight(Integer.parseInt(request.getParameter(ConstantController.Parameter.FLIGHT_ID)), flight);
				request.setAttribute(ConstantController.Attribute.SELECTED_FLIGHT, updatedFlight);
				request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request, response);

			} catch (ServiceException | ServletException | IOException e) {
				// rootLogger.error(e2);
				e.printStackTrace();
			}
		} else {
			int idSelectedFlight = Integer.parseInt(selectedFlight);
			try {
				request.setAttribute(ConstantController.Attribute.CURRENT_PAGE, ConstantController.PathToPage.PATH_TO_UPDATE_FLIGHT_DATA);
				Flight flight = flightService.getFlight(idSelectedFlight);
				AircraftService aircraftService = serviceFactory.getAircraftService();
				List<Aircraft> aircrafts = aircraftService.getAircraftrs();
				request.setAttribute(ConstantController.Attribute.AIRCRAFTS, aircrafts);
				request.setAttribute(ConstantController.Attribute.SELECTED_FLIGHT, flight);
				request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request, response);
			} catch (ServiceException | ServletException | IOException e) {
				// rootLogger.error(e2);
				e.printStackTrace();
			}
		}
	}
}
