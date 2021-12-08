package nl.softwarestrijders.waiter.product.core.port.messaging;

import nl.softwarestrijders.waiter.product.core.domain.event.ProductEvent;

public interface ProductEventPublisher {
    void publish(ProductEvent event);
}
