package nl.softwarestrijders.waiter.product.infrastructure.driver.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqEventListener {
    @RabbitListener(queues = "#{'${messaging.queue.product}'}")
    public void listen(){} //ToDo: Add listener implementation.
}
