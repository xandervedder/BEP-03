package nl.softwarestrijders.waiter.customer.core.application;

import nl.softwarestrijders.waiter.customer.core.application.exception.CustomerNotFoundException;
import nl.softwarestrijders.waiter.customer.core.application.query.GetCustomerById;
import nl.softwarestrijders.waiter.customer.core.domain.Customer;
import nl.softwarestrijders.waiter.customer.core.port.storage.AddressRepository;
import nl.softwarestrijders.waiter.customer.core.port.storage.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerQueryHandler {
	private final CustomerRepository customerRepository;
	private final AddressRepository addressRepository;

	public CustomerQueryHandler(CustomerRepository customerRepository, AddressRepository addressRepository) {
		this.customerRepository = customerRepository;
		this.addressRepository = addressRepository;
	}

	public Customer handle(GetCustomerById query) {
		return this.customerRepository.findById(query.getId())
				.orElseThrow(() -> new CustomerNotFoundException(query.getId().toString()));
	}
}
