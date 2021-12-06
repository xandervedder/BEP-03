package nl.softwarestrijders.waiter.review.core.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;
import java.util.UUID;

/**
 * Class that represents a {@link ProductReview}. The reason this is very similar to the
 * {@link DeliveryReview} is because we want seperate {@link org.springframework.data.mongodb.core.mapping.Document}'s
 * for the different types of reviews. This makes retrieving reviews of certain types easier.
 */
@Document(collection = "product_review")
public final class ProductReview extends ReviewBase {
    private final UUID productId;

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
