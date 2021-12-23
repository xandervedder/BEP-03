package nl.softwarestrijders.waiter.order.core.domain;

import nl.softwarestrijders.waiter.order.common.annotation.TestExcludeGenerated;
import nl.softwarestrijders.waiter.order.common.exception.InvalidModificationException;

import java.util.Objects;
import java.util.UUID;

public class ReceiptItem {

    private UUID productId;
    private double price;
    private int amount;

    public ReceiptItem() {}
    public ReceiptItem(UUID productId, double price) {
        this.productId = productId;
        this.amount = 1;
        this.price = price;
    }

    public ReceiptItem(UUID productId, int amount, double price) {
        this.productId = productId;
        this.amount = amount;
        this.price = price;
    }

    public UUID getProductId() {
        return productId;
    }

    public int getAmount() {
        return amount;
    }

    public double getPrice() {
        return price;
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
