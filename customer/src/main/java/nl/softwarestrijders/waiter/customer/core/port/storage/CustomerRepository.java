package nl.softwarestrijders.waiter.customer.core.port.storage;

import nl.softwarestrijders.waiter.customer.core.domain.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface CustomerRepository extends MongoRepository<Customer, UUID> {
}
