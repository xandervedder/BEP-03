package nl.softwarestrijders.waiter.order.domain.id;

import nl.softwarestrijders.waiter.order.common.annotation.TestExcludeGenerated;

import java.util.Objects;

@TestExcludeGenerated
public class ProductId {

    private int id;

    public ProductId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductId productId = (ProductId) o;
        return id == productId.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
