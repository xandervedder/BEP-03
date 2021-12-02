package nl.softwarestrijders.waiter.order.common.exception;

public class InvalidModificationException extends RuntimeException {

    public InvalidModificationException(String errorMessage) {
        super(errorMessage);
    }
}
