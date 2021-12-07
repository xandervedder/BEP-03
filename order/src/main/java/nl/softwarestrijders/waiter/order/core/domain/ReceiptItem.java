package nl.softwarestrijders.waiter.order.core.domain;

import nl.softwarestrijders.waiter.order.common.annotation.TestExcludeGenerated;
import nl.softwarestrijders.waiter.order.common.exception.InvalidModificationException;
import nl.softwarestrijders.waiter.order.core.domain.id.ProductId;

import java.util.Objects;

public class ReceiptItem {

    private ProductId productId;

    private int amount;

    public ReceiptItem() {
    }

    public ReceiptItem(ProductId productId) {
        this.productId = productId;
        this.amount = 1;
    }

    public ReceiptItem(ProductId productId, int amount) {
        this.productId = productId;
        this.amount = amount;
    }

    public ProductId getProductId() {
        return productId;
    }

    public int getAmount() {
        return amount;
    }

    public void addAmount(int amount) {
        if (amount < 0)
            throw new InvalidModificationException("This modification cannot be done");

        this.amount += amount;
    }

    public void removeAmount(int amount) {
        if (amount < 0 || this.amount - amount <= 0)
            throw new InvalidModificationException("This modification cannot be done");

        this.amount -= amount;
    }

    @Override
    @TestExcludeGenerated
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReceiptItem that = (ReceiptItem) o;
        return Objects.equals(productId, that.productId);
    }

    @Override
    @TestExcludeGenerated
    public int hashCode() {
        return Objects.hash(productId);
    }
}
