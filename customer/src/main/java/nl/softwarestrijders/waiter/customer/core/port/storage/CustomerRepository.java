package nl.softwarestrijders.waiter.customer.core.port.storage;

import nl.softwarestrijders.waiter.customer.core.domain.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
}
