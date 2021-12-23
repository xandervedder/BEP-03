package nl.softwarestrijders.waiter.customer.core.domain.exceptions;

import java.util.UUID;

public class ReviewNotFoundException extends RuntimeException {
    public ReviewNotFoundException(UUID id) {
        super(String.format("Review with id %s could not be found", id));
    }
}
