package nl.softwarestrijders.waiter.product.infrastructure.driver.messaging;

import java.util.UUID;

public record DeleteProductCommand(UUID id) {
}
