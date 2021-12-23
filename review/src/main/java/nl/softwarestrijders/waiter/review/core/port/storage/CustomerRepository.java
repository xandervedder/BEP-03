package nl.softwarestrijders.waiter.review.core.port.storage;

import java.util.UUID;

public interface CustomerRepository {
    boolean existsById(UUID id);
}
