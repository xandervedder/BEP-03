package nl.softwarestrijders.waiter.review.infrastructure.driven.messaging;

import nl.softwarestrijders.waiter.review.core.domain.event.DomainEvent;
import nl.softwarestrijders.waiter.review.core.port.messaging.ReviewEventPublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RabbitMqEventPublisher implements ReviewEventPublisher {
    private final RabbitTemplate rabbitTemplate;
    private final String waiterExchange;

    public RabbitMqEventPublisher(RabbitTemplate rabbitTemplate, String waiterExchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.waiterExchange = waiterExchange;
    }

    @Override
    public void publish(DomainEvent event) {
        this.rabbitTemplate.convertAndSend(this.waiterExchange, event.key(), event);
    }
}
