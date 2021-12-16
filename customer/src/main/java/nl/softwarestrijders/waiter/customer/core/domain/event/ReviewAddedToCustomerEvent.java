package nl.softwarestrijders.waiter.customer.core.domain.event;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public record ReviewAddedToCustomerEvent(UUID customerId, UUID reviewId, String type) implements CustomerDomainEvent {
	private static final String ROUTING_KEY = "customer.review.created";

	@Override
	@JsonIgnore
	public String key() {
		return ROUTING_KEY;
	}
}
