package by.epamtr.airline.controller.command.impl;

import java.io.IOException;

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
import by.epamtr.airline.service.UserService;
import by.epamtr.airline.service.exception.ServiceException;
import by.epamtr.airline.service.validator.AircraftValidation;

public class AddAircraftTypeCommand implements Command {
	private static final Logger LOGGER = Logger.getLogger(AddAircraftTypeCommand.class);
	
		@Override
		public void execute(HttpServletRequest request, HttpServletResponse response)  {
			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			AircraftService aircraftService = serviceFactory.getAircraftService();
			String page=request.getParameter(ConstantController.Parameter.AIRCRAFT_TYPE);
			request.setAttribute(ConstantController.Attribute.CURRENT_PAGE, ConstantController.PathToPage.PATH_TO_ADD_AIRCRAFT_TYPE);
			if(page!=null) {
				try {
					String aircraftType = request.getParameter(ConstantController.Parameter.AIRCRAFT_TYPE);
					int flightRange = Integer.parseInt(request.getParameter(ConstantController.Parameter.RANGE_FLIGHT));
					int numberPassenger = Integer.parseInt(request.getParameter(ConstantController.Parameter.NUMBER_PASSENGERS));
					AircraftType typeAircraft=new AircraftType();
					typeAircraft.setAircraftType(aircraftType);
					typeAircraft.setRangeFlight(flightRange);
					typeAircraft.setNumberPassenger(numberPassenger);
					boolean dataIsValid=true;
					if(!AircraftValidation.validateAircraftType(aircraftType)) {
						request.setAttribute(ConstantController.Attribute.AIRCRAFT_TYPE_VALID, false);
						dataIsValid=false;
					}
					if(!AircraftValidation.validateNumberPassenger(numberPassenger)) {
						request.setAttribute(ConstantController.Attribute.PASSENGER_NUMBER_VALID, false);
						dataIsValid=false;
					}
					if(!AircraftValidation.validateFlightRange(flightRange)) {
						request.setAttribute(ConstantController.Attribute.FLIGHT_RANGE_VALID, false);
						dataIsValid=false;
					}
					if(dataIsValid==true) {
						boolean result=aircraftService.addAircraftType(typeAircraft);
						if (result) {
							LOGGER.info(LoggerMessageConstant.AIRCRAFT_TYPE_IS_ADDED);
							request.setAttribute(ConstantController.Attribute.RESULT_ATTR, ConstantController.Attribute.SUCCESSFUL_OPERATION);
						} else {
							LOGGER.info(LoggerMessageConstant.AIRCRAFT_TYPE_IS_NOT_ADDED);
							request.setAttribute(ConstantController.Attribute.RESULT_ATTR, ConstantController.Attribute.FAILED_OPERATION);
						}
					}
				
					request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request, response);
				} catch (ServiceException | ServletException | IOException e2) {
					LOGGER.error(LoggerMessageConstant.ERROR_ADD_AIRCRAFT_TYPE, e2);
					e2.printStackTrace();
				}
			}else {
				LOGGER.info(LoggerMessageConstant.GO_TO_ADD_AIRCRAFT_TYPE);
				try {
					request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request, response);
				} catch (ServletException | IOException e) {
					LOGGER.error(LoggerMessageConstant.ERROR_GO_TO_MAIN_PAGE, e);
				}
			}
			
			
		}
	

}
