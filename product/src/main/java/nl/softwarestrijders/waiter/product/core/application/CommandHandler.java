package nl.softwarestrijders.waiter.product.core.application;

import nl.softwarestrijders.waiter.product.core.application.exceptions.ProductAlreadyExistsException;
import nl.softwarestrijders.waiter.product.core.domain.NutritionalValue;
import nl.softwarestrijders.waiter.product.core.domain.Product;
import nl.softwarestrijders.waiter.product.core.port.data.ProductRepository;
import nl.softwarestrijders.waiter.product.infrastructure.driver.messaging.CreateProductEvent;
import nl.softwarestrijders.waiter.product.infrastructure.driver.messaging.DeleteProductEvent;
import org.springframework.stereotype.Service;

@Service
public class CommandHandler {
    private final ProductRepository repository;

    public CommandHandler(ProductRepository repository) {
        this.repository = repository;
    }

    public void handle(CreateProductEvent event) {
        if(repository.existByPriceAndNameAndDescription(event.price(), event.name(), event.description())) {
            throw new ProductAlreadyExistsException(event.name());
        }
        repository.save(
                new Product(
                event.price(),
                event.name(),
                event.description(),
                event.weight(),
                new NutritionalValue(
                        event.kcal(),
                        event.fats(),
                        event.carbs(),
                        event.proteins(),
                        event.salts()
                )
        ));
        //ToDo publish event
    }

    public void handle(DeleteProductEvent event) {
        repository.deleteById(event.id());
        //ToDo publish event
    }
}
