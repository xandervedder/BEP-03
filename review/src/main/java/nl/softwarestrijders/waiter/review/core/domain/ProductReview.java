package nl.softwarestrijders.waiter.review.core.domain;

import java.util.Objects;
import java.util.UUID;

public final class ProductReview extends ReviewBase {
    private final UUID productId; // Check if 'final' is possible with MongoDb

    /**
     * Constructor of the {@link ProductReview} class.
     *
     * @param productId   The Id of the product.
     * @param customerId  The Id of the customer.
     * @param title       The title of the review.
     * @param description The description of the review.
     * @param rating      The rating of the review.
     * @see #setCustomerId(UUID)
     * @see #setTitle(String)
     * @see #setDescription(String)
     * @see #setRating(Rating)
     */
    public ProductReview(UUID productId, UUID customerId, String title, String description, Rating rating) {
        super(customerId, title, description, rating);

        Objects.requireNonNull(productId);
        this.productId = productId;
    }

    public UUID getProductId() {
        return productId;
    }
}
