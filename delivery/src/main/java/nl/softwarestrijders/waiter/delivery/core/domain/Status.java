package nl.softwarestrijders.waiter.delivery.core.domain;

import nl.softwarestrijders.waiter.delivery.core.common.Generated;

public enum Status {
    REGISTERED, // REQUESTED
    INPICKUP, // Courier is on his way to pick up the order
    PICKUP, // Courier arrived at the restaurant for the pick up
    CONVEYING, // Courier is under way
    DELIVERED, // Courier delivered the order
    DELAYED, // Delivery got delayed
    FAILED; // Delivery failed (something went wrong)

    @Generated
    @Override
    public String toString() {
        return name();
    }
}
