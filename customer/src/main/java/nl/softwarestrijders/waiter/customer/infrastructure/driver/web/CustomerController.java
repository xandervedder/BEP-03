package nl.softwarestrijders.waiter.customer.infrastructure.driver.web;

import nl.softwarestrijders.waiter.customer.core.application.CustomerCommandHandler;
import nl.softwarestrijders.waiter.customer.core.application.CustomerQueryHandler;
import nl.softwarestrijders.waiter.customer.core.application.command.RegisterCustomer;
import nl.softwarestrijders.waiter.customer.core.application.query.*;
import nl.softwarestrijders.waiter.customer.core.domain.Address;
import nl.softwarestrijders.waiter.customer.core.domain.Customer;
import nl.softwarestrijders.waiter.customer.infrastructure.driver.web.dto.AddressDto;
import nl.softwarestrijders.waiter.customer.infrastructure.driver.web.dto.CustomerDto;
import nl.softwarestrijders.waiter.customer.infrastructure.driver.web.request.RegisterCustomerRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	private final CustomerCommandHandler commandHandler;
	private final CustomerQueryHandler queryHandler;

	public CustomerController(CustomerCommandHandler commandHandler, CustomerQueryHandler queryHandler) {
		this.commandHandler = commandHandler;
		this.queryHandler = queryHandler;
	}

	@PostMapping
	public CustomerDto registerCustomer(@RequestBody RegisterCustomerRequest request) {
		return this.toDto(this.commandHandler.handleRegisterCustomer(
				new RegisterCustomer(
						request.firstname(),
						request.lastname(),
						request.email(),
						request.housenumber(),
						request.addition(),
						request.street(),
						request.postalCode(),
						request.city()
				)
		));
	}

	@GetMapping
	public List<CustomerDto> findAll() {
		return this.toDto(this.queryHandler.handle());
	}

	@GetMapping("/{customerId}/reviews")
	public Map<UUID, String> getReviewsFromCustomer(@PathVariable UUID customerId) {
		return this.queryHandler.handle(new GetReviewsFromCustomer(customerId));
	}

	@GetMapping("/{customerId}/orders")
	public List<UUID> getOrdersFromCustomer(@PathVariable UUID customerId) {
		return this.queryHandler.handle(new GetOrdersFromCustomer(customerId));
	}

	@GetMapping("/{customerId}/deliveries")
	public List<UUID> getDeliveriesFromCustomer(@PathVariable UUID customerId) {
		return this.queryHandler.handle(new GetDeliveriesFromCustomer(customerId));
	}

	@GetMapping("/{customerId}/retrieve-address")
	public AddressDto getAddressByCustomerId(@PathVariable UUID customerId) {
		return this.toDto(this.queryHandler.handle(new GetAddressByCustomerId(customerId)));
	}

	private List<CustomerDto> toDto(List<Customer> customers) {
		return customers.stream().map(this::toDto).toList();
	}

	private CustomerDto toDto(Customer customer) {
		return new CustomerDto(
			customer.getId(),
			customer.getFirstName(),
			customer.getLastName(),
			customer.getEmail(),
			this.toDto(customer.getAddress()),
			customer.getOrders(),
			customer.getReviews(),
			customer.getDeliveries()
		);
	}

	private AddressDto toDto(Address address) {
		return new AddressDto(
			address.getHouseNumber(),
			address.getAddition(),
			address.getStreet(),
			address.getPostalCode(),
			address.getCity()
		);
	}
}
