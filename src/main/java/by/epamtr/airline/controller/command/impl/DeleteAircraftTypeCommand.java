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
		List<AircraftType> aircraftTypes;
		String deleteParameter = request.getParameter(ConstantController.Parameter.DELETE_AIRCRAFT_TYPE);
		request.setAttribute(ConstantController.Attribute.CURRENT_PAGE, ConstantController.PathToPage.PATH_TO_DELETE_AIRCRAFT_TYPE);
		if (deleteParameter == null) {
			try {
				LOGGER.info(LoggerMessageConstant.GO_TO_DELETE_AIRCRAFT_TYPE);
				aircraftTypes = aircraftService.getAircraftTypes();
				request.setAttribute(ConstantController.Attribute.AIRCRAFT_TYPES, aircraftTypes);
				request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request, response);
			} catch (ServletException | IOException | ServiceException e) {
				LOGGER.error(LoggerMessageConstant.ERROR_GO_TO_MAIN_PAGE, e);
			}
		} else {
			 try {
				 int id=(Integer)request.getSession().getAttribute(ConstantController.Attribute.ID_AIRCRAFT_TYPE);
				boolean result=aircraftService.deliteAircraftType(id);
				if(result) {
					LOGGER.info(LoggerMessageConstant.AIRCRAFT_TYPE_IS_DELETED);
				}else {
					LOGGER.info(LoggerMessageConstant.AIRCRAFT_TYPE_IS_NOT_DELETED);
				}
				aircraftTypes = aircraftService.getAircraftTypes();
				request.setAttribute(ConstantController.Attribute.AIRCRAFT_TYPES, aircraftTypes);
				request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request, response);
			} catch (NumberFormatException | ServiceException | ServletException | IOException e) {
				LOGGER.error(LoggerMessageConstant.ERROR_GO_TO_MAIN_PAGE, e);
			}
		}

	}

}
