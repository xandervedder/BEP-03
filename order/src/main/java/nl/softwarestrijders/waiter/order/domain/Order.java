package nl.softwarestrijders.waiter.order.domain;

public class Order {

    private OrderId id;
    private Reciept reciept;
    private Price price;

    public Order(OrderId id) {
        this.id = id;
    }
}
