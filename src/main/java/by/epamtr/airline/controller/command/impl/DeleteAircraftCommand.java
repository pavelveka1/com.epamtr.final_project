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
import by.epamtr.airline.service.AircraftService;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.exception.ServiceException;

public class DeleteAircraftCommand implements Command {
	private static final Logger logger = Logger.getLogger(DeleteAircraftCommand.class);
	private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private final AircraftService aircraftService = serviceFactory.getAircraftService();
	private List<Aircraft> aircrafts = new ArrayList<Aircraft>();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		String aircraftsParameter = request.getParameter(ConstantController.Parameter.AIRCRAFT_NUMBER);
		request.getSession().setAttribute(ConstantController.Attribute.CURRENT_PAGE,
				ConstantController.PathToPage.PATH_TO_DELETE_AIRCRAFT);
		if (aircraftsParameter == null) {
			logger.info(LoggerMessageConstant.GO_TO_DELETE_AIRCRAFT);
			try {
				aircrafts = aircraftService.getAircraftrs();
				request.getSession().setAttribute(ConstantController.Attribute.AIRCRAFTS, aircrafts);
			} catch (ServiceException e) {
				logger.error(LoggerMessageConstant.ERROR_GET_AIRCRAFTS, e);
				request.setAttribute(ConstantController.Attribute.ERROR, ConstantController.Attribute.SOMETHING_GOES_WRONG);
			}
		} else {

			boolean result = false;
			try {
				result = aircraftService
						.deleteAircraft(request.getParameter(ConstantController.Parameter.AIRCRAFT_NUMBER));
			} catch (ServiceException e) {
				logger.error(LoggerMessageConstant.ERROR_DELETE_AIRCRAFT, e);
				request.setAttribute(ConstantController.Attribute.ERROR, ConstantController.Attribute.SOMETHING_GOES_WRONG);
			}
			if (result) {
				logger.info(LoggerMessageConstant.AIRCRAFT_IS_DELETED);
				request.setAttribute(ConstantController.Attribute.RESULT_ATTR,
						ConstantController.Attribute.SUCCESSFUL_OPERATION);
			} else {
				logger.info(LoggerMessageConstant.AIRCRAFT_IS_NOT_DELETED);
				request.setAttribute(ConstantController.Attribute.RESULT_ATTR,
						ConstantController.Attribute.FAILED_OPERATION);
			}
			try {
				aircrafts = aircraftService.getAircraftrs();
			} catch (ServiceException e) {
				logger.error(LoggerMessageConstant.ERROR_GET_AIRCRAFTS, e);
				request.setAttribute(ConstantController.Attribute.ERROR, ConstantController.Attribute.SOMETHING_GOES_WRONG);
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
