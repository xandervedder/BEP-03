package nl.softwarestrijders.waiter.delivery.infrastructure.driver.messaging.event;

import nl.softwarestrijders.waiter.delivery.core.domain.DeliveryAddress;
import nl.softwarestrijders.waiter.delivery.core.domain.Status;

import java.time.Instant;
import java.util.UUID;

public class DeliveryStatusEvent {
    public UUID delivery;
    public Status status;
    public DeliveryAddress address;
    public UUID order;
    public UUID customer;
}
