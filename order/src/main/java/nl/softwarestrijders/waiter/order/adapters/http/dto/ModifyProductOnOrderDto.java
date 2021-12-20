package nl.softwarestrijders.waiter.order.adapters.http.dto;

import java.util.UUID;

public record ModifyProductOnOrderDto(UUID productId, int amount) {
}
