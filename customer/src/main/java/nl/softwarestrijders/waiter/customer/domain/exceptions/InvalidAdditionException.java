package nl.softwarestrijders.waiter.customer.domain.exceptions;

/**
 * Exception that is thrown when the given addition doesn't comply with the set boundaries for additions
 *
 * @see nl.softwarestrijders.waiter.customer.domain.Address#setAddition(String) addition
 */
public class InvalidAdditionException extends RuntimeException {
	public InvalidAdditionException() {
		super("Addition must consist of numbers and letters only");
	}
}
