package nl.softwarestrijders.waiter.order.ports.storage;

import nl.softwarestrijders.waiter.order.core.domain.Order;
import nl.softwarestrijders.waiter.order.core.domain.id.CustomerId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends MongoRepository<Order, UUID> {
    public Optional<List<Order>> findAllByCustomerId(CustomerId id);
}
