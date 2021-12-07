package nl.softwarestrijders.waiter.review.infrastructure.driver.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqEventListener {
    @RabbitListener(queues = "#{'${messaging.queue.review}'}")
    public void listen(ReviewOnProductEvent event) {
        // TODO: Implement listening to the different events
    }
}
