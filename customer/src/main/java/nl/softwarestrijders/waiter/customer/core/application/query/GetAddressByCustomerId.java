package nl.softwarestrijders.waiter.customer.core.application.query;

import java.util.UUID;

public class GetAddressByCustomerId {
	private final UUID id;

	public GetAddressByCustomerId(UUID id) {
		this.id = id;
	}

	public UUID getId() {
		return id;
	}
}
