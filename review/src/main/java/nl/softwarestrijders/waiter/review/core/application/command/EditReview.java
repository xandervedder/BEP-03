package nl.softwarestrijders.waiter.review.core.application.command;

import java.util.UUID;

public record EditReview(UUID reviewId, String title, String description, int rating) {
}
