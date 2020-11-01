package by.epamtr.airline.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtr.airline.controller.ConstantController;
import by.epamtr.airline.controller.command.Command;
import by.epamtr.airline.entity.Aircraft;
import by.epamtr.airline.entity.AircraftType;
import by.epamtr.airline.service.AircraftService;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.exception.ServiceException;
import by.epamtr.airline.service.validator.AircraftValidation;

public class AddAircraftCommand implements Command {
	
	ServiceFactory serviceFactory = ServiceFactory.getInstance();
	AircraftService aircraftService = serviceFactory.getAircraftService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		List<AircraftType> aircraftTypes = null;
		try {
			aircraftTypes = aircraftService.getAircraftTypes();
		} catch (ServiceException e1) {
			// rootLogger.error(e);
			e1.printStackTrace();
		}
		
		request.getSession().setAttribute(ConstantController.Attribute.AIRCRAFT_TYPES, aircraftTypes);
		String registerNumberParameter = request.getParameter(ConstantController.Parameter.REGISTER_NUMBER_PARAM);
		request.setAttribute(ConstantController.Attribute.CURRENT_PAGE, ConstantController.PathToPage.PATH_TO_ADD_AIRCRAFT);
		if (registerNumberParameter == null) {
			try {
				
				request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request, response);
			} catch (ServletException | IOException  e) {
				// rootLogger.error(e);
				e.printStackTrace();
			}
		} else {
			try {
				int idTypeAircraft = (Integer) request.getSession().getAttribute(ConstantController.Attribute.ID_AIRCRAFT_TYPE);
				AircraftType aircraftType=new AircraftType();
				aircraftType.setIdAircraftType(idTypeAircraft);
				String registerNumber = request.getParameter(ConstantController.Parameter.REGISTER_NUMBER_PARAM);
				String statusAircraft = request.getParameter(ConstantController.Parameter.AIRCRAFT_STATUS_PARAM);
				Aircraft aircraft=new Aircraft();
				aircraft.setRegisterNumber(registerNumber);
				aircraft.setStatus(statusAircraft);
				if(AircraftValidation.validateRegistrationNumber(registerNumber)) {
					boolean result=aircraftService.addAircraft(aircraft, aircraftType);
					if (result) {
						request.setAttribute(ConstantController.Attribute.RESULT_ATTR, ConstantController.Attribute.SUCCESSFUL_OPERATION);
					} else {
						request.setAttribute(ConstantController.Attribute.RESULT_ATTR, ConstantController.Attribute.FAILED_OPERATION);
					}
				}else {
					request.setAttribute(ConstantController.Attribute.DATA_IS_VALID, false);
				}
				request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request, response);
			} catch (ServletException | IOException | ServiceException e) {
				// rootLogger.error(e);
				e.printStackTrace();
			}
		}

	}

}
