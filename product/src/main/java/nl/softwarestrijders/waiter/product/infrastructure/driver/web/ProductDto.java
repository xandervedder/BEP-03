package nl.softwarestrijders.waiter.product.infrastructure.driver.web;

import java.util.UUID;

public record ProductDto(
        UUID id,
        double price,
        String name,
        String description,
        int weight,
        NutritionalValueDto nutritionalValueDto
) {
}
