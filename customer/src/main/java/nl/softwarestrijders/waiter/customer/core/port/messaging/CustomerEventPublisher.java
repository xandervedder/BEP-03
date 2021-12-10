package nl.softwarestrijders.waiter.customer.core.port.messaging;

import nl.softwarestrijders.waiter.customer.core.domain.event.CustomerEvent;

public interface CustomerEventPublisher {
	void publish(CustomerEvent event);
}
