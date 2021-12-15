package nl.softwarestrijders.waiter.review.core.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Class that contains data and logic to make a {@link Review}.
 */
@Document(collection = "review")
public class Review {
    private static final int MIN_TITLE_LENGTH = 3; // Allows for titles like 'Bad' and 'Good'
    private static final int MAX_TITLE_LENGTH = 32; // Disallow large sentences (those are meant for the description)
    private static final int MIN_DESCRIPTION_LENGTH = 32;
    private static final int MAX_DESCRIPTION_LENGTH = 512;
    private static final String TYPE_DELIVERY = "delivery";
    private static final String TYPE_PRODUCT = "product";

    @Id
    @SuppressWarnings("FieldMayBeFinal")
    private UUID id; // Field cannot be final, since this would break MongoDb instantiation
    private UUID conceptId;
    private UUID customerId;
    private String type;
    private String title;
    private String description;
    private Rating rating;

    /**
     * Constructor of the {@link Review} class.
     *
     * @param conceptId   The id of the concept that was reviewed. In our case this could be a Product or Delivery.
     * @param customerId  The id of the customer.
     * @param type        The type of the review, related to `reviewedConceptId`.
     * @param title       The title of the review.
     * @param description The description of the review.
     * @param rating      The rating of the review.
     * @see #setConceptId(UUID)
     * @see #setCustomerId(UUID)
     * @see #setType(String)
     * @see #setTitle(String)
     * @see #setDescription(String)
     * @see #setRating(Rating)
     */
    public Review(UUID conceptId, UUID customerId, String type, String title, String description, Rating rating) {
        this.id = UUID.randomUUID();

        this.setConceptId(conceptId);
        this.setCustomerId(customerId);
        this.setType(type);
        this.setTitle(title);
        this.setDescription(description);
        this.setRating(rating);
    }

    public UUID getId() {
        return id;
    }

    /**
     * Sets the concept id of the review.
     * I called it a 'concept' since it is about a 'concept'.
     * I am basically naming this field like it's something abstract, it could be anything, really.
     * This in turn makes it possible to reuse this class for multiple types of reviews.
     * Previously I had two separate classes for ProductReviews and DeliveryReviews, but this makes the code that creates
     * the reviews very messy, so I decided to do it this way instead.
     *
     * @param conceptId The UUID of the concept
     */
    public void setConceptId(UUID conceptId) {
        this.conceptId = Objects.requireNonNull(conceptId);
    }

    public UUID getConceptId() {
        return conceptId;
    }

    /**
     * Sets the customers' id of the {@link Review}.
     *
     * @param customerId The id of the customer.
     * @throws NullPointerException Providing null will throw this.
     */
    public void setCustomerId(UUID customerId) {
        this.customerId = Objects.requireNonNull(customerId);
    }

    public UUID getCustomerId() {
        return customerId;
    }

    /**
     * Sets the type of the review, for now this should be limited to 'product' and 'delivery' since we don't want to save
     * anything other than that.
     *
     * @param type The type of the review
     */
    public void setType(String type) {
        if (!List.of(TYPE_DELIVERY, TYPE_PRODUCT).contains(type)) {
            throw new IllegalArgumentException(String.format("Invalid review type %s", type));
        }

        this.type = Objects.requireNonNull(type);
    }

    public String getType() {
        return type;
    }

    /**
     * Sets the title of the {@link Review}.
     *
     * @param title The title of the {@link Review}.
     * @throws NullPointerException Providing null will throw this.
     * @throws IllegalArgumentException If the title is blank or outside the minimum range it will throw this exception.
     */
    public void setTitle(String title) {
        Objects.requireNonNull(title);
        requireNonBlankOrEmpty(title);
        requireStringBetweenLengthRange(MIN_TITLE_LENGTH, MAX_TITLE_LENGTH, title);

        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    /**
     * Sets the description of the {@link Review}.
     *
     * @param description The description of the {@link Review}.
     * @throws NullPointerException Providing null will throw this.
     * @throws IllegalArgumentException If the title is blank or outside the minimum range it will throw this exception.
     */
    public void setDescription(String description) {
        Objects.requireNonNull(description);
        requireNonBlankOrEmpty(description);
        requireStringBetweenLengthRange(MIN_DESCRIPTION_LENGTH, MAX_DESCRIPTION_LENGTH, description);

        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Sets the {@link Rating} of the {@link Review}
     *
     * @param rating The {@link Rating} of the {@link Review}.
     * @throws NullPointerException Providing null will throw this.
     */
    public void setRating(Rating rating) {
        this.rating = Objects.requireNonNull(rating);
    }

    public Rating getRating() {
        return rating;
    }

    private static void requireNonBlankOrEmpty(String value) {
        if (value.isEmpty() || value.isBlank()) {
            throw new IllegalArgumentException("Value cannot be blank or empty");
        }
    }

    private static void requireStringBetweenLengthRange(int minLength, int maxLength, String value) {
        var length = value.length();
        if (length < minLength) {
            throw new IllegalArgumentException(
                    String.format("Value %s is shorter than minimun length %d", value, minLength)
            );
        } else if (length > maxLength) {
            throw new IllegalArgumentException(
                    String.format("Value %s is longer than maximum length %d", value, maxLength)
            );
        }
    }
}
