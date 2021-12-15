package nl.softwarestrijders.waiter.review.infrastructure.driver.messaging.event;

import java.util.UUID;

public record EditEvent(UUID reviewId, String title, String description, int rating) {
}
