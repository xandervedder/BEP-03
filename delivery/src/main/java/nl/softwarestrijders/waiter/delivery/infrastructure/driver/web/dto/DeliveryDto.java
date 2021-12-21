package nl.softwarestrijders.waiter.delivery.infrastructure.driver.web.dto;

import nl.softwarestrijders.waiter.delivery.core.domain.DeliveryAddress;
import nl.softwarestrijders.waiter.delivery.core.domain.Status;

import java.util.UUID;

public record DeliveryDto(
        UUID id,
        DeliveryAddress address,
        Status status,
        UUID orderId
) {
}
