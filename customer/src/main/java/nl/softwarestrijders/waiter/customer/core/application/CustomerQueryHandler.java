package nl.softwarestrijders.waiter.customer.core.application;

import nl.softwarestrijders.waiter.customer.core.application.exception.CustomerNotFoundException;
import nl.softwarestrijders.waiter.customer.core.application.query.GetAddressByCustomerId;
import nl.softwarestrijders.waiter.customer.core.application.query.GetDeliveriesFromCustomer;
import nl.softwarestrijders.waiter.customer.core.application.query.GetOrdersFromCustomer;
import nl.softwarestrijders.waiter.customer.core.application.query.GetReviewsFromCustomer;
import nl.softwarestrijders.waiter.customer.core.domain.Address;
import nl.softwarestrijders.waiter.customer.core.domain.Customer;
import nl.softwarestrijders.waiter.customer.core.port.storage.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class CustomerQueryHandler {
	private final CustomerRepository customerRepository;

	public CustomerQueryHandler(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public List<Customer> handle() {
		return this.customerRepository.findAll();
	}

	public Address handle(GetAddressByCustomerId query) {
		return this.findCustomerById(query.id()).getAddress();
	}

	public List<UUID> handle(GetDeliveriesFromCustomer query) {
		return this.findCustomerById(query.id()).getDeliveries();
	}

	public List<UUID> handle(GetOrdersFromCustomer query) {
		return this.findCustomerById(query.id()).getOrders();
	}

	public Map<UUID, String> handle(GetReviewsFromCustomer query) {
		return this.findCustomerById(query.id()).getReviews();
	}

	private Customer findCustomerById(UUID id) {
		return this.customerRepository.findById(id)
				.orElseThrow(() -> new CustomerNotFoundException(id.toString()));
	}
}
