package nl.softwarestrijders.waiter.order.adapters.messaging;

import nl.softwarestrijders.waiter.order.core.domain.events.OrderEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqEventListener {
    @RabbitListener(queues = "#{'${messaging.queue.order}'}")
    public void listen(OrderEvent event) {
        System.out.println("EVENT LOG: " + event.message());
    }
}
