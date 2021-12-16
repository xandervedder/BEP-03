package nl.softwarestrijders.waiter.customer.core.port.messaging;

import nl.softwarestrijders.waiter.customer.core.domain.event.CustomerDomainEvent;

public interface CustomerEventPublisher {
	void publish(CustomerDomainEvent event);
}
