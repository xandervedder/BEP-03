package nl.softwarestrijders.waiter.customer.infrastructure.driver.web;

import nl.softwarestrijders.waiter.customer.core.application.CustomerCommandHandler;
import nl.softwarestrijders.waiter.customer.core.application.CustomerQueryHandler;
import nl.softwarestrijders.waiter.customer.core.application.command.RegisterCustomer;
import nl.softwarestrijders.waiter.customer.core.domain.Customer;
import nl.softwarestrijders.waiter.customer.infrastructure.driver.web.request.RegisterCustomerRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
