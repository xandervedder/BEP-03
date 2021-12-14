package nl.softwarestrijders.waiter.delivery.core.domain.event;

import nl.softwarestrijders.waiter.delivery.core.domain.Status;

import java.util.UUID;

public class DeliveryStatusChanged extends DeliveryEvent {
    private final UUID delivery;
    private final Status status;

    public DeliveryStatusChanged(UUID delivery, Status status) {
        this.delivery = delivery;
        this.status = status;
    }

    @Override
    public String getEventKey() {
        return "delivery.status";
    }

    public UUID getDelivery() {
        return delivery;
    }

    public Status getStatus() {
        return status;
    }
}
