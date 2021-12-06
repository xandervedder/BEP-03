package nl.softwarestrijders.waiter.delivery.core.domain.exceptions;

public class InvalidStreetNameException extends RuntimeException {
    public InvalidStreetNameException() {
        super("Street name should at least be 1 character");
    }
}
