package nl.softwarestrijders.waiter.review.core.port.messaging;

import nl.softwarestrijders.waiter.review.core.domain.event.ReviewEvent;

public interface ReviewEventPublisher {
    void publish(ReviewEvent event);
}
