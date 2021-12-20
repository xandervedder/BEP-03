package nl.softwarestrijders.waiter.order.core.domain.events;

import java.util.UUID;

public class CreatedOrder extends OrderEvent {
    private final UUID order;

    public CreatedOrder(UUID order) {
        this.order = order;
    }

    public UUID getOrder() {
        return order;
    }

    @Override
    public String getEventKey() {
        return "order.created";
    }
}
