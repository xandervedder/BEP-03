package nl.softwarestrijders.waiter.order.core.application;

import nl.softwarestrijders.waiter.order.common.exception.OrderNotFoundException;
import nl.softwarestrijders.waiter.order.core.domain.Order;
import nl.softwarestrijders.waiter.order.core.domain.events.CreatedOrder;
import nl.softwarestrijders.waiter.order.core.domain.events.DeletedOrder;
import nl.softwarestrijders.waiter.order.core.domain.events.ProductAddedToOrder;
import nl.softwarestrijders.waiter.order.core.domain.events.ProductRemovedFromOrder;
import nl.softwarestrijders.waiter.order.ports.messaging.OrderEventPublisher;
import nl.softwarestrijders.waiter.order.ports.storage.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CommandHandler {
    private final OrderEventPublisher eventPublisher;
    private final OrderRepository repository;

    public CommandHandler(OrderEventPublisher eventPublisher, OrderRepository repository) {
        this.eventPublisher = eventPublisher;
        this.repository = repository;
    }

    public Order handleCreateOrder(UUID customerId) {
        var order = new Order(UUID.randomUUID(), customerId);

        this.repository.save(order);

        this.eventPublisher.publish(new CreatedOrder(order.getId()));

        return order;
    }

    public Order handleAddProductToOrder(UUID orderId, UUID productId, int amount) {
        var order = this.repository.findById(orderId).orElseThrow(() -> new OrderNotFoundException("Could not find order with id: " + orderId));

        order.addProduct(productId, amount);

        this.repository.save(order);

        this.eventPublisher.publish(new ProductAddedToOrder(order.getId(), productId, amount));

        return order;
    }

    public void handleRemoveProductFromOrder(UUID orderId, UUID productId, int amount) {
        var order = this.repository.findById(orderId).orElseThrow(() -> new OrderNotFoundException("Could not find order with id: " + orderId));

        order.removeProduct(productId, amount);

        this.repository.save(order);

        this.eventPublisher.publish(new ProductRemovedFromOrder(order.getId(), productId, amount));
    }

    public void handleDeleteOrder(UUID orderId) {
        var order = this.repository.findById(orderId).orElseThrow(() -> new OrderNotFoundException("Could not find order with id: " + orderId));

        this.repository.delete(order);

        this.eventPublisher.publish(new DeletedOrder(order));
    }
}
