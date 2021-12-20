package nl.softwarestrijders.waiter.product.core.domain.event;

import java.util.UUID;

public record ProductDeletedEvent(UUID id) implements ProductEvent {
    private static final String ROUTING_KEY = "events.product.deleted";

    @Override
    public String key() {
        return ROUTING_KEY;
    }
}
