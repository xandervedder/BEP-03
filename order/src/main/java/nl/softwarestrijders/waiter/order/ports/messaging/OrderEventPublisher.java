package nl.softwarestrijders.waiter.order.ports.messaging;

import nl.softwarestrijders.waiter.order.core.domain.events.OrderEvent;

public interface OrderEventPublisher {
    void publish(OrderEvent event);
}
