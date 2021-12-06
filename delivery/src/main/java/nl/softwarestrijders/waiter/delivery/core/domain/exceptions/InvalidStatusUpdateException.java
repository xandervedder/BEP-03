package nl.softwarestrijders.waiter.delivery.core.domain.exceptions;

public class InvalidStatusUpdateException extends RuntimeException {
    public InvalidStatusUpdateException() {
        super("Cannot update status when order is already delivered or failed");
    }
}
