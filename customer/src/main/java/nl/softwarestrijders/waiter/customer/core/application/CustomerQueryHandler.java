package nl.softwarestrijders.waiter.customer.core.application;

import nl.softwarestrijders.waiter.customer.core.application.exception.CustomerNotFoundException;
import nl.softwarestrijders.waiter.customer.core.application.query.*;
import nl.softwarestrijders.waiter.customer.core.domain.Address;
import nl.softwarestrijders.waiter.customer.core.domain.Customer;
import nl.softwarestrijders.waiter.customer.core.port.storage.AddressRepository;
import nl.softwarestrijders.waiter.customer.core.port.storage.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerQueryHandler {
	private final CustomerRepository customerRepository;

	public CustomerQueryHandler(CustomerRepository customerRepository, AddressRepository addressRepository) {
		this.customerRepository = customerRepository;
	}

	//Note: all of these query handlers that still need to be implemented are going to use
	//HttpRepositories (as implementation), but we will have to wait for that to be available (just clarifying)

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

	private Customer findCustomerById(UUID id) {
		return this.customerRepository.findById(id)
				.orElseThrow(() -> new CustomerNotFoundException(id.toString()));
	}
}
