package nl.softwarestrijders.waiter.review.core.domain;

import java.util.Objects;
import java.util.UUID;

public final class DeliveryReview extends ReviewBase {
    private final UUID deliveryId; // Check if 'final' is possible with MongoDb

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
