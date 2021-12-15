package nl.softwarestrijders.waiter.delivery.core.port.storage;

import nl.softwarestrijders.waiter.delivery.core.domain.Delivery;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface DeliveryRepository extends MongoRepository<Delivery, UUID> {
    Optional<Delivery> findDeliveryByOrderId(UUID orderId);
}
