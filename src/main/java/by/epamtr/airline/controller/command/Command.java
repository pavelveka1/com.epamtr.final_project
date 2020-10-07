package by.epamtr.airline.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public interface Command {
	String FULL_FORM="fullForm";
	String EMPTY_FORM="emptyForm";
	Logger rootLogger = LogManager.getRootLogger();
	 void execute(HttpServletRequest request, HttpServletResponse response);


}
