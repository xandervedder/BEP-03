package nl.softwarestrijders.waiter.delivery.core.domain.exceptions;

public class InvalidPostalCodeException extends RuntimeException {
    public InvalidPostalCodeException() {
        super("Postal code should be 4 digits and 2 letters");
    }
}
