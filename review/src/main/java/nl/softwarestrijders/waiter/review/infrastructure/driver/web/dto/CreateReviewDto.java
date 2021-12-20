package nl.softwarestrijders.waiter.review.infrastructure.driver.web.dto;

import java.util.UUID;

public record CreateReviewDto(UUID customerId, UUID conceptId, String type, String title, String description, int rating) {
}
