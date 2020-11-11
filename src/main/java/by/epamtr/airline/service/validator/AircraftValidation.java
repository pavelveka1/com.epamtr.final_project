package by.epamtr.airline.service.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AircraftValidation {
	private static final String REGISTRATION_NUMBER_PATTERN="([A-Z]{1,5})-([A-Z0-9]{1,7})";
	private static final String AIRCRAFT_TYPE_PATTERN="([A-Za-z]{1,10})([-\\s])([0-9]{1,5})";
	private static final String NUMBER_PASSENGER_PATTERN="([1-9])([0-9]{1,2})";
	private static final String FLIGHT_RANGE_PATTERN="([1-9])([0-9]{3})";
	
	private static Pattern registrationNumberPattern=Pattern.compile(REGISTRATION_NUMBER_PATTERN);
	private static Pattern aircraftTypePattern=Pattern.compile(AIRCRAFT_TYPE_PATTERN);
	private static Pattern numberPassengerPattern=Pattern.compile(NUMBER_PASSENGER_PATTERN);
	private static Pattern flightRangePattern=Pattern.compile(FLIGHT_RANGE_PATTERN);
	
	private static Matcher matcher;
	
	public static boolean validateRegistrationNumber(String registrationNumber) {
		matcher=registrationNumberPattern.matcher(registrationNumber);
		 if(matcher.matches()) {
			 return true;
		 }
		 return false;
	}
	
	public static boolean validateAircraftType(String aircraftType) {
		matcher=aircraftTypePattern.matcher(aircraftType);
		if(matcher.matches()) {
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean validateNumberPassenger(String numberPassenger) {
		matcher=numberPassengerPattern.matcher(numberPassenger);
		if(matcher.matches()) {
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean validateFlightRange(String flightRange) {
		matcher=flightRangePattern.matcher(flightRange);
		if(matcher.matches()) {
			return true;
		}else {
			return false;
		}
	}

}
