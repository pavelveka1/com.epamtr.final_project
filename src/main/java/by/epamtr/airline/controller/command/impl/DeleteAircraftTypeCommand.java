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
import by.epamtr.airline.entity.AircraftType;
import by.epamtr.airline.service.AircraftService;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.exception.ServiceException;

public class DeleteAircraftTypeCommand implements Command {
	private static final Logger LOGGER = Logger.getLogger(DeleteAircraftTypeCommand.class);
	ServiceFactory serviceFactory = ServiceFactory.getInstance();
	AircraftService aircraftService = serviceFactory.getAircraftService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		List<AircraftType> aircraftTypes = null;
		String deleteParameter = request.getParameter(ConstantController.Parameter.DELETE_AIRCRAFT_TYPE);
		request.setAttribute(ConstantController.Attribute.CURRENT_PAGE,
				ConstantController.PathToPage.PATH_TO_DELETE_AIRCRAFT_TYPE);
		if (deleteParameter == null) {
			LOGGER.info(LoggerMessageConstant.GO_TO_DELETE_AIRCRAFT_TYPE);
			try {
				aircraftTypes = aircraftService.getAircraftTypes();
				request.setAttribute(ConstantController.Attribute.AIRCRAFT_TYPES, aircraftTypes);
			} catch (ServiceException e) {
				LOGGER.error(LoggerMessageConstant.ERROR_GET_AIRCRAFT_TYPES, e);
				request.setAttribute(ConstantController.Attribute.ERROR, e);
			}
		} else {

			int id = (Integer) request.getSession().getAttribute(ConstantController.Attribute.ID_AIRCRAFT_TYPE);
			boolean result = false;
			try {
				result = aircraftService.deliteAircraftType(id);
			} catch (ServiceException e) {
				LOGGER.error(LoggerMessageConstant.ERROR_DELETE_AIRCRAFT_TYPE, e);
				request.setAttribute(ConstantController.Attribute.ERROR, e);
			}
			if (result) {
				LOGGER.info(LoggerMessageConstant.AIRCRAFT_TYPE_IS_DELETED);
				request.setAttribute(ConstantController.Attribute.RESULT_ATTR,
						ConstantController.Attribute.SUCCESSFUL_OPERATION);
			} else {
				LOGGER.info(LoggerMessageConstant.AIRCRAFT_TYPE_IS_NOT_DELETED);
				request.setAttribute(ConstantController.Attribute.RESULT_ATTR,
						ConstantController.Attribute.FAILED_OPERATION);
			}
			try {
				aircraftTypes = aircraftService.getAircraftTypes();
			} catch (ServiceException e) {
				LOGGER.error(LoggerMessageConstant.ERROR_GET_AIRCRAFT_TYPES, e);
				request.setAttribute(ConstantController.Attribute.ERROR, e);
			}
			request.setAttribute(ConstantController.Attribute.AIRCRAFT_TYPES, aircraftTypes);
		}
		try {
			request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request, response);
		} catch (ServletException | IOException e) {
			LOGGER.error(LoggerMessageConstant.ERROR_GO_TO_MAIN_PAGE, e);
		}
	}
}
