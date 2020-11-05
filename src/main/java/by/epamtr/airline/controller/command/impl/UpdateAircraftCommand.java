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
import by.epamtr.airline.entity.Aircraft;
import by.epamtr.airline.entity.AircraftType;
import by.epamtr.airline.service.AircraftService;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.exception.ServiceException;
import by.epamtr.airline.service.validator.AircraftValidation;

public class UpdateAircraftCommand implements Command {
	private static final Logger LOGGER = Logger.getLogger(SignIn.class);
	ServiceFactory serviceFactory = ServiceFactory.getInstance();
	AircraftService aircraftService = serviceFactory.getAircraftService();
	List<Aircraft> aircrafts = new ArrayList<Aircraft>();
	List<AircraftType> aircraftTypes=new ArrayList<AircraftType>();
	

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		String aircraftsParameter = request.getParameter(ConstantController.Parameter.AIRCRAFT_NUMBER);
		request.setAttribute(ConstantController.Attribute.CURRENT_PAGE, ConstantController.PathToPage.PATH_TO_UPDATE_AIRCRAFT);
		if (aircraftsParameter == null) {
			try {
				LOGGER.info(LoggerMessageConstant.GO_TO_PAGE_CHOOSE_AIRCRAFT);
				aircrafts = aircraftService.getAircraftrs();
				request.getSession().setAttribute(ConstantController.Attribute.AIRCRAFTS, aircrafts);
				request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request, response);
			} catch (ServletException | IOException | ServiceException e) {
				LOGGER.error(LoggerMessageConstant.ERROR_GO_TO_MAIN_PAGE, e);
			}
		} else {
			try {
				String registrationNumber = request.getParameter(ConstantController.Parameter.AIRCRAFT_NUMBER);
				String newRegistrationNumber = request.getParameter(ConstantController.Parameter.REGISTER_NUMBER_PARAM);
				if(AircraftValidation.validateRegistrationNumber(newRegistrationNumber)) {
					boolean result=aircraftService.updateAircraft(registrationNumber, newRegistrationNumber);
					if (result) {
						LOGGER.info(LoggerMessageConstant.AIRCRAFT_IS_UPDATED);
						request.setAttribute(ConstantController.Attribute.RESULT_ATTR, ConstantController.Attribute.SUCCESSFUL_OPERATION);
					} else {
						LOGGER.info(LoggerMessageConstant.AIRCRAFT_IS_NOT_UPDATED);
						request.setAttribute(ConstantController.Attribute.RESULT_ATTR, ConstantController.Attribute.FAILED_OPERATION);
					}
				}else {
					request.setAttribute(ConstantController.Attribute.DATA_IS_VALID, false);
				}
				aircrafts = aircraftService.getAircraftrs();
				request.getSession().setAttribute(ConstantController.Attribute.AIRCRAFTS, aircrafts);
				request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request, response);
			} catch (ServletException | IOException | ServiceException e) {
				LOGGER.error(LoggerMessageConstant.ERROR_GO_TO_MAIN_PAGE, e);
			}
		}

	}

}
