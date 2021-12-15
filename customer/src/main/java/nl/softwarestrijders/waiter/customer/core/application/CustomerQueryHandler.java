package nl.softwarestrijders.waiter.customer.core.application;

import nl.softwarestrijders.waiter.customer.core.application.exception.CustomerNotFoundException;
import nl.softwarestrijders.waiter.customer.core.application.query.*;
import nl.softwarestrijders.waiter.customer.core.domain.Address;
import nl.softwarestrijders.waiter.customer.core.domain.Customer;
import nl.softwarestrijders.waiter.customer.core.port.storage.AddressRepository;
import nl.softwarestrijders.waiter.customer.core.port.storage.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerQueryHandler {
	private final CustomerRepository customerRepository;
	private final AddressRepository addressRepository;

	public CustomerQueryHandler(CustomerRepository customerRepository, AddressRepository addressRepository) {
		this.customerRepository = customerRepository;
		this.addressRepository = addressRepository;
	}

	public Address handle(GetAddressByCustomerId query) {
		return this.findCustomerById(query.id()).getAddress();
	}

	public void handle(GetAllProducts query) {
		//TODO: implement
	}

	public Customer handle(GetCustomerById query) {
		return this.findCustomerById(query.id());
	}

	public void handle(GetDeliveriesFromCustomer query) {
		//TODO: implement
	}

	public void handle(GetDeliveryStatusFromOrder query) {
		//TODO: implement
	}

	public String handle(GetEmailAddressFromCustomer query) {
		return this.findCustomerById(query.id()).getEmail();
	}

	public void handle(GetOrdersFromCustomer query) {
		//TODO: implement
	}

	public void handle(GetProductInfo query) {
		//TODO: implement
	}

	public List<UUID> handle(GetReviewsFromCustomer query) {
		return this.findCustomerById(query.id()).getReviews();
	}

	public Customer findCustomerById(UUID id) {
		return this.customerRepository.findById(id)
				.orElseThrow(() -> new CustomerNotFoundException(id.toString()));
	}
}
