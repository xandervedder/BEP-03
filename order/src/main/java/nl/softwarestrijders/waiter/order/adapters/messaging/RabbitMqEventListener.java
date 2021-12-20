package nl.softwarestrijders.waiter.order.adapters.messaging;

import nl.softwarestrijders.waiter.order.core.application.CommandHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqEventListener {
    private final CommandHandler commandHandler;

    public RabbitMqEventListener(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    // Does not listen for now.
    @RabbitListener(queues = "#{'${messaging.queue}'}")
    public void listen() {

    }
}
