package nl.softwarestrijders.waiter.order.core.domain;

import nl.softwarestrijders.waiter.order.core.domain.id.CustomerId;
import nl.softwarestrijders.waiter.order.core.domain.id.OrderId;
import nl.softwarestrijders.waiter.order.core.domain.id.ProductId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "orders")
public class Order {

    @Id
    private OrderId id;

    private Receipt receipt;

    private CustomerId customerId;

    private Price price;

    public Order(OrderId id) {
        this.id = id;
        this.receipt = new Receipt();
    }

    public OrderId getId() {
        return id;
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

    public CustomerId getCustomerId() {
        return customerId;
    }

    public void setCustomerId(CustomerId customerId) {
        this.customerId = customerId;
    }

    public Price getPrice() {
        return price;
    }

    private Price calculatePrice() {
        return Price.calculatePrice(this.receipt);
    }
}
