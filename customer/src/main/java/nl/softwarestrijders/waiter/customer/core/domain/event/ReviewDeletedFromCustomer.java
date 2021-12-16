package nl.softwarestrijders.waiter.customer.core.domain.event;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public record ReviewDeletedFromCustomer(UUID customerId, UUID reviewId) implements CustomerDomainEvent {
	private static final String ROUTING_KEY = "customer.review.deleted";

	@Override
	@JsonIgnore
	public String key() {
		return ROUTING_KEY;
	}
}
