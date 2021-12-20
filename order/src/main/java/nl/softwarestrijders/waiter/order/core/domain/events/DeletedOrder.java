package nl.softwarestrijders.waiter.order.core.domain.events;

import nl.softwarestrijders.waiter.order.core.domain.Order;

import java.util.UUID;

public class DeletedOrder extends OrderEvent {
    private final Order order;

    public DeletedOrder(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

    @Override
    public String getEventKey() {
        return "order.deleted";
    }
}
