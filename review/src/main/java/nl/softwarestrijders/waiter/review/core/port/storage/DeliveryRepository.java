package nl.softwarestrijders.waiter.review.core.port.storage;

import java.util.UUID;

public interface DeliveryRepository {
    boolean existsById(UUID deliveryId);
}
