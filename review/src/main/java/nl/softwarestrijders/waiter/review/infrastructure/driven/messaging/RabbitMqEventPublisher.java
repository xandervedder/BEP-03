package nl.softwarestrijders.waiter.review.infrastructure.driven.messaging;

import nl.softwarestrijders.waiter.review.core.domain.event.ReviewEvent;
import nl.softwarestrijders.waiter.review.core.port.messaging.ReviewEventPublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RabbitMqEventPublisher implements ReviewEventPublisher {
    private final RabbitTemplate rabbitTemplate;
    private final String jobBoardExchange;

    public RabbitMqEventPublisher(RabbitTemplate rabbitTemplate, String jobBoardExchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.jobBoardExchange = jobBoardExchange;
    }

    @Override
    public void publish(ReviewEvent event) {
        this.rabbitTemplate.convertAndSend(this.jobBoardExchange, event.key(), event);
    }
}
