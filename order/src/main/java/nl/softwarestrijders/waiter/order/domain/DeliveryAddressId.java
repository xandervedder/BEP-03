package nl.softwarestrijders.waiter.order.domain;

import java.util.Objects;

public class DeliveryAddressId {

    private int id;

    public DeliveryAddressId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliveryAddressId that = (DeliveryAddressId) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
