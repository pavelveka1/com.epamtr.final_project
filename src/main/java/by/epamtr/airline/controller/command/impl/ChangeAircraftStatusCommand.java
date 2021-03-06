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

public class ChangeAircraftStatusCommand implements Command {
	private static final Logger logger = Logger.getLogger(ChangeAircraftStatusCommand.class);
	private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private final AircraftService aircraftService = serviceFactory.getAircraftService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String idAircraftParam = request.getParameter(ConstantController.Parameter.ID_AIRCRAFT);
		List<Aircraft> aircrafts = new ArrayList<Aircraft>();
		request.getSession().setAttribute(ConstantController.Attribute.CURRENT_PAGE,
				ConstantController.PathToPage.PATH_TO_CHANGE_AIRCRAFT_STATUS);
		if (idAircraftParam == null) {
			logger.info(LoggerMessageConstant.GO_TO_CHANGE_AIRCRAFT_STATUS_PAGE);
			try {
				aircrafts = aircraftService.getAircraftrs();
			} catch (ServiceException e) {
				logger.error(LoggerMessageConstant.ERROR_GET_AIRCRAFTS, e);
				request.setAttribute(ConstantController.Attribute.ERROR, ConstantController.Attribute.SOMETHING_GOES_WRONG);
			}
			request.setAttribute(ConstantController.Attribute.AIRCRAFTS, aircrafts);
		} else {

			String status = request.getParameter(ConstantController.Parameter.AIRCRAFT_STATUS);
			int idAircraft = Integer.parseInt(idAircraftParam);
			boolean result = false;
			try {
				result = aircraftService.changeAircraftStatus(idAircraft, status);
			} catch (ServiceException e) {
				logger.error(LoggerMessageConstant.ERROR_CHANGE_AIRCRAFT_STATUS, e);
				request.setAttribute(ConstantController.Attribute.ERROR, ConstantController.Attribute.SOMETHING_GOES_WRONG);
			}
			if (result) {
				logger.info(LoggerMessageConstant.AIRCRAFT_STATUS_CHANGED);
			} else {
				logger.info(LoggerMessageConstant.AIRCRAFT_STATUS_NOT_CHANGED);
			}
			try {
				aircrafts = aircraftService.getAircraftrs();
			} catch (ServiceException e) {
				logger.error(LoggerMessageConstant.ERROR_GET_AIRCRAFTS, e);
				request.setAttribute(ConstantController.Attribute.ERROR, ConstantController.Attribute.SOMETHING_GOES_WRONG);
			}
			request.setAttribute(ConstantController.Attribute.AIRCRAFTS, aircrafts);

		}
		try {
			request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request, response);
		} catch (ServletException | IOException e) {
			logger.error(LoggerMessageConstant.ERROR_GO_TO_MAIN_PAGE, e);
		}
	}

}
