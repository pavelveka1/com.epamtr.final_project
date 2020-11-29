package by.epamtr.airline.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.epamtr.airline.controller.ConstantController;
import by.epamtr.airline.controller.LoggerMessageConstant;
import by.epamtr.airline.controller.command.Command;
import by.epamtr.airline.controller.validation.AircraftValidation;
import by.epamtr.airline.entity.AircraftType;
import by.epamtr.airline.service.AircraftService;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.exception.ServiceException;

public class AddAircraftTypeCommand implements Command {
	private static final Logger logger = Logger.getLogger(AddAircraftTypeCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		AircraftService aircraftService = serviceFactory.getAircraftService();
		String page = request.getParameter(ConstantController.Parameter.AIRCRAFT_TYPE);
		request.getSession().setAttribute(ConstantController.Attribute.CURRENT_PAGE,
				ConstantController.PathToPage.PATH_TO_ADD_AIRCRAFT_TYPE);
		if (page != null) {
			String aircraftType = request.getParameter(ConstantController.Parameter.AIRCRAFT_TYPE);
			String flightRangeParam = request.getParameter(ConstantController.Parameter.RANGE_FLIGHT);
			String numberPassengerParam = request.getParameter(ConstantController.Parameter.NUMBER_PASSENGERS);

			boolean dataIsValid = true;
			if (!AircraftValidation.validateAircraftType(aircraftType)) {
				request.setAttribute(ConstantController.Attribute.AIRCRAFT_TYPE_VALID, false);
				dataIsValid = false;
			}
			if (!AircraftValidation.validateNumberPassenger(numberPassengerParam)) {
				request.setAttribute(ConstantController.Attribute.PASSENGER_NUMBER_VALID, false);
				dataIsValid = false;
			}
			if (!AircraftValidation.validateFlightRange(flightRangeParam)) {
				request.setAttribute(ConstantController.Attribute.FLIGHT_RANGE_VALID, false);
				dataIsValid = false;
			}
			boolean result = false;
			if (dataIsValid == true) {
				try {
					AircraftType typeAircraft = new AircraftType();
					typeAircraft.setAircraftType(aircraftType);
					typeAircraft.setRangeFlight(Integer.parseInt(flightRangeParam));
					typeAircraft.setNumberPassenger(Integer.parseInt(numberPassengerParam));
					result = aircraftService.addAircraftType(typeAircraft);
				} catch (ServiceException e) {
					logger.error(LoggerMessageConstant.ERROR_ADD_AIRCRAFT_TYPE, e);
					request.setAttribute(ConstantController.Attribute.ERROR, ConstantController.Attribute.SOMETHING_GOES_WRONG);

				}

				if (result) {
					logger.info(LoggerMessageConstant.AIRCRAFT_TYPE_IS_ADDED);
					request.setAttribute(ConstantController.Attribute.RESULT_ATTR,
							ConstantController.Attribute.SUCCESSFUL_OPERATION);
				} else {
					logger.info(LoggerMessageConstant.AIRCRAFT_TYPE_IS_NOT_ADDED);
					request.setAttribute(ConstantController.Attribute.RESULT_ATTR,
							ConstantController.Attribute.FAILED_OPERATION);
				}
			}

		} else {
			logger.info(LoggerMessageConstant.GO_TO_ADD_AIRCRAFT_TYPE);
		}
		try {
			request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request, response);
		} catch (ServletException | IOException e2) {
			logger.error(LoggerMessageConstant.ERROR_GO_TO_MAIN_PAGE, e2);
			request.setAttribute(ConstantController.Attribute.ERROR, ConstantController.Attribute.SOMETHING_GOES_WRONG);
			try {
				request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request,
						response);
			} catch (ServletException | IOException e1) {
				logger.error(LoggerMessageConstant.ERROR_GO_TO_MAIN_PAGE, e1);
			}
		}
	}

}
