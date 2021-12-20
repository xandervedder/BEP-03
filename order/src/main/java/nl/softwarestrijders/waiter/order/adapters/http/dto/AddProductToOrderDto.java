package nl.softwarestrijders.waiter.order.adapters.http.dto;

import java.util.UUID;

public record AddProductToOrderDto(
        UUID orderId,
        UUID productId,
        int amount) {
}
