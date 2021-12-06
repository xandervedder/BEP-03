package nl.softwarestrijders.waiter.review.core.port.storage;

import nl.softwarestrijders.waiter.review.core.domain.DeliveryReview;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface DeliveryReviewRepository extends MongoRepository<DeliveryReview, UUID> {
}
