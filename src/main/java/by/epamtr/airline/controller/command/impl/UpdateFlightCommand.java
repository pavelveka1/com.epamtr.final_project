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
import by.epamtr.airline.entity.Aircraft;
import by.epamtr.airline.entity.Flight;
import by.epamtr.airline.service.AircraftService;
import by.epamtr.airline.service.FlightService;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.exception.ServiceException;
import by.epamtr.airline.service.validator.FlightValidation;

public class UpdateFlightCommand implements Command {
	private static final Logger LOGGER = Logger.getLogger(UpdateFlightCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		FlightService flightService = serviceFactory.getFlightService();
		String selectedFlight = request.getParameter(ConstantController.Parameter.ID_FLIGHT);
		String flightDataStatus = request.getParameter(ConstantController.Parameter.FLIGHT_DATA_STATUS);
		request.setAttribute(ConstantController.Attribute.CURRENT_PAGE,
				ConstantController.PathToPage.PATH_TO_UPDATE_FLIGHT_DATA);
		if (flightDataStatus != null) {
			try {
				LOGGER.info(LoggerMessageConstant.GO_TO_PAGE_UPDATE_FLIGHT);
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
					
					Flight updatedFlight = flightService.updateFlight(
							Integer.parseInt(request.getParameter(ConstantController.Parameter.FLIGHT_ID)), flight);
					request.setAttribute(ConstantController.Attribute.SELECTED_FLIGHT, updatedFlight);
					if(flight!=null) {
						LOGGER.info(LoggerMessageConstant.FLIGHT_IS_UPDATED);
					}else {
						LOGGER.info(LoggerMessageConstant.FLIGHT_IS_NOT_UPDATED);
					}

				} else {
					LOGGER.info(LoggerMessageConstant.UPDATE_FLIGHT_DATA_NOT_VALID);
				}
				request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request,
						response);
			} catch (ServiceException | ServletException | IOException e) {
				LOGGER.error(LoggerMessageConstant.ERROR_GO_TO_MAIN_PAGE, e);
			}
		} else {
			LOGGER.info(LoggerMessageConstant.GO_TO_PAGE_CHOOSE_FLIGHT_STATUS);
			int idSelectedFlight = Integer.parseInt(selectedFlight);
			try {
				request.setAttribute(ConstantController.Attribute.CURRENT_PAGE,
						ConstantController.PathToPage.PATH_TO_UPDATE_FLIGHT_DATA);
				Flight flight = flightService.getFlight(idSelectedFlight);
				AircraftService aircraftService = serviceFactory.getAircraftService();
				List<Aircraft> aircrafts = aircraftService.getAircraftrs();
				request.getSession().setAttribute(ConstantController.Attribute.AIRCRAFTS, aircrafts);
				request.getSession().setAttribute(ConstantController.Attribute.SELECTED_FLIGHT, flight);
				request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request,
						response);
			} catch (ServiceException | ServletException | IOException e) {
				LOGGER.error(LoggerMessageConstant.ERROR_GO_TO_MAIN_PAGE, e);
			}
		}
	}
}
