package nl.softwarestrijders.waiter.customer.core.domain.event;

import java.util.UUID;

public record ReviewDeletedFromCustomer(UUID customerId, UUID reviewId) {
}
