package nl.softwarestrijders.waiter.delivery.infrastructure.driver.messaging.event;

import nl.softwarestrijders.waiter.delivery.core.domain.Status;

import java.time.Instant;
import java.util.UUID;

public class DeliveryStatusEvent {
    public UUID eventId;
    public String eventKey;
    public Instant eventDate;
    public UUID delivery;
    public Enum<Status> status;
}
