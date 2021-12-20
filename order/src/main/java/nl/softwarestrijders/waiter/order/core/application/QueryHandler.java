package nl.softwarestrijders.waiter.order.core.application;

import nl.softwarestrijders.waiter.order.core.domain.Order;
import nl.softwarestrijders.waiter.order.core.domain.Price;
import nl.softwarestrijders.waiter.order.ports.storage.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class QueryHandler {
    private final OrderRepository repository;

    public QueryHandler(OrderRepository repository) {
        this.repository = repository;
    }

    public Order getOrderById(UUID id) {
        return this.repository.findById(id).orElseThrow();
    }

    public UUID getCustomerByOrderId(UUID id) {
        return this.repository.findById(id).orElseThrow().getCustomerId();
    }

    public Price getTotalPriceByOrderId(UUID id) {
        return this.repository.findById(id).orElseThrow().getPrice();
    }

    public List<Order> getAllOrdersByCustomerId(UUID id) {
        return this.repository.findAllByCustomerId(id).orElseThrow();
    }

    public List<Order> getAllOrders() {
        return this.repository.findAll();
    }
}
