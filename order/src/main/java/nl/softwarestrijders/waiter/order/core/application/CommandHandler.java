package nl.softwarestrijders.waiter.order.core.application;

import nl.softwarestrijders.waiter.order.core.domain.Order;
import nl.softwarestrijders.waiter.order.ports.messaging.OrderEventPublisher;
import nl.softwarestrijders.waiter.order.ports.storage.OrderRepository;

import java.util.UUID;

public class CommandHandler {
    private final OrderEventPublisher eventPublisher;
    private final OrderRepository repository;

    public CommandHandler(OrderEventPublisher eventPublisher, OrderRepository repository) {
        this.eventPublisher = eventPublisher;
        this.repository = repository;
    }

    public void handleCreateOrder() {
        //TODO: Implement this
    }

    public void HandleChangeOrderStatus() {
        //TODO: Implement this
    }

    public void handleAddProductToOrder() {
        //TODO: Implement this
    }

    public void handleDeleteOrder() {
        //TODO: Implement this
    }

    private Order getOrderById(UUID id) {
        return this.repository.findById(id).orElseThrow();
    }
}
