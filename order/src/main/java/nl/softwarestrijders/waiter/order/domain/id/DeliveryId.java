package nl.softwarestrijders.waiter.order.domain.id;

import nl.softwarestrijders.waiter.order.common.annotation.TestExcludeGenerated;

import java.util.Objects;

@TestExcludeGenerated
public class DeliveryId {

    private int id;

    public DeliveryId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliveryId that = (DeliveryId) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
