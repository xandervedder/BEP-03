package nl.softwarestrijders.waiter.order.core.domain.events;

import java.util.UUID;

public class ProductAddedToOrder extends OrderEvent {
    private final UUID order;
    private final UUID productId;
    private final int amount;

    public ProductAddedToOrder(UUID order, UUID productId, int amount) {
        this.order = order;
        this.productId = productId;
        this.amount = amount;
    }

    public UUID getOrder() {
        return order;
    }

    public UUID getProductId() {
        return productId;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String getEventKey() {
        return "order.product.added";
    }
}
