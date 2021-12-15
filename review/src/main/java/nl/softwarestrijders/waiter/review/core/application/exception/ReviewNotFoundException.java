package nl.softwarestrijders.waiter.review.core.application.exception;

import java.util.UUID;

public class ReviewNotFoundException extends RuntimeException {
    public ReviewNotFoundException(UUID reviewId) {
        super(String.format("Could not find review with id %s", reviewId));
    }
}
