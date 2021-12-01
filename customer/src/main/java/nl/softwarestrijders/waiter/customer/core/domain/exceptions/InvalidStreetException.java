package nl.softwarestrijders.waiter.customer.core.domain.exceptions;

/**
 * Exception that is thrown when the given street doesn't comply with the set boundaries for street naming
 *
 * @see nl.softwarestrijders.waiter.customer.core.domain.Address#setStreet(String) street
 */
public class InvalidStreetException extends RuntimeException {
	public InvalidStreetException(String specification) {
		super(specification);
	}
}
