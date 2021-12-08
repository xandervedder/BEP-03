package nl.softwarestrijders.waiter.delivery.core.port.messaging;

import nl.softwarestrijders.waiter.delivery.core.domain.event.DeliveryEvent;

public interface DeliveryEventPublisher {
    void publish(DeliveryEvent event);
}
