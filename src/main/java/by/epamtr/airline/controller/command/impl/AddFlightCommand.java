package by.epamtr.airline.controller.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.epamtr.airline.controller.ConstantController;
import by.epamtr.airline.controller.LoggerMessageConstant;
import by.epamtr.airline.controller.command.Command;
import by.epamtr.airline.controller.validation.FlightValidation;
import by.epamtr.airline.entity.Aircraft;
import by.epamtr.airline.entity.Flight;
import by.epamtr.airline.service.AircraftService;
import by.epamtr.airline.service.FlightService;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.exception.ServiceException;

public class AddFlightCommand implements Command {

	private static final Logger logger = Logger.getLogger(AddFlightCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		 ServiceFactory serviceFactory = ServiceFactory.getInstance();
		 AircraftService aircraftService = serviceFactory.getAircraftService();
		 FlightService flightService = serviceFactory.getFlightService();
		 List<Aircraft> aircrafts = new ArrayList<Aircraft>();
		String currentCity = request.getParameter(ConstantController.Parameter.CURRENT_CITY);
		request.getSession().setAttribute(ConstantController.Attribute.CURRENT_PAGE,
				ConstantController.PathToPage.PATH_TO_ADD_FLIGHT);
		if (currentCity == null) {
			try {
				aircrafts = aircraftService.getAircraftrs();
				request.getSession().setAttribute(ConstantController.Attribute.AIRCRAFTS, aircrafts);
			} catch (ServiceException e) {
				logger.error(LoggerMessageConstant.ERROR_GET_AIRCRAFTS, e);
				request.setAttribute(ConstantController.Attribute.ERROR,
						ConstantController.Attribute.SOMETHING_GOES_WRONG);
			}
		} else {
			try {
				String currentTown = request.getParameter(ConstantController.Parameter.CURRENT_CITY);
				String destinationCity = request.getParameter(ConstantController.Parameter.DESTINATION_CITY);
				int flightRange = 0;
				int flightTime = 0;
				if (request.getParameter(ConstantController.Parameter.FLIGHT_RANGE) != ""
						&& request.getParameter(ConstantController.Parameter.FLIGHT_TIME) != "") {
					flightRange = Integer.parseInt(request.getParameter(ConstantController.Parameter.FLIGHT_RANGE));
					flightTime = Integer.parseInt(request.getParameter(ConstantController.Parameter.FLIGHT_TIME));
				}
				String timeDeparture = request.getParameter(ConstantController.Parameter.TIME_DEPARTURE);
				String status = request.getParameter(ConstantController.Parameter.STATUS);
				int idAircraft = (Integer) request.getSession().getAttribute(ConstantController.Attribute.ID_AIRCRAFT);
				Flight flight = new Flight();
				flight.setCurrentCity(currentTown);
				flight.setDestinationCity(destinationCity);
				flight.setFlightRange(flightRange);
				flight.setFlightTime(flightTime);
				flight.setTimeDeparture(timeDeparture);
				flight.setStatus(status);
				Aircraft aircraft = new Aircraft();
				aircraft.setIdAircraft(idAircraft);
				boolean result = false;
				boolean dataIsValid = true;
				if (!FlightValidation.cityValidation(flight.getCurrentCity())) {
					request.setAttribute(ConstantController.Attribute.CURRENT_CITY_VALID, false);
					dataIsValid = false;
				}
				if (!FlightValidation.cityValidation(flight.getDestinationCity())) {
					request.setAttribute(ConstantController.Attribute.DESTINATION_CITY_VALID, false);
					dataIsValid = false;
				}

				if (!FlightValidation.flightRangeValidation(flight.getFlightRange())) {
					request.setAttribute(ConstantController.Attribute.FLIGHT_RANGE_VALID, false);
					dataIsValid = false;
				}
				if (!FlightValidation.flightTimeValidation(flight.getFlightTime())) {
					request.setAttribute(ConstantController.Attribute.FLIGHT_TIME_VALID, false);
					dataIsValid = false;
				}
				if (!FlightValidation.dateValidation(flight.getTimeDeparture())) {
					request.setAttribute(ConstantController.Attribute.TIME_DEPARTURE_VALID, false);
					dataIsValid = false;
				}

				if (dataIsValid == true) {
					result = flightService.addFlight(flight, aircraft);
					if (result) {
						logger.info(LoggerMessageConstant.FLIGHT_ADDED);
						request.setAttribute(ConstantController.Attribute.RESULT_ATTR,
								ConstantController.Attribute.SUCCESSFUL_OPERATION);
					} else {
						request.setAttribute(ConstantController.Attribute.RESULT_ATTR,
								ConstantController.Attribute.FAILED_OPERATION);
						logger.info(LoggerMessageConstant.FLIGHT_NOT_ADDED);
					}
				} else {
					logger.info(LoggerMessageConstant.FLIGHT_NOT_VALID);

				}
			} catch (ServiceException e) {
				logger.error(LoggerMessageConstant.ERROR_GO_TO_MAIN_PAGE, e);
				request.setAttribute(ConstantController.Attribute.ERROR,
						ConstantController.Attribute.SOMETHING_GOES_WRONG);
			}
		}

		try {
			request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request, response);
		} catch (ServletException | IOException e) {
			logger.error(LoggerMessageConstant.ERROR_GO_TO_MAIN_PAGE, e);
		}

	}

}
