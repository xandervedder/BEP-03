package nl.softwarestrijders.waiter.customer.core.application;

import nl.softwarestrijders.waiter.customer.core.application.command.RegisterCustomer;
import nl.softwarestrijders.waiter.customer.core.application.exception.CustomerNotFoundException;
import nl.softwarestrijders.waiter.customer.core.domain.Address;
import nl.softwarestrijders.waiter.customer.core.domain.Customer;
import nl.softwarestrijders.waiter.customer.core.domain.event.CustomerDomainEvent;
import nl.softwarestrijders.waiter.customer.core.port.messaging.CustomerEventPublisher;
import nl.softwarestrijders.waiter.customer.core.port.storage.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerCommandHandler {
	private final CustomerRepository customerRepository;
	private final CustomerEventPublisher eventPublisher;

	public CustomerCommandHandler(CustomerRepository customerRepository, CustomerEventPublisher eventPublisher) {
		this.customerRepository = customerRepository;
		this.eventPublisher = eventPublisher;
	}

	public Customer handleRegisterCustomer(RegisterCustomer command) {
		var address = new Address(command.housenumber(), command.addition(), command.street(), command.postalCode(), command.city());
		var customer = new Customer(command.firstname(), command.lastname(), command.email(), address);

		this.publishEventsFor(customer);
		return this.customerRepository.save(customer);
	}

	public void handleReviewAdded(UUID customerId, UUID reviewId, String type) {
		var customer = this.findCustomerById(customerId);
		customer.addReview(reviewId, type);
		this.customerRepository.save(customer);
	}

	public void handleReviewRemoved(UUID customerId, UUID reviewId) {
		var customer = this.findCustomerById(customerId);
		customer.removeReview(reviewId);
		this.customerRepository.save(customer);
	}

	public void handleOrderAdded(UUID customerId, UUID orderId) {
		var customer = this.findCustomerById(customerId);
		customer.addOrder(orderId);
		this.customerRepository.save(customer);
	}

	public void handleOrderRemoved(UUID customerId, UUID orderId) {
		var customer = this.findCustomerById(customerId);
		customer.removeOrder(orderId);
		this.customerRepository.save(customer);
	}

	private void publishEventsFor(Customer customer) {
		List<CustomerDomainEvent> events = customer.listEvents();
		events.forEach(eventPublisher::publish);
		customer.clearEvents();
	}

	private Customer findCustomerById(UUID id) {
		return this.customerRepository.findById(id)
				.orElseThrow(() -> new CustomerNotFoundException(id.toString()));
	}
}
