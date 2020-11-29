package by.epamtr.airline.controller.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidation {
	private static final String LOGIN_PATTERN = "([A-Za-z0-9-_]){5,20}";
	private static final String PASSWORD_PATTERN = "([A-Za-z0-9_-]){5,15}";
	private static final String NAME_PATTERN = "([A-ZА-Я])([a-zа-я]{1,15})";
	private static final String EMAIL_PATTERN = "([a-z0-9_-]{1,15})@([a-z0-9_-]{1,10})(\\.([a-z]{2,6}))";
	private static Pattern loginPattern = Pattern.compile(LOGIN_PATTERN);
	private static Pattern passwordPattern = Pattern.compile(PASSWORD_PATTERN);
	private static Pattern namePattern = Pattern.compile(NAME_PATTERN);
	private static Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);
	private static Matcher matcher;

	public static boolean loginValidation(String login) {
		matcher = loginPattern.matcher(login);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	public static boolean passwordValidation(String password) {
		matcher = passwordPattern.matcher(password);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	public static boolean nameValidation(String name) {
		matcher = namePattern.matcher(name);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	public static boolean emailValidation(String email) {
		matcher = emailPattern.matcher(email);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}
}
