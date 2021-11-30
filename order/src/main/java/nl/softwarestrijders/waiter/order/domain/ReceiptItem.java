package nl.softwarestrijders.waiter.order.domain;

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

    public int addAmount(int amount) {
        if(amount < 0)
            throw new RuntimeException("Cannot add a negative amount");

        return this.amount += amount;
    }

    public int removeAmount(int amount) {
        if(amount < 0) {
            throw new RuntimeException("Cannot remove a negative amount");
        }

        return this.amount -= amount;
    }
}
