package nl.softwarestrijders.waiter.review.core.port.messaging;

import nl.softwarestrijders.waiter.review.core.domain.event.DomainEvent;

public interface ReviewEventPublisher {
    void publish(DomainEvent event);
}
