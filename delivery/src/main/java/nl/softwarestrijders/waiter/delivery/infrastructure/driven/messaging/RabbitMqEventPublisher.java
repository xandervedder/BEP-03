package nl.softwarestrijders.waiter.delivery.infrastructure.driven.messaging;

import nl.softwarestrijders.waiter.delivery.core.domain.event.DeliveryEvent;
import nl.softwarestrijders.waiter.delivery.core.port.messaging.DeliveryEventPublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RabbitMqEventPublisher implements DeliveryEventPublisher {
    private final RabbitTemplate rabbitTemplate;
    private final String waiterExchange;

    public RabbitMqEventPublisher(RabbitTemplate rabbitTemplate, String waiterExchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.waiterExchange = waiterExchange;
    }

    @Override
    public void publish(DeliveryEvent event) {
        this.rabbitTemplate.convertAndSend(waiterExchange, event.getEventKey(), event);
    }
}
