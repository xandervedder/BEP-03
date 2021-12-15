package nl.softwarestrijders.waiter.review.core.port.storage;

import nl.softwarestrijders.waiter.review.core.domain.Review;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface ReviewRepository extends MongoRepository<Review, UUID> {
    boolean existsByConceptIdAndCustomerId(UUID conceptId, UUID customerId);
    List<Review> findAllByCustomerId(UUID customerId);
    List<Review> findAllByConceptIdAndType(UUID conceptId, String type);
}
