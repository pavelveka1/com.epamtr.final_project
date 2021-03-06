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
import by.epamtr.airline.controller.validation.FlightValidation;
import by.epamtr.airline.entity.Aircraft;
import by.epamtr.airline.entity.Flight;
import by.epamtr.airline.service.AircraftService;
import by.epamtr.airline.service.FlightService;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.exception.ServiceException;

public class UpdateFlightCommand implements Command {
	private static final Logger logger = Logger.getLogger(UpdateFlightCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		FlightService flightService = serviceFactory.getFlightService();
		String selectedFlight = request.getParameter(ConstantController.Parameter.ID_FLIGHT);
		String flightDataStatus = request.getParameter(ConstantController.Parameter.FLIGHT_DATA_STATUS);
		request.getSession().setAttribute(ConstantController.Attribute.CURRENT_PAGE,
				ConstantController.PathToPage.PATH_TO_UPDATE_FLIGHT_DATA);
		if (flightDataStatus != null) {
			logger.info(LoggerMessageConstant.GO_TO_PAGE_UPDATE_FLIGHT);
			String currentCity = request.getParameter(ConstantController.Parameter.CURRENT_CITY);
			String destinationCity = request.getParameter(ConstantController.Parameter.DESTINATION_CITY);
			int flightRange = Integer.parseInt(request.getParameter(ConstantController.Parameter.FLIGHT_RANGE));
			int flightTime = Integer.parseInt(request.getParameter(ConstantController.Parameter.FLIGHT_TIME));
			String aircraftNumber = request.getParameter(ConstantController.Parameter.AIRCRAFT_NUMBER);
			String timeDeparture = request.getParameter(ConstantController.Parameter.TIME_DEPARTURE);
			String flightStatus = request.getParameter(ConstantController.Parameter.FLIGHT_STATUS);
			Flight flight = new Flight();
			flight.setCurrentCity(currentCity);
			flight.setDestinationCity(destinationCity);
			flight.setFlightRange(flightRange);
			flight.setFlightTime(flightTime);
			flight.setAircraftNumber(aircraftNumber);
			flight.setTimeDeparture(timeDeparture);
			flight.setStatus(flightStatus);
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
				Flight updatedFlight = null;
				try {
					updatedFlight = flightService.updateFlight(
							Integer.parseInt(request.getParameter(ConstantController.Parameter.FLIGHT_ID)), flight);
				} catch (NumberFormatException | ServiceException e) {
					logger.error(LoggerMessageConstant.ERROR_UPDATE_FLIGHT, e);
					request.setAttribute(ConstantController.Attribute.ERROR, ConstantController.Attribute.SOMETHING_GOES_WRONG);
				}
				request.getSession().setAttribute(ConstantController.Attribute.SELECTED_FLIGHT, updatedFlight);
				if (flight != null) {
					logger.info(LoggerMessageConstant.FLIGHT_IS_UPDATED);
				} else {
					logger.info(LoggerMessageConstant.FLIGHT_IS_NOT_UPDATED);
				}

			} else {
				logger.info(LoggerMessageConstant.UPDATE_FLIGHT_DATA_NOT_VALID);
			}
		} else {
			logger.info(LoggerMessageConstant.GO_TO_PAGE_CHOOSE_FLIGHT_STATUS);
			int idSelectedFlight = Integer.parseInt(selectedFlight);
			request.getSession().setAttribute(ConstantController.Attribute.CURRENT_PAGE,
					ConstantController.PathToPage.PATH_TO_UPDATE_FLIGHT_DATA);
			Flight flight = null;
			try {
				flight = flightService.getFlight(idSelectedFlight);
			} catch (ServiceException e) {
				logger.error(LoggerMessageConstant.ERROR_GET_FLIGHT, e);
				request.setAttribute(ConstantController.Attribute.ERROR, ConstantController.Attribute.SOMETHING_GOES_WRONG);
			}
			AircraftService aircraftService = serviceFactory.getAircraftService();
			List<Aircraft> aircrafts = null;
			try {
				aircrafts = aircraftService.getAircraftrs();
			} catch (ServiceException e) {
				logger.error(LoggerMessageConstant.ERROR_GET_AIRCRAFTS, e);
				request.setAttribute(ConstantController.Attribute.ERROR, ConstantController.Attribute.SOMETHING_GOES_WRONG);
			}
			request.getSession().setAttribute(ConstantController.Attribute.AIRCRAFTS, aircrafts);
			request.getSession().setAttribute(ConstantController.Attribute.SELECTED_FLIGHT, flight);
		}
		try {
			request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request, response);
		} catch (ServletException | IOException e) {
			logger.error(LoggerMessageConstant.ERROR_GO_TO_MAIN_PAGE, e);
		}
	}
}
