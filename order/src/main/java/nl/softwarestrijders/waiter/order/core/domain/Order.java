package nl.softwarestrijders.waiter.order.core.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "orders")
public class Order {

    @Id
    private UUID id;
    private Receipt receipt;
    private UUID customerId;
    private Price price;

    public Order(UUID id, UUID customerId) {
        this.id = id;
        this.customerId = customerId;
        this.receipt = new Receipt();
        this.price = new Price(0.00, 0.00, 0.21);
    }

    public UUID getId() {
        return id;
    }

    public void addProduct(UUID product, int amount, double price) {
        this.receipt.addItem(product, amount, price);
        this.price.calculatePrice(this.receipt);
    }

    public void removeProduct(UUID productId, int amount) {
        this.receipt.removeItem(productId, amount);
        this.price.calculatePrice(this.receipt);
    }

    public Receipt getReceipt() {
        return this.receipt;
    }

    public UUID getCustomerId() {
        return this.customerId;
    }

    public Price getPrice() {
        return price;
    }
}
