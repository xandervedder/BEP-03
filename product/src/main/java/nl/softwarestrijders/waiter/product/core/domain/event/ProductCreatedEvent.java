package nl.softwarestrijders.waiter.product.core.domain.event;

import nl.softwarestrijders.waiter.product.core.domain.Product;

public record ProductCreatedEvent(Product product) implements ProductEvent{
    private static final String ROUTING_KEY = "events.product.created";

    @Override
    public String key() {
        return ROUTING_KEY;
    }
}
