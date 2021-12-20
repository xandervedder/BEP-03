package nl.softwarestrijders.waiter.customer.core.domain.event;

import java.util.UUID;

public record ReviewAddedToCustomerEvent(UUID customerId, UUID reviewId, String type) {
}
