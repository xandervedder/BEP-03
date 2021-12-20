package nl.softwarestrijders.waiter.order.core.application;

import nl.softwarestrijders.waiter.order.core.domain.Order;
import nl.softwarestrijders.waiter.order.core.domain.events.CreatedOrder;
import nl.softwarestrijders.waiter.order.core.domain.id.CustomerId;
import nl.softwarestrijders.waiter.order.core.domain.id.OrderId;
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

    public void handleCreateOrder(UUID customerId) {
        var order = new Order(new OrderId(UUID.randomUUID()));
        order.setCustomerId(new CustomerId(customerId));

        this.repository.save(order);

        this.eventPublisher.publish(new CreatedOrder(order.getId().id()));
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
