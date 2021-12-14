package nl.softwarestrijders.waiter.customer.core.application.query;

import java.util.UUID;

public class GetCustomerById {
	private final UUID id;

	public GetCustomerById(UUID id) {
		this.id = id;
	}

	public UUID getId() {
		return id;
	}
}
