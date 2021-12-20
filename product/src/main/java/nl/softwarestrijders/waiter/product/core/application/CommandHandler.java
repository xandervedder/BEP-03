package nl.softwarestrijders.waiter.product.core.application;

import nl.softwarestrijders.waiter.product.core.application.exceptions.ProductAlreadyExistsException;
import nl.softwarestrijders.waiter.product.core.domain.NutritionalValue;
import nl.softwarestrijders.waiter.product.core.domain.Product;
import nl.softwarestrijders.waiter.product.core.domain.event.ProductCreatedEvent;
import nl.softwarestrijders.waiter.product.core.domain.event.ProductDeletedEvent;
import nl.softwarestrijders.waiter.product.core.port.data.ProductRepository;
import nl.softwarestrijders.waiter.product.core.port.messaging.ProductEventPublisher;
import nl.softwarestrijders.waiter.product.infrastructure.driver.messaging.CreateProductCommand;
import nl.softwarestrijders.waiter.product.infrastructure.driver.messaging.DeleteProductCommand;
import org.springframework.stereotype.Service;

@Service
public class CommandHandler {
    private final ProductRepository repository;
    private final ProductEventPublisher eventPublisher;

    public CommandHandler(ProductRepository repository, ProductEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public Product handle(CreateProductCommand command) {
        if(repository.existsByPriceAndNameAndDescription(command.price(), command.name(), command.description())) {
            throw new ProductAlreadyExistsException(command.name());
        }
        Product product =
                new Product(
                command.price(),
                command.name(),
                command.description(),
                command.weight(),
                new NutritionalValue(
                        command.kcal(),
                        command.fats(),
                        command.carbs(),
                        command.proteins(),
                        command.salts()
                ));
        repository.save(product);
        eventPublisher.publish(new ProductCreatedEvent(product));
        return product;
    }

    public void handle(DeleteProductCommand command) {
        var id = command.id();
        repository.deleteById(id);
        eventPublisher.publish(new ProductDeletedEvent(id));
    }
}
