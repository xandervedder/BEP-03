package nl.softwarestrijders.waiter.customer.core.application.query;

import java.util.UUID;

public class GetEmailAddressFromCustomer {
	private final UUID id;

	public GetEmailAddressFromCustomer(UUID id) {
		this.id = id;
	}

	public UUID getId() {
		return id;
	}
}
