package nl.softwarestrijders.waiter.review.core.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;
import java.util.UUID;

/**
 * Class that represents a {@link DeliveryReview}. The reason this is very similar to the
 * {@link ProductReview} is because we want seperate {@link org.springframework.data.mongodb.core.mapping.Document}'s
 * for the different types of reviews. This makes retrieving reviews of certain types easier.
 */
@Document(collection = "delivery_review")
public final class DeliveryReview extends ReviewBase {
    private final UUID deliveryId;

    /**
     * Constructor of the {@link DeliveryReview} class.
     *
     * @param deliveryId  The Id of the delivery.
     * @param customerId  The Id of the customer.
     * @param title       The title of the review.
     * @param description The description of the review.
     * @param rating      The rating of the review.
     * @see #setCustomerId(UUID)
     * @see #setTitle(String)
     * @see #setDescription(String)
     * @see #setRating(Rating)
     */
    public DeliveryReview(UUID deliveryId, UUID customerId, String title, String description, Rating rating) {
        super(customerId, title, description, rating);

        Objects.requireNonNull(deliveryId);
        this.deliveryId = deliveryId;
    }

    public UUID getDeliveryId() {
        return deliveryId;
    }
}
