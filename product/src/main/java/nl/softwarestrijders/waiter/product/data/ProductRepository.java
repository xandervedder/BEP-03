package nl.softwarestrijders.waiter.product.data;

import nl.softwarestrijders.waiter.product.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ProductRepository extends MongoRepository<Product, UUID> {
}
