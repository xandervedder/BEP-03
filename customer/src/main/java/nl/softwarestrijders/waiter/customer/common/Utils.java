package nl.softwarestrijders.waiter.customer.common;

import java.util.regex.Pattern;

/**
 * Utility class with static methods to aid
 */
public class Utils {
	/**
	 * Function that checks if the email address given has a valid composition of characters.
	 *
	 * @param email email address
	 * @return if the email address has a valid composition.
	 */
	public static boolean isEmailValid(String email) {
		// REGEX for email checking
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

		var pattern = Pattern.compile(emailRegex);

		return pattern.matcher(email).matches();
	}
}
