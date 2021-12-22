package nl.softwarestrijders.waiter.customer.infrastructure.driver.web.dto;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public record CustomerDto(
        UUID id,
        String firstName,
        String lastName,
        String email,
        AddressDto address,
        List<UUID> orders,
        Map<UUID, String> reviews,
        List<UUID> deliveries
) {
}
