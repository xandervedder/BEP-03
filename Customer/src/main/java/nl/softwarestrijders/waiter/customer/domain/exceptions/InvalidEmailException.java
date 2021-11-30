package nl.softwarestrijders.waiter.customer.domain.exceptions;

/**
 * Exception that is thrown if the email address contains invalid characters or if the composition contains failures.
 *
 * @see nl.softwarestrijders.waiter.customer.domain.Customer#setEmail (String) (String) email address
 */
public class InvalidEmailException extends RuntimeException {
	public InvalidEmailException() {
		super("Email address composition is not valid");
	}
}
