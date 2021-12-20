package nl.softwarestrijders.waiter.customer.infrastructure.driver.messaging.event.customer;

import java.util.UUID;

public record ReviewCreatedEvent(UUID customerId, UUID reviewId) {
}
