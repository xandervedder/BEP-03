package nl.softwarestrijders.waiter.customer.core.application;

import nl.softwarestrijders.waiter.customer.core.domain.Customer;
import nl.softwarestrijders.waiter.customer.core.domain.event.CustomerEvent;
import nl.softwarestrijders.waiter.customer.core.port.messaging.CustomerEventPublisher;
import nl.softwarestrijders.waiter.customer.core.port.storage.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerCommandHandler {
	private final CustomerRepository customerRepository;
	private final CustomerEventPublisher eventPublisher;

	public CustomerCommandHandler(CustomerRepository customerRepository, CustomerEventPublisher eventPublisher) {
		this.customerRepository = customerRepository;
		this.eventPublisher = eventPublisher;
	}

	private void publishEventsFor(Customer customer) {
		List<CustomerEvent> events = customer.listEvents();
		events.forEach(eventPublisher::publish);
		customer.clearEvents();
	}
}
