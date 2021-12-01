package nl.softwarestrijders.waiter.order.domain;

import java.util.Objects;

public class ReceiptItem {

    private ReceiptItemId id;
    private ProductId productId;
    private int amount;

    public ReceiptItem(ReceiptItemId id, ProductId productId) {
        this.id = id;
        this.productId = productId;
        this.amount = 1;
    }

    public ReceiptItem(ReceiptItemId id, ProductId productId, int amount) {
        this.id = id;
        this.productId = productId;
        this.amount = amount;
    }

    public ReceiptItemId getId() {
        return id;
    }

    public ProductId getProductId() {
        return productId;
    }

    public int getAmount() {
        return amount;
    }

    public void addAmount(int amount) {
        if(amount < 0)
            throw new RuntimeException("This modification cannot be done");

        this.amount += amount;
    }

    public void removeAmount(int amount) {
        if(amount < 0 || this.amount - amount <= 0) {
            throw new RuntimeException("This modification cannot be done");
        }

        this.amount -= amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReceiptItem that = (ReceiptItem) o;
        return amount == that.amount && Objects.equals(id, that.id) && Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productId);
    }
}
