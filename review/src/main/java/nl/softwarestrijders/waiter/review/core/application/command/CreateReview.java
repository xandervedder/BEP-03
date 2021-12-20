package nl.softwarestrijders.waiter.review.core.application.command;

import java.util.UUID;

public record CreateReview(
        UUID customerId,
        UUID conceptId,
        String reviewType,
        String title,
        String description,
        int rating
) {
}
