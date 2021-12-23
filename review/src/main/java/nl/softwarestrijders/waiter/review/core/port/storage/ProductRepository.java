package nl.softwarestrijders.waiter.review.core.port.storage;

import java.util.UUID;

public interface ProductRepository {
    boolean existsById(UUID productId);
}
