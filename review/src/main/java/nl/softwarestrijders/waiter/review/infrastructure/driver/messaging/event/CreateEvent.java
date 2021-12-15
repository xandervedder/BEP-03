package nl.softwarestrijders.waiter.review.infrastructure.driver.messaging.event;

import java.util.UUID;

public record CreateEvent(
        UUID conceptId,
        UUID customerId,
        String reviewType,
        String title,
        String description,
        int rating
) {
}
