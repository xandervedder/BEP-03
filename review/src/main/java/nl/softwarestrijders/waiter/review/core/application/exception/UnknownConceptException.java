package nl.softwarestrijders.waiter.review.core.application.exception;

import java.util.UUID;

public class UnknownConceptException extends RuntimeException {
    public UnknownConceptException(UUID conceptId) {
        super(String.format("Could not find concept with id %s", conceptId));
    }
}
