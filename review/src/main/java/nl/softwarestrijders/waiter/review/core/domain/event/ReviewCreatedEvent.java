package nl.softwarestrijders.waiter.review.core.domain.event;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public record ReviewCreatedEvent(UUID reviewId, UUID customerId, String type) implements DomainEvent {
    private static final String ROUTING_KEY = "events.review.created";

    @Override
    @JsonIgnore
    public String key() {
        return ROUTING_KEY;
    }
}
