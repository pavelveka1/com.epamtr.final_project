package by.epamtr.airline.service.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignInDataValidator {
	private static final String LOGIN_PATTERN = "[^$#№%,?&!\\s]{5,20}";
	private static final String PASSWORD_PATTERN = "([^$#№%,?&!\\.\\s]{5,15})";
	private static Pattern loginPattern = Pattern.compile(LOGIN_PATTERN);
	private static Pattern passwordPattern = Pattern.compile(PASSWORD_PATTERN);
	private static Matcher loginMatcher;
	private static Matcher passwordMatcher;

	public static boolean validate(String login, String password) {
		loginMatcher = loginPattern.matcher(login);
		passwordMatcher = passwordPattern.matcher(password);
		if (loginMatcher.matches()) {
			if (passwordMatcher.matches()) {
				return true;
			}
		}

		return false;
	}

}
