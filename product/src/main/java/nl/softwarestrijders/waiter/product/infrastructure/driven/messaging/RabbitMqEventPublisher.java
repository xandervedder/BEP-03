package nl.softwarestrijders.waiter.product.infrastructure.driven.messaging;

import nl.softwarestrijders.waiter.product.core.domain.event.ProductEvent;
import nl.softwarestrijders.waiter.product.core.port.messaging.ProductEventPublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RabbitMqEventPublisher implements ProductEventPublisher {
    private final RabbitTemplate rabbitTemplate;
    private final String waiterExchange;

    public RabbitMqEventPublisher(RabbitTemplate rabbitTemplate, String waiterExchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.waiterExchange = waiterExchange;
    }

    @Override
    public void publish(ProductEvent event) {
        this.rabbitTemplate.convertAndSend(this.waiterExchange, event.key(), event);
    }
}
