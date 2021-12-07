package nl.softwarestrijders.waiter.review.infrastructure.driver.messaging;

import nl.softwarestrijders.waiter.review.core.domain.event.ReviewEvent;
import nl.softwarestrijders.waiter.review.core.port.messaging.ReviewEventPublisher;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class RabbitMqEventListener {
    @RabbitListener(queues = "#{'${messaging.queue.review}'}")
    public void listen(ReviewOnProductEvent event) {
        var logger = Logger.getLogger(RabbitMqEventListener.class.getName());
        logger.info(String.format("Got a review event: %s", event));
    }
}
