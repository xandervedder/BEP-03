package nl.softwarestrijders.waiter.review.core.port.storage;

import nl.softwarestrijders.waiter.review.core.domain.ProductReview;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ProductReviewRepository extends MongoRepository<ProductReview, UUID> {
}
