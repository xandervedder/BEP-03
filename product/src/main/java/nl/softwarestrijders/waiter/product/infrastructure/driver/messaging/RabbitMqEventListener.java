package nl.softwarestrijders.waiter.product.infrastructure.driver.messaging;

import nl.softwarestrijders.waiter.product.core.application.CommandHandler;
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

    @RabbitListener(queues = "#{'${messaging.queue.create-product}'}")
    public void listen(CreateProductEvent event){
        LOGGER.info("Received create product event: " + event);
        this.commandHandler.handle(event);
    }

    @RabbitListener(queues = "#{'${messaging.queue.delete-product}'}")
    public void listen(DeleteProductEvent event){
        LOGGER.info("Received delete product event: " + event);
        this.commandHandler.handle(event);
    }
}
