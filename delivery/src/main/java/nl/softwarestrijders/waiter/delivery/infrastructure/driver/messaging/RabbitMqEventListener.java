package nl.softwarestrijders.waiter.delivery.infrastructure.driver.messaging;

import nl.softwarestrijders.waiter.delivery.core.application.DeliveryCommandHandler;
import nl.softwarestrijders.waiter.delivery.infrastructure.driver.messaging.event.OrderEvent;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class RabbitMqEventListener {
    private final DeliveryCommandHandler commandHandler;

    public RabbitMqEventListener(DeliveryCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @RabbitListener(queues = "#{'${messaging.queue.order}'}")
    void listen(Message message, OrderEvent event) {
        var key = message.getMessageProperties().getReceivedRoutingKey();
        switch (key) {
            case "order.created" -> this.commandHandler.handleRegisterDelivery(event.order, event.customer);
            case "order.deleted" -> this.commandHandler.handleDeleteDelivery(event.order);
        }
    }
}
