package nl.softwarestrijders.waiter.review.infrastructure.driver.messaging.event;

import java.util.UUID;

public record DeleteEvent(UUID reviewId, UUID customerId) {
}
