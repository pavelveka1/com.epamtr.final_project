package by.epamtr.airline.controller;

import java.util.HashMap;
import java.util.Map;

import by.epamtr.airline.controller.command.Command;
import by.epamtr.airline.controller.command.CommandName;
import by.epamtr.airline.controller.command.impl.AddAircraftCommand;
import by.epamtr.airline.controller.command.impl.AddAircraftTypeCommand;
import by.epamtr.airline.controller.command.impl.AddCrewToFlightCommand;
import by.epamtr.airline.controller.command.impl.AddFlightCommand;
import by.epamtr.airline.controller.command.impl.AddUserCommand;
import by.epamtr.airline.controller.command.impl.ChangeAircraftStatusCommand;
import by.epamtr.airline.controller.command.impl.ChangeFlightStatusCommand;
import by.epamtr.airline.controller.command.impl.DeleteAircraftCommand;
import by.epamtr.airline.controller.command.impl.DeleteAircraftTypeCommand;
import by.epamtr.airline.controller.command.impl.DeleteCrewFromFlightCommand;
import by.epamtr.airline.controller.command.impl.DeleteFlightCommand;
import by.epamtr.airline.controller.command.impl.DeliteUserCommand;
import by.epamtr.airline.controller.command.impl.GetFlightsByStatusCommand;
import by.epamtr.airline.controller.command.impl.GetFlightsByUser;
import by.epamtr.airline.controller.command.impl.GetUserCommand;
import by.epamtr.airline.controller.command.impl.GetUsersByFlightIdCommand;
import by.epamtr.airline.controller.command.impl.GetUsersByRoleCommand;
import by.epamtr.airline.controller.command.impl.GetUsersCommand;
import by.epamtr.airline.controller.command.impl.GoToMainPage;
import by.epamtr.airline.controller.command.impl.GoToLoginPage;
import by.epamtr.airline.controller.command.impl.SignIn;
import by.epamtr.airline.controller.command.impl.SignOut;
import by.epamtr.airline.controller.command.impl.UpdateAircraftCommand;
import by.epamtr.airline.controller.command.impl.UpdateFlightCommand;
import by.epamtr.airline.controller.command.impl.UpdateUserCommand;

public class CommandProvider {
	private static final CommandProvider instance = new CommandProvider();

	private final Map<CommandName, Command> commands = new HashMap<>();

	private CommandProvider() {
		commands.put(CommandName.SIGN_IN, new SignIn());
		commands.put(CommandName.SIGN_OUT, new SignOut());

		commands.put(CommandName.GO_TO_LOGIN_PAGE, new GoToLoginPage());
		commands.put(CommandName.GO_TO_MAIN_PAGE, new GoToMainPage());
		commands.put(CommandName.WRONG_REQUEST, new GoToLoginPage());

		commands.put(CommandName.ADD_USER, new AddUserCommand());
		commands.put(CommandName.DELITE_USER, new DeliteUserCommand());
		commands.put(CommandName.UPDATE_USER, new UpdateUserCommand());
		commands.put(CommandName.GET_USER, new GetUserCommand());
		commands.put(CommandName.GET_USERS, new GetUsersCommand());

		commands.put(CommandName.GET_USERS_BY_ROLE, new GetUsersByRoleCommand());
		commands.put(CommandName.GET_USERS_BY_FLIGHT_ID, new GetUsersByFlightIdCommand());
		commands.put(CommandName.ADD_CREW_TO_FLIGHT, new AddCrewToFlightCommand());
		commands.put(CommandName.DELETE_CREW_FROM_FLIGHT, new DeleteCrewFromFlightCommand());

		commands.put(CommandName.ADD_FLIGHT, new AddFlightCommand());
		commands.put(CommandName.DELETE_FLIGHT, new DeleteFlightCommand());
		commands.put(CommandName.UPDATE_FLIGHT, new UpdateFlightCommand());
		commands.put(CommandName.GET_FLIGHTS_BY_STATUS, new GetFlightsByStatusCommand());
		commands.put(CommandName.GET_FLIGHTS_BY_USER, new GetFlightsByUser());
		commands.put(CommandName.CHANGE_FLIGHT_STATUS, new ChangeFlightStatusCommand());

		commands.put(CommandName.ADD_AIRCRAFT, new AddAircraftCommand());
		commands.put(CommandName.DELETE_AIRCRAFT, new DeleteAircraftCommand());
		commands.put(CommandName.UPDATE_AIRCRAFT, new UpdateAircraftCommand());
		commands.put(CommandName.CHANGE_STATUS_AIRCRAFT, new ChangeAircraftStatusCommand());
		commands.put(CommandName.ADD_AIRCRAFT_TYPE, new AddAircraftTypeCommand());
		commands.put(CommandName.DELETE_AIRCRAFT_TYPE, new DeleteAircraftTypeCommand());

	}

	public static CommandProvider getInstance() {
		return instance;
	}

	public Command getCommand(String command) {
		CommandName commandName = null;
		try {
			commandName = CommandName.valueOf(command.toUpperCase());
		} catch (IllegalArgumentException | NullPointerException e) {
			return commands.get(CommandName.WRONG_REQUEST);
		}
		return commands.get(commandName);
	}

}
