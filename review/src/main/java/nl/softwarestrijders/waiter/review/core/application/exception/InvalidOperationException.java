package nl.softwarestrijders.waiter.review.core.application.exception;

public class InvalidOperationException extends RuntimeException {
    public InvalidOperationException() {
        super("Operation invalid");
    }
}
