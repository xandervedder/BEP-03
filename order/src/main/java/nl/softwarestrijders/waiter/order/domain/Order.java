package nl.softwarestrijders.waiter.order.domain;

public class Order {

    private OrderId id;
    private Receipt receipt;
    private Price price;

    public Order(OrderId id) {
        this.id = id;
    }

    public void addProduct(ProductId product, int amount) {
        receipt.addItem(product, amount);
        this.price = this.calculatePrice();
    }

    public void removeProduct(ProductId productId, int amount) {
        receipt.removeItem(productId, amount);
        this.price = this.calculatePrice();
    }

    private Price calculatePrice() {
        return Price.calculatePrice(this.receipt);
    }
}
