package nl.softwarestrijders.waiter.customer.core.domain.exceptions;


/**
 * Exception that is thrown if the name contains invalid characters
 *
 * @see nl.softwarestrijders.waiter.customer.core.domain.Customer customer
 */
public class InvalidNameException extends RuntimeException {
	public InvalidNameException(char c) {
		super(String.format("Name contains invalid character: \"%c\"", c));
	}
}
