package nl.softwarestrijders.waiter.customer.infrastructure.driven.messaging;


import nl.softwarestrijders.waiter.customer.core.domain.event.CustomerDomainEvent;
import nl.softwarestrijders.waiter.customer.core.port.messaging.CustomerEventPublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RabbitMqEventPublisher implements CustomerEventPublisher {
	private final RabbitTemplate rabbitTemplate;
	private final String waiterExchange;

	public RabbitMqEventPublisher(RabbitTemplate rabbitTemplate, String waiterExchange) {
		this.rabbitTemplate = rabbitTemplate;
		this.waiterExchange = waiterExchange;
	}

	@Override
	public void publish(CustomerDomainEvent event) {
		this.rabbitTemplate.convertAndSend(this.waiterExchange, event.key(), event);
	}
}
