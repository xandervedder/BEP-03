package nl.softwarestrijders.waiter.order.ports.messaging;

public interface OrderEventPublisher {
    void publish(OutgoingOrderEvent event);
}
