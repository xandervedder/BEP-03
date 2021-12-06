package nl.softwarestrijders.waiter.delivery.core.domain.exceptions;

public class InvalidHouseNumberException extends RuntimeException {
    public InvalidHouseNumberException() {
        super("House number should be 1 or higher");
    }
}
