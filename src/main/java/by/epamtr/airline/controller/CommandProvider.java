package by.epamtr.airline.controller;

import java.util.HashMap;
import java.util.Map;

import by.epamtr.airline.controller.command.Command;
import by.epamtr.airline.controller.command.CommandName;
import by.epamtr.airline.controller.command.impl.AddUserCommand;
import by.epamtr.airline.controller.command.impl.DeliteUserCommand;
import by.epamtr.airline.controller.command.impl.GetUserCommand;
import by.epamtr.airline.controller.command.impl.GetUsersCommand;
import by.epamtr.airline.controller.command.impl.GoToAdministratorPage;
import by.epamtr.airline.controller.command.impl.GoToCrewPage;
import by.epamtr.airline.controller.command.impl.GoToDispatcherPage;
import by.epamtr.airline.controller.command.impl.GoToLoginPage;
import by.epamtr.airline.controller.command.impl.GoToManagerPage;
import by.epamtr.airline.controller.command.impl.SignIn;
import by.epamtr.airline.controller.command.impl.SignOut;
import by.epamtr.airline.controller.command.impl.UpdateUserCommand;

public class CommandProvider {
	private static final CommandProvider instance = new CommandProvider();

	private final Map<CommandName, Command> commands = new HashMap<>();

	private CommandProvider() {
		commands.put(CommandName.SIGN_IN, new SignIn());
		commands.put(CommandName.SIGN_OUT, new SignOut());
		
		commands.put(CommandName.GO_TO_LOGIN_PAGE, new GoToLoginPage());
		commands.put(CommandName.GO_TO_CREW_PAGE, new GoToCrewPage());
		commands.put(CommandName.GO_TO_ADMINISTRATOR_PAGE, new GoToAdministratorPage());
		commands.put(CommandName.GO_TO_DISPATCHER_PAGE, new GoToDispatcherPage());
		commands.put(CommandName.GO_TO_MANAGER_PAGE, new GoToManagerPage());
		commands.put(CommandName.WRONG_REQUEST, new GoToLoginPage());
		
		commands.put(CommandName.ADD_USER, new AddUserCommand());
		commands.put(CommandName.DELITE_USER, new DeliteUserCommand());
		commands.put(CommandName.UPDATE_USER, new UpdateUserCommand());
		commands.put(CommandName.GET_USER, new GetUserCommand());
		commands.put(CommandName.GET_USERS, new GetUsersCommand());
		
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
