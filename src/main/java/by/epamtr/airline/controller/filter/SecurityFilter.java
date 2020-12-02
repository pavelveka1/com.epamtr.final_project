package by.epamtr.airline.controller.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.epamtr.airline.controller.ConstantController;
import by.epamtr.airline.controller.command.CommandName;
import by.epamtr.airline.entity.User;
import by.epamtr.airline.entity.UserRole;

/**
 * Servlet Filter implementation class SequrityFilter
 */
public class SecurityFilter implements Filter {
	private static final String COMMAND_PARAMETER = "command";
	private static final int PERMISSION_DENIED = 403;
	private static final Logger logger = Logger.getLogger(SecurityFilter.class);

	/**
	 * Map that stores the correspondence between the command and the user role of
	 * which the execution of this command is allowed
	 */
	private final Map<CommandName, UserRole[]> permissionMap = new HashMap<CommandName, UserRole[]>();

	/**
	 * Put commands and allowed roles in Map
	 *
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		permissionMap.put(CommandName.ADD_USER, new UserRole[] { UserRole.ADMINISTRATOR });
		permissionMap.put(CommandName.DELITE_USER, new UserRole[] { UserRole.ADMINISTRATOR });
		permissionMap.put(CommandName.UPDATE_USER, new UserRole[] { UserRole.ADMINISTRATOR });
		permissionMap.put(CommandName.ADD_CREW_TO_FLIGHT,
				new UserRole[] { UserRole.ADMINISTRATOR, UserRole.DISPATCHER });
		permissionMap.put(CommandName.DELETE_CREW_FROM_FLIGHT,
				new UserRole[] { UserRole.ADMINISTRATOR, UserRole.DISPATCHER });
		permissionMap.put(CommandName.GET_USERS, new UserRole[] { UserRole.ADMINISTRATOR, UserRole.DISPATCHER,
				UserRole.ATTENDANT, UserRole.ENGINEER, UserRole.MANAGER, UserRole.PILOT });
		permissionMap.put(CommandName.GET_USER, new UserRole[] { UserRole.ADMINISTRATOR, UserRole.DISPATCHER,
				UserRole.ATTENDANT, UserRole.ENGINEER, UserRole.MANAGER, UserRole.PILOT });
		permissionMap.put(CommandName.GET_USER_BY_LOGIN, new UserRole[] { UserRole.ADMINISTRATOR, UserRole.DISPATCHER,
				UserRole.ATTENDANT, UserRole.ENGINEER, UserRole.MANAGER, UserRole.PILOT });
		permissionMap.put(CommandName.GET_USERS_BY_ROLE, new UserRole[] { UserRole.ADMINISTRATOR, UserRole.DISPATCHER,
				UserRole.ATTENDANT, UserRole.ENGINEER, UserRole.MANAGER, UserRole.PILOT });
		permissionMap.put(CommandName.GET_USERS_BY_FLIGHT_ID, new UserRole[] { UserRole.ADMINISTRATOR,
				UserRole.DISPATCHER, UserRole.ATTENDANT, UserRole.ENGINEER, UserRole.MANAGER, UserRole.PILOT });

		permissionMap.put(CommandName.ADD_FLIGHT, new UserRole[] { UserRole.ADMINISTRATOR });
		permissionMap.put(CommandName.DELETE_FLIGHT, new UserRole[] { UserRole.ADMINISTRATOR, UserRole.DISPATCHER });
		permissionMap.put(CommandName.UPDATE_FLIGHT, new UserRole[] { UserRole.ADMINISTRATOR });
		permissionMap.put(CommandName.CHANGE_FLIGHT_STATUS,
				new UserRole[] { UserRole.ADMINISTRATOR, UserRole.DISPATCHER });
		permissionMap.put(CommandName.GET_FLIGHTS_BY_STATUS, new UserRole[] { UserRole.ADMINISTRATOR,
				UserRole.DISPATCHER, UserRole.ATTENDANT, UserRole.ENGINEER, UserRole.MANAGER, UserRole.PILOT });
		permissionMap.put(CommandName.GET_FLIGHTS_BY_USER, new UserRole[] { UserRole.ADMINISTRATOR, UserRole.DISPATCHER,
				UserRole.ATTENDANT, UserRole.ENGINEER, UserRole.MANAGER, UserRole.PILOT });

		permissionMap.put(CommandName.ADD_AIRCRAFT, new UserRole[] { UserRole.ADMINISTRATOR });
		permissionMap.put(CommandName.DELETE_AIRCRAFT, new UserRole[] { UserRole.ADMINISTRATOR });
		permissionMap.put(CommandName.UPDATE_AIRCRAFT, new UserRole[] { UserRole.ADMINISTRATOR });
		permissionMap.put(CommandName.CHANGE_STATUS_AIRCRAFT, new UserRole[] { UserRole.ADMINISTRATOR });
		permissionMap.put(CommandName.ADD_AIRCRAFT_TYPE, new UserRole[] { UserRole.ADMINISTRATOR });
		permissionMap.put(CommandName.DELETE_AIRCRAFT_TYPE, new UserRole[] { UserRole.ADMINISTRATOR });

	}

	/**
	 * Default constructor.
	 */
	public SecurityFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		String commandName = req.getParameter(COMMAND_PARAMETER);
		User user = (User) req.getSession(true).getAttribute(ConstantController.Attribute.SIGNED_IN_USER);

		CommandName command = parseCommand(commandName);

		if (isCommandNeedPermission(command) && !hasUserAllowedRole(command, user)) {
			logger.warn("Authorization fail: try opening private page by user without needed permission");
			resp.setStatus(PERMISSION_DENIED);
			return;
		}

		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	private CommandName parseCommand(String commandName) {
		try {
			return CommandName.valueOf(commandName.toUpperCase());
		} catch (IllegalArgumentException | NullPointerException e) {
			logger.warn("This command is not exist in enum CommandName: " + commandName);
			return null;
		}
	}

	private boolean isCommandNeedPermission(CommandName command) {
		return command != null && permissionMap.containsKey(command);
	}

	private boolean hasUserAllowedRole(CommandName command, User user) {
		if (user == null) {
			logger.warn("Authorization fail: attempt to open private page not authored user");
			return false;
		}
		boolean hasAllowedRole = false;
		UserRole[] allowedRoles = permissionMap.get(command);
		for (UserRole allowedRole : allowedRoles) {
			if (allowedRole == user.getRole()) {
				hasAllowedRole = true;
				break;
			}
		}

		return hasAllowedRole;
	}
}
