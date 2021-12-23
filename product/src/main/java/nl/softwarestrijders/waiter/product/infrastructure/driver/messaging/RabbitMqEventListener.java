package nl.softwarestrijders.waiter.product.infrastructure.driver.messaging;

import nl.softwarestrijders.waiter.product.core.application.CommandHandler;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class RabbitMqEventListener {
    private final CommandHandler commandHandler;

    public RabbitMqEventListener(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }
}
