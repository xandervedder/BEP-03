package nl.softwarestrijders.waiter.delivery.core.domain.event;

import nl.softwarestrijders.waiter.delivery.core.domain.DeliveryAddress;

import java.util.UUID;

public class DeliveryAddressChanged extends DeliveryEvent {
    private final UUID delivery;
    private final DeliveryAddress address;

    public DeliveryAddressChanged(UUID delivery, DeliveryAddress address) {
        this.delivery = delivery;
        this.address = address;
    }

    @Override
    public String getEventKey() {
        return "delivery.address";
    }

    public UUID getDelivery() {
        return delivery;
    }

    public DeliveryAddress getAddress() {
        return address;
    }
}
