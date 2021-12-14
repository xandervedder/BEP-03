package nl.softwarestrijders.waiter.customer.infrastructure.driver.messaging.event.customer;

import java.util.UUID;

public record AddedReviewToCustomerEvent(UUID customerId, UUID reviewId) {
}
