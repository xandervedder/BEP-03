package nl.softwarestrijders.waiter.delivery.infrastructure.driver.messaging.event;

import java.util.UUID;

public class OrderEvent {
    public UUID delivery;
    public UUID order;
    public UUID customer;
}
