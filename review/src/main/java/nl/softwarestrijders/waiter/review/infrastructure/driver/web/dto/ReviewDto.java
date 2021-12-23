package nl.softwarestrijders.waiter.review.infrastructure.driver.web.dto;

import java.util.UUID;

public record ReviewDto(
        UUID id,
        UUID conceptId,
        UUID customerId,
        String reviewType,
        String title,
        String description,
        int rating
) {
}
