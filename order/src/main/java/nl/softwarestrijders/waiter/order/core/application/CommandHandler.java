package nl.softwarestrijders.waiter.order.core.application;

import nl.softwarestrijders.waiter.order.common.exception.InvalidModificationException;
import nl.softwarestrijders.waiter.order.common.exception.OrderNotFoundException;
import nl.softwarestrijders.waiter.order.core.domain.Order;
import nl.softwarestrijders.waiter.order.core.domain.events.OrderCreated;
import nl.softwarestrijders.waiter.order.core.domain.events.OrderDeleted;
import nl.softwarestrijders.waiter.order.core.domain.events.ProductAddedToOrder;
import nl.softwarestrijders.waiter.order.core.domain.events.ProductRemovedFromOrder;
import nl.softwarestrijders.waiter.order.ports.http.ProductRepository;
import nl.softwarestrijders.waiter.order.ports.messaging.OrderEventPublisher;
import nl.softwarestrijders.waiter.order.ports.storage.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CommandHandler {
    private final OrderEventPublisher eventPublisher;
    private final OrderRepository repository;
    private final ProductRepository productRepository;

    public CommandHandler(OrderEventPublisher eventPublisher, OrderRepository repository, ProductRepository productRepository) {
        this.eventPublisher = eventPublisher;
        this.repository = repository;
        this.productRepository = productRepository;
    }

    public Order handleCreateOrder(UUID customerId) {
        var order = new Order(UUID.randomUUID(), customerId);

        this.repository.save(order);

        this.eventPublisher.publish(new OrderCreated(order.getId(), order.getCustomerId()));

        return order;
    }

    public Order handleAddProductToOrder(UUID orderId, UUID productId, int amount) {
        var order = this.findById(orderId);
        var product = this.productRepository.findProduct(productId).orElseThrow(
                () -> new InvalidModificationException(productId + " is not a valid product ID"));

        order.addProduct(productId, amount, product.price());

        this.repository.save(order);

        this.eventPublisher.publish(new ProductAddedToOrder(order.getId(), productId, amount));

        return order;
    }

    public void handleRemoveProductFromOrder(UUID orderId, UUID productId, int amount) {
        var order = this.findById(orderId);

        order.removeProduct(productId, amount);

        this.repository.save(order);

        this.eventPublisher.publish(new ProductRemovedFromOrder(order.getId(), productId, amount));
    }

    public void handleDeleteOrder(UUID orderId) {
        var order = this.findById(orderId);

        this.repository.delete(order);

        this.eventPublisher.publish(new OrderDeleted(orderId, order.getCustomerId()));
    }

    private Order findById(UUID id) {
        return this.repository.findById(id).orElseThrow(() -> new OrderNotFoundException("Could not find order with id: " + id));
    }
}
