package nl.softwarestrijders.waiter.customer.infrastructure.driver.web;

import nl.softwarestrijders.waiter.customer.core.application.CustomerCommandHandler;
import nl.softwarestrijders.waiter.customer.core.application.CustomerQueryHandler;
import nl.softwarestrijders.waiter.customer.core.application.command.RegisterCustomer;
import nl.softwarestrijders.waiter.customer.core.application.query.GetDeliveriesFromCustomer;
import nl.softwarestrijders.waiter.customer.core.application.query.GetOrdersFromCustomer;
import nl.softwarestrijders.waiter.customer.core.application.query.GetReviewsFromCustomer;
import nl.softwarestrijders.waiter.customer.core.domain.Customer;
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
	public Customer registerCustomer(@RequestBody RegisterCustomerRequest request) {
		return this.commandHandler.handleRegisterCustomer(
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
		);
	}

	@GetMapping("/reviews")
	public Map<UUID, String> getReviewsFromCustomer(@RequestBody UUID customerId) {
		return this.queryHandler.handle(new GetReviewsFromCustomer(customerId));
	}

	@GetMapping("/orders")
	public List<UUID> getOrdersFromCustomer(@RequestBody UUID customerId) {
		return this.queryHandler.handle(new GetOrdersFromCustomer(customerId));
	}

	@GetMapping("/deliveries")
	public List<UUID> getDeliveriesFromCustomer(@RequestBody UUID customerId) {
		return this.queryHandler.handle(new GetDeliveriesFromCustomer(customerId));
	}
}
