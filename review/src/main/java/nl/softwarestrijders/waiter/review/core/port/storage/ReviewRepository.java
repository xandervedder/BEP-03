package nl.softwarestrijders.waiter.review.core.port.storage;

import nl.softwarestrijders.waiter.review.core.domain.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ReviewRepository extends MongoRepository<Review, UUID> {
}
