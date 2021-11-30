package nl.softwarestrijders.waiter.customer.domain.exceptions;

/**
 * Exception that is thrown if the location name is not valid
 *
 * @see nl.softwarestrijders.waiter.customer.domain.Customer#setFirstName(String) (String) first name
 * @see nl.softwarestrijders.waiter.customer.domain.Customer#setLastName(String) (String) last name
 */
public class InvalidNameStartException extends RuntimeException {
	public InvalidNameStartException() {
		super("Name must start with a capital letter");
	}
}
