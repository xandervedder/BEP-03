package nl.softwarestrijders.waiter.order.core.application;

import nl.softwarestrijders.waiter.order.common.exception.OrderNotFoundException;
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
        return this.repository.findById(id).orElseThrow(
                () -> new OrderNotFoundException("Could not find order with id: " + id));
    }

    public UUID getCustomerByOrderId(UUID id) {
        return this.repository.findById(id).orElseThrow(
                () -> new OrderNotFoundException("Could not find customer with order id: " + id))
                .getCustomerId();
    }

    public Price getTotalPriceByOrderId(UUID id) {
        return this.repository.findById(id).orElseThrow(
                () -> new OrderNotFoundException("Could not find price with order id: " + id))
                .getPrice();
    }

    public List<Order> getAllOrdersByCustomerId(UUID id) {
        return this.repository.findAllByCustomerId(id).orElseThrow(
                () -> new OrderNotFoundException("Could not find orders with customer id: " + id));
    }

    public List<Order> getAllOrders() {
        return this.repository.findAll();
    }
}
