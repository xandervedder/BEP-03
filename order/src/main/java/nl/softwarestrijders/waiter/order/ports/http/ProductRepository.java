package nl.softwarestrijders.waiter.order.ports.http;

import java.util.UUID;

public interface ProductRepository {
    boolean productExists(UUID productId);
}
