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
import by.epamtr.airline.controller.validation.AircraftValidation;
import by.epamtr.airline.entity.Aircraft;
import by.epamtr.airline.entity.AircraftType;
import by.epamtr.airline.service.AircraftService;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.exception.ServiceException;

public class UpdateAircraftCommand implements Command {
	private static final Logger logger = Logger.getLogger(SignIn.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		AircraftService aircraftService = serviceFactory.getAircraftService();
		List<Aircraft> aircrafts = new ArrayList<Aircraft>();
		List<AircraftType> aircraftTypes = new ArrayList<AircraftType>();
		String aircraftsParameter = request.getParameter(ConstantController.Parameter.AIRCRAFT_NUMBER);
		request.getSession().setAttribute(ConstantController.Attribute.CURRENT_PAGE,
				ConstantController.PathToPage.PATH_TO_UPDATE_AIRCRAFT);
		if (aircraftsParameter == null) {
			logger.info(LoggerMessageConstant.GO_TO_PAGE_CHOOSE_AIRCRAFT);
			try {
				aircrafts = aircraftService.getAircraftrs();
			} catch (ServiceException e) {
				logger.error(LoggerMessageConstant.ERROR_GET_AIRCRAFTS, e);
				request.setAttribute(ConstantController.Attribute.ERROR,
						ConstantController.Attribute.SOMETHING_GOES_WRONG);
			}
			request.getSession().setAttribute(ConstantController.Attribute.AIRCRAFTS, aircrafts);
		} else {

			String registrationNumber = request.getParameter(ConstantController.Parameter.AIRCRAFT_NUMBER);
			String newRegistrationNumber = request.getParameter(ConstantController.Parameter.REGISTER_NUMBER_PARAM);
			if (AircraftValidation.validateRegistrationNumber(newRegistrationNumber)) {
				boolean result = false;
				try {
					result = aircraftService.updateAircraft(registrationNumber, newRegistrationNumber);
				} catch (ServiceException e) {
					logger.error(LoggerMessageConstant.ERROR_UPDATE_AIRCRAFT, e);
					request.setAttribute(ConstantController.Attribute.ERROR,
							ConstantController.Attribute.SOMETHING_GOES_WRONG);
				}
				if (result) {
					logger.info(LoggerMessageConstant.AIRCRAFT_IS_UPDATED);
					request.setAttribute(ConstantController.Attribute.RESULT_ATTR,
							ConstantController.Attribute.SUCCESSFUL_OPERATION);
				} else {
					logger.info(LoggerMessageConstant.AIRCRAFT_IS_NOT_UPDATED);
					request.setAttribute(ConstantController.Attribute.RESULT_ATTR,
							ConstantController.Attribute.FAILED_OPERATION);
				}
			} else {
				request.setAttribute(ConstantController.Attribute.DATA_IS_VALID, false);
			}
			try {
				aircrafts = aircraftService.getAircraftrs();
			} catch (ServiceException e) {
				logger.error(LoggerMessageConstant.ERROR_GET_AIRCRAFTS, e);
				request.setAttribute(ConstantController.Attribute.ERROR,
						ConstantController.Attribute.SOMETHING_GOES_WRONG);
			}
			request.getSession().setAttribute(ConstantController.Attribute.AIRCRAFTS, aircrafts);

		}
		try {
			request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request, response);
		} catch (ServletException | IOException e) {
			logger.error(LoggerMessageConstant.ERROR_GO_TO_MAIN_PAGE, e);
		}
	}

}
