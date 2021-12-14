package nl.softwarestrijders.waiter.customer.core.application.exception;

public class CustomerNotFoundException extends RuntimeException {
	public CustomerNotFoundException(String id) {
		super(String.format("Customer with id \"%s\" could not be found", id));
	}
}
