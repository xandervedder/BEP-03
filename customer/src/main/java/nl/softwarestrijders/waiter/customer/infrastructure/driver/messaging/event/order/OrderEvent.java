package nl.softwarestrijders.waiter.customer.infrastructure.driver.messaging.event.order;

import java.util.UUID;

public record OrderEvent(String eventKey, UUID customerId, UUID orderId) {
}
