package nl.softwarestrijders.waiter.customer.infrastructure.driver.messaging.event;

import java.util.UUID;

public record OrderEvent(UUID customer, UUID order) {
}
