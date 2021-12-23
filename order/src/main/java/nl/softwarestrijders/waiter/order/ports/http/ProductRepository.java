package nl.softwarestrijders.waiter.order.ports.http;

import nl.softwarestrijders.waiter.order.adapters.http.repository.dto.ProductDto;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {
    Optional<ProductDto> findProduct(UUID productId);
}
