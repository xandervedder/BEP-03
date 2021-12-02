package nl.softwarestrijders.waiter.order.domain;

import nl.softwarestrijders.waiter.order.domain.id.OrderId;
import nl.softwarestrijders.waiter.order.domain.id.ProductId;

public class Order {

    private OrderId id;
    private Receipt receipt;
    private Price price;

    public Order(OrderId id) {
        this.id = id;
        this.receipt = new Receipt();
    }

    public void addProduct(ProductId product, int amount) {
        this.receipt.addItem(product, amount);
        this.price = this.calculatePrice();
    }

    public void removeProduct(ProductId productId, int amount) {
        this.receipt.removeItem(productId, amount);
        this.price = this.calculatePrice();
    }

    public Receipt getReceipt() {
        return this.receipt;
    }

    // TODO: Get this function to calculate prices from product service.
    // TODO: Write tests for this method.
    private Price calculatePrice() {
        return Price.calculatePrice(this.receipt);
    }
}
