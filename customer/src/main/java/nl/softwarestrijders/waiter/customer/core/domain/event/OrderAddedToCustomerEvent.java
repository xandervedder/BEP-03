package nl.softwarestrijders.waiter.customer.core.domain.event;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public record OrderAddedToCustomerEvent(UUID customerId, UUID orderId) implements CustomerDomainEvent {
	private static final String ROUTING_KEY = "customer.order.added";

	@Override
	@JsonIgnore
	public String key() {
		return null;
	}
}
