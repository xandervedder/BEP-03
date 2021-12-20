package nl.softwarestrijders.waiter.customer.core.domain.event;

import java.util.UUID;

public record OrderAddedToCustomerEvent(UUID customerId, UUID orderId) {
}
