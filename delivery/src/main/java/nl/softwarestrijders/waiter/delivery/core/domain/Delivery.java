package nl.softwarestrijders.waiter.delivery.core.domain;

import nl.softwarestrijders.waiter.delivery.core.common.Generated;
import nl.softwarestrijders.waiter.delivery.core.domain.event.DeliveryAddressChanged;
import nl.softwarestrijders.waiter.delivery.core.domain.event.DeliveryEvent;
import nl.softwarestrijders.waiter.delivery.core.domain.event.DeliveryStatusChanged;
import nl.softwarestrijders.waiter.delivery.core.domain.exceptions.InvalidStatusUpdateException;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

/**
 * Class that contains the information about the Delivery
 */
@Document(collection = "delivery")
public class Delivery {
    @Id
    private UUID id;
    private DeliveryAddress address;
    private Status status;
    private UUID orderId;

    @Transient
    private List<DeliveryEvent> events = new ArrayList<>();

    /**
     * Constructor of {@link Delivery} class.
     *
     * @param address The address where the order needs te be delivered.
     * @param status  The status of the delivery.
     * @param orderId The Id of the order related to the delivery.
     */
    public Delivery(DeliveryAddress address, Status status, UUID orderId) {
        setAddress(address);
        setStatus(status);
        setOrderId(orderId);
        this.id = UUID.randomUUID();
    }

    public void changeStatus(Status status) {
        setStatus(status);
        this.events.add(new DeliveryStatusChanged(this.id, status));
    }

    public void changeAddress(DeliveryAddress address) {
        setAddress(address);
        this.events.add(new DeliveryAddressChanged(this.id, address));
    }

    /**
     * Method used for setting/updating the status if it is valid considering the delivery process.
     * Can't update the status if the current delivery is finalized (DELIVERED) or failed.
     *
     * @param status the updated status of the delivery
     */
    public void setStatus(Status status) {
        if (this.status == Status.DELIVERED || this.status == Status.FAILED)
            throw new InvalidStatusUpdateException();
        this.status = Objects.requireNonNull(status, "Need a valid status to set or update");
    }

    public void setAddress(DeliveryAddress address) {
        this.address = Objects.requireNonNull(address, "Need an address to be able to deliver the order");
    }

    public void setOrderId(UUID orderId) {
        this.orderId = Objects.requireNonNull(orderId, "Cannot make a delivery without an order reference");
    }

    @Generated
    public UUID getId() {
        return id;
    }

    @Generated
    public DeliveryAddress getAddress() {
        return address;
    }

    @Generated
    public Status getStatus() {
        return status;
    }

    @Generated
    public UUID getOrderId() {
        return orderId;
    }

    public void addEvent(DeliveryEvent event) {
        this.events.add(event);
    }

    public void clearEvents() {
        this.events.clear();
    }

    public List<DeliveryEvent> listEvents() {
        return Collections.unmodifiableList(this.events);
    }
}
