package nl.softwarestrijders.waiter.order.core.domain.events;

import java.util.UUID;

public class OrderDeleted extends OrderEvent {
    private final UUID order;
    private final UUID customer;

    public OrderDeleted(UUID order, UUID customer) {
        this.order = order;
        this.customer = customer;
    }

    public UUID getOrder() {
        return order;
    }

    public UUID getCustomer() {
        return customer;
    }

    @Override
    public String getEventKey() {
        return "order.deleted";
    }
}
