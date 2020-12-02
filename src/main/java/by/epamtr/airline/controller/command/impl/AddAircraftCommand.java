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
import by.epamtr.airline.controller.validation.AircraftValidation;
import by.epamtr.airline.entity.Aircraft;
import by.epamtr.airline.entity.AircraftType;
import by.epamtr.airline.service.AircraftService;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.exception.ServiceException;

public class AddAircraftCommand implements Command {
	private static final Logger logger = Logger.getLogger(AddAircraftCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		AircraftService aircraftService = serviceFactory.getAircraftService();
		List<AircraftType> aircraftTypes = null;

		try {
			aircraftTypes = aircraftService.getAircraftTypes();
		} catch ( ServiceException e1) {
			logger.error(LoggerMessageConstant.ERROR_GET_AIRCRAFT_TYPES, e1);
			request.setAttribute(ConstantController.Attribute.ERROR, ConstantController.Attribute.SOMETHING_GOES_WRONG);
		}

		request.getSession().setAttribute(ConstantController.Attribute.AIRCRAFT_TYPES, aircraftTypes);
		String registerNumberParameter = request.getParameter(ConstantController.Parameter.REGISTER_NUMBER_PARAM);
		request.getSession().setAttribute(ConstantController.Attribute.CURRENT_PAGE,
				ConstantController.PathToPage.PATH_TO_ADD_AIRCRAFT);
		if (registerNumberParameter == null) {
			logger.info(LoggerMessageConstant.GO_TO_PAGE_ADD_AIRCRAFT);
		} else {

			int idTypeAircraft = (Integer) request.getSession()
					.getAttribute(ConstantController.Attribute.ID_AIRCRAFT_TYPE);
			AircraftType aircraftType = new AircraftType();
			aircraftType.setIdAircraftType(idTypeAircraft);
			String registerNumber = request.getParameter(ConstantController.Parameter.REGISTER_NUMBER_PARAM);
			String statusAircraft = request.getParameter(ConstantController.Parameter.AIRCRAFT_STATUS_PARAM);
			Aircraft aircraft = new Aircraft();
			aircraft.setRegisterNumber(registerNumber);
			aircraft.setStatus(statusAircraft);
			if (AircraftValidation.validateRegistrationNumber(registerNumber)) {
				boolean result = false;
				try {
					result = aircraftService.addAircraft(aircraft, aircraftType);
				} catch (ServiceException e) {
					logger.error(LoggerMessageConstant.ERROR_ADD_AIRCRAFT, e);
					request.setAttribute(ConstantController.Attribute.ERROR, ConstantController.Attribute.SOMETHING_GOES_WRONG);
				}
				if (result) {
					logger.info(LoggerMessageConstant.AIRCRAFT_IS_ADDED);
					request.setAttribute(ConstantController.Attribute.RESULT_ATTR,
							ConstantController.Attribute.SUCCESSFUL_OPERATION);
				} else {
					logger.info(LoggerMessageConstant.AIRCRAFT_IS_NOT_ADDED);
					request.setAttribute(ConstantController.Attribute.RESULT_ATTR,
							ConstantController.Attribute.FAILED_OPERATION);
				}
			} else {
				logger.info(LoggerMessageConstant.AIRCRAFT_DATA_NOT_VALID);
				request.setAttribute(ConstantController.Attribute.DATA_IS_VALID, false);
			}
		}
		try {

			request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request, response);
		} catch (ServletException | IOException e) {
			logger.error(LoggerMessageConstant.ERROR_GO_TO_MAIN_PAGE, e);
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
