package nl.softwarestrijders.waiter.order.adapters.messaging;

import nl.softwarestrijders.waiter.order.core.domain.events.OrderEvent;
import nl.softwarestrijders.waiter.order.ports.messaging.OrderEventPublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RabbitMqEventPublisher implements OrderEventPublisher {
    private final RabbitTemplate rabbitTemplate;
    private final String waiterExchange;

    public RabbitMqEventPublisher(RabbitTemplate rabbitTemplate, String waiterExchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.waiterExchange = waiterExchange;
    }

    @Override
    public void publish(OrderEvent event) {
        this.rabbitTemplate.convertAndSend(this.waiterExchange, event.getEventKey(), event);
    }
}
