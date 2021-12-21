package nl.softwarestrijders.waiter.customer.core.application.exception;

import java.util.UUID;

public class OrderNotFoundException extends RuntimeException {
	public OrderNotFoundException(UUID id) {
		super(String.format("Order with id: %s has not been found!", id.toString()));
	}
}
