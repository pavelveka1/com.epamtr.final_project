package by.epamtr.airline.service.validator;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FlightValidation {
	private static final String CITY_PATTERN="([A-Za-zА-Яа-я]{2,15})\\s?([-]?)\\s?([A-Za-zА-Яа-я]{2,15})";
	private static final String FLIGHT_TIME_PATTERN="([1-9])([0-9]{1,2})";
	private static final String FLIGHT_RANGE_PATTERN="([1-9])([0-9]{2,3})";
	
	private static Pattern cityPattern=Pattern.compile(CITY_PATTERN);
	private static Pattern flightTimePattern=Pattern.compile(FLIGHT_TIME_PATTERN);
	private static Pattern flightRangePattern=Pattern.compile(FLIGHT_RANGE_PATTERN);
	
	private static Matcher matcher;
	

	public static boolean cityValidation(String cityName) {
		matcher=cityPattern.matcher(cityName);
		 if(matcher.matches()) {
			 return true;
		 }
		 return false;
	}
	
	public static boolean flightRangeValidation(int flightRange) {
		matcher=flightRangePattern.matcher(String.valueOf(flightRange));
		 if(matcher.matches()) {
			 return true;
		 }
		 return false;
	}
	
	public static boolean flightTimeValidation(int flightTime) {
		matcher=flightTimePattern.matcher(String.valueOf(flightTime));
		 if(matcher.matches()) {
			 return true;
		 }
		 return false;
	}
	
	public static boolean dateValidation(String date) {
		LocalDateTime currentDate= LocalDateTime.now();
		if(date=="") {
			return false;
		}
		LocalDateTime dateTime = LocalDateTime.parse(date);
		int result=currentDate.compareTo(dateTime);
		if(result>0) {
			return false;
		}else if(result<0||result==0) {
			return true;
		}
		return false;
	}

}
