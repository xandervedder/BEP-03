package nl.softwarestrijders.waiter.customer.infrastructure.driver.messaging.event.review;

import java.util.UUID;

public record ReviewEvent(UUID customerId, UUID reviewId, String type) {
}
