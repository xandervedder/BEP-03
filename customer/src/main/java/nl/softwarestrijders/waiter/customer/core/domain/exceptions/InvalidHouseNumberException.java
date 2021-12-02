package nl.softwarestrijders.waiter.customer.core.domain.exceptions;

/**
 * Exception that is thrown when the given house number doens't comply with the set house number boundaries
 *
 * @see nl.softwarestrijders.waiter.customer.core.domain.Address#setHouseNumber(int) houseNumber
 */
public class InvalidHouseNumberException extends RuntimeException {
	public InvalidHouseNumberException() {
		super("HouseNumber must consist of (positive) numbers only");
	}
}
