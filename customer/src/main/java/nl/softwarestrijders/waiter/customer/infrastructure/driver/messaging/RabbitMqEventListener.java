package nl.softwarestrijders.waiter.customer.infrastructure.driver.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqEventListener {
	@RabbitListener(queues = "#{'${messaging.queue.review}'}")
	public void listen(CustomerOnRegistrationEvent event) {
		// TODO: Implement listening to the different events
	}
}

