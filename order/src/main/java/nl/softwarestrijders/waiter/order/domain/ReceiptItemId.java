package nl.softwarestrijders.waiter.order.domain;

import java.util.Objects;

public class ReceiptItemId {

    private int id;

    public ReceiptItemId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReceiptItemId that = (ReceiptItemId) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
