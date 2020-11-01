package by.epamtr.airline.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtr.airline.controller.ConstantController;
import by.epamtr.airline.controller.command.Command;
import by.epamtr.airline.entity.AircraftType;
import by.epamtr.airline.service.AircraftService;
import by.epamtr.airline.service.ServiceFactory;
import by.epamtr.airline.service.UserService;
import by.epamtr.airline.service.exception.ServiceException;
import by.epamtr.airline.service.validator.AircraftValidation;

public class AddAircraftTypeCommand implements Command {

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
					if(AircraftValidation.validateAircraftType(aircraftType)) {
						if(AircraftValidation.validateNumberPassenger(numberPassenger)) {
							if(AircraftValidation.validateFlightRange(flightRange)) {
								boolean result=aircraftService.addAircraftType(typeAircraft);
								if (result) {
									request.setAttribute(ConstantController.Attribute.RESULT_ATTR, ConstantController.Attribute.SUCCESSFUL_OPERATION);
								} else {
									request.setAttribute(ConstantController.Attribute.RESULT_ATTR, ConstantController.Attribute.FAILED_OPERATION);
								}
							}else {
								request.setAttribute(ConstantController.Attribute.FLIGHT_RANGE_VALID, false);
							}
						}else {
							request.setAttribute(ConstantController.Attribute.PASSENGER_NUMBER_VALID, false);
						}
	
					}else {
						request.setAttribute(ConstantController.Attribute.AIRCRAFT_TYPE_VALID, false);
					}
				
					request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request, response);
				} catch (ServiceException | ServletException | IOException e2) {
				//	rootLogger.error(e2);
					e2.printStackTrace();
				}
			}else {
				try {
					request.getRequestDispatcher(ConstantController.PathToPage.PATH_TO_MAIN_PAGE).forward(request, response);
				} catch (ServletException | IOException e) {
					rootLogger.error(e);
				}
			}
			
			
		}

}
