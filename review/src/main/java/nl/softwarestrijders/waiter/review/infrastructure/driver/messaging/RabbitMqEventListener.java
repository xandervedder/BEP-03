package nl.softwarestrijders.waiter.review.infrastructure.driver.messaging;

import nl.softwarestrijders.waiter.review.core.application.CommandHandler;
import nl.softwarestrijders.waiter.review.infrastructure.driver.messaging.event.CreateEvent;
import nl.softwarestrijders.waiter.review.infrastructure.driver.messaging.event.DeleteEvent;
import nl.softwarestrijders.waiter.review.infrastructure.driver.messaging.event.EditEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class RabbitMqEventListener {
    private static final Logger LOGGER = Logger.getLogger(RabbitMqEventListener.class.getName());

    private final CommandHandler commandHandler;

    public RabbitMqEventListener(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @RabbitListener(queues = "#{'${messaging.queue.create-review}'}")
    public void listen(CreateEvent event) {
        LOGGER.info(String.format("Recieved creation event: %s", event));
        this.commandHandler.handle(event);
    }

    @RabbitListener(queues = "#{'${messaging.queue.edit-review}'}")
    public void listen(EditEvent event) {
        LOGGER.info(String.format("Recieved edit event: %s", event));
        this.commandHandler.handle(event);
    }

    @RabbitListener(queues = "#{'${messaging.queue.delete-review}'}")
    public void listen(DeleteEvent event) {
        LOGGER.info(String.format("Recieved deletion event: %s", event));
        this.commandHandler.handle(event);
    }
}
