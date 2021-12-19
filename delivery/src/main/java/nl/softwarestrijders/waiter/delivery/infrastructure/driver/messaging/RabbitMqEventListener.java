package nl.softwarestrijders.waiter.delivery.infrastructure.driver.messaging;

import nl.softwarestrijders.waiter.delivery.core.application.DeliveryCommandHandler;
import nl.softwarestrijders.waiter.delivery.infrastructure.driver.messaging.event.DeliveryStatusEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class RabbitMqEventListener {
    private final DeliveryCommandHandler commandHandler;

    public RabbitMqEventListener(DeliveryCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @RabbitListener(queues = "#{'${messaging.queue.order}'}")
    void listen(DeliveryStatusEvent event) {
        if ("order.created".equals(event.eventKey)) {
            this.commandHandler.handleRegisterDelivery(event.order);
        }
    }
}
