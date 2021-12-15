package nl.softwarestrijders.waiter.review.core.application.exception;

import java.util.UUID;

public class AlreadyReviewedException extends RuntimeException {
    public AlreadyReviewedException(UUID conceptId, UUID customerId) {
        super(String.format("Concept with id %s is already reviewed by customer %s", conceptId, customerId));
    }
}
