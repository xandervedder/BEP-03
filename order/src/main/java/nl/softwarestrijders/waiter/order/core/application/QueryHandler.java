package nl.softwarestrijders.waiter.order.core.application;

import nl.softwarestrijders.waiter.order.core.domain.Order;
import nl.softwarestrijders.waiter.order.ports.storage.OrderRepository;

public class QueryHandler {
    private final OrderRepository repository;

    public QueryHandler(OrderRepository repository) {
        this.repository = repository;
    }

    public Order getOrderById() {
        return null;
    }

    public Order getCustomerByOrderId() {
        return null;
    }

    public Order getTotalPriceByOrderId() {
        return null;
    }

    public Order getAllOrdersByCustomerId() {
        return null;
    }

    public Order getAllOrders() {
        return null;
    }
}
