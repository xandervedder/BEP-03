package nl.softwarestrijders.waiter.product.core.port.data;

import nl.softwarestrijders.waiter.product.core.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ProductRepository extends MongoRepository<Product, UUID> {
    boolean existByPriceAndNameAndDescription(double price, String name, String description);
}