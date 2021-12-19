package nl.softwarestrijders.waiter.delivery.infrastructure.driven.storage;

import java.util.UUID;

public record CustomerDeliveryAddress(
        UUID id,
        int houseNumber,
        String addition,
        String street,
        String postalCode,
        String city) {
}
