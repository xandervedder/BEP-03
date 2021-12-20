package nl.softwarestrijders.waiter.product.infrastructure.driver.messaging;

import nl.softwarestrijders.waiter.product.core.application.CommandHandler;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class RabbitMqEventListener {
    private static final Logger LOGGER = Logger.getLogger(RabbitMqEventListener.class.getName());
    private final CommandHandler commandHandler;

    public RabbitMqEventListener(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }
}
