package nl.softwarestrijders.waiter.review.core.domain.event;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public record ReviewDeletedEvent(UUID reviewId, UUID customerId) implements DomainEvent {
    private static final String ROUTING_KEY = "events.review.deleted";

    @Override
    @JsonIgnore
    public String key() {
        return ROUTING_KEY;
    }
}
