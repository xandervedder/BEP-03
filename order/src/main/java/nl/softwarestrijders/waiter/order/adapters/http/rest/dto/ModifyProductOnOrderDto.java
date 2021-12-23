package nl.softwarestrijders.waiter.order.adapters.http.rest.dto;

import java.util.UUID;

public record ModifyProductOnOrderDto(int amount, UUID productId) {
}
