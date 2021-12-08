package nl.softwarestrijders.waiter.delivery.infrastructure.driver.messaging;

import nl.softwarestrijders.waiter.delivery.infrastructure.driver.messaging.event.DeliveryStatusEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqEventListener {
    @RabbitListener(queues = "#{'${messaging.queue.delivery}'}")
    void listen(DeliveryStatusEvent event) {
        // define the events that should be listened to.
    }
}
