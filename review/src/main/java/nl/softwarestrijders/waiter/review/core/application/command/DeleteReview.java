package nl.softwarestrijders.waiter.review.core.application.command;

import java.util.UUID;

public record DeleteReview(UUID customerId, UUID reviewId) {
}
