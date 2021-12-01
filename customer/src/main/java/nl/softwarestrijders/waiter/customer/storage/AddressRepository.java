package nl.softwarestrijders.waiter.customer.storage;

import nl.softwarestrijders.waiter.customer.core.domain.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface AddressRepository extends MongoRepository<Address, UUID> {
}
