package nl.softwarestrijders.waiter.order.core.application;

import nl.softwarestrijders.waiter.order.core.domain.Order;
import nl.softwarestrijders.waiter.order.core.domain.events.CreatedOrder;
import nl.softwarestrijders.waiter.order.core.domain.events.DeletedOrder;
import nl.softwarestrijders.waiter.order.core.domain.events.ProductAddedToOrder;
import nl.softwarestrijders.waiter.order.core.domain.events.ProductRemovedFromOrder;
import nl.softwarestrijders.waiter.order.core.domain.id.CustomerId;
import nl.softwarestrijders.waiter.order.core.domain.id.OrderId;
import nl.softwarestrijders.waiter.order.core.domain.id.ProductId;
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

    public void handleAddProductToOrder(UUID orderId, UUID productId, int amount) {
        var order = this.repository.findById(orderId).orElseThrow();

        order.addProduct(new ProductId(productId), amount);

        this.repository.save(order);

        this.eventPublisher.publish(new ProductAddedToOrder(order.getId().id(), productId, amount));
    }

    public void handleRemoveProductFromOrder(UUID orderId, UUID productId, int amount) {
        var order = this.repository.findById(orderId).orElseThrow();

        order.removeProduct(new ProductId(productId), amount);

        this.repository.save(order);

        this.eventPublisher.publish(new ProductRemovedFromOrder(order.getId().id(), productId, amount));
    }

    public void handleDeleteOrder(UUID orderId) {
        var order = this.repository.findById(orderId).orElseThrow();

        this.repository.delete(order);

        this.eventPublisher.publish(new DeletedOrder(order));
    }
}
