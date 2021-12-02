package nl.softwarestrijders.waiter.review.core.domain;

import java.util.Objects;
import java.util.UUID;

/**
 * Class that contains data and logic to make a {@link ReviewBase} that are both shared by {@link ProductReview} and
 * {@link DeliveryReview}.
 */
public sealed class ReviewBase permits ProductReview, DeliveryReview {
    private static final int MIN_TITLE_LENGTH = 3; // Allows for titles like 'Bad' and 'Good'
    private static final int MAX_TITLE_LENGTH = 32; // Disallow large sentences (those are meant for the description)
    private static final int MIN_DESCRIPTION_LENGTH = 32;
    private static final int MAX_DESCRIPTION_LENGTH = 512; // Is this not enough? Should it be more?

    private final UUID id; // Might change from final to non-final, maybe it's possible with MongoDB (or not)
    private UUID customerId;
    private String title;
    private String description;
    private Rating rating;

    /**
     * Constructor of the {@link ReviewBase} class.
     *
     * @param customerId  The Id of the customer.
     * @param title       The title of the review.
     * @param description The description of the review.
     * @param rating      The rating of the review.
     * @see #setCustomerId(UUID)
     * @see #setTitle(String)
     * @see #setDescription(String)
     * @see #setRating(Rating)
     */
    public ReviewBase(UUID customerId, String title, String description, Rating rating) {
        this.id = UUID.randomUUID();

        this.setCustomerId(customerId);
        this.setTitle(title);
        this.setDescription(description);
        this.setRating(rating);
    }

    public UUID getId() {
        return id;
    }

    /**
     * Sets the customers' id of the {@link ReviewBase}.
     *
     * @param customerId The id of the customer.
     * @throws NullPointerException Providing null will throw this.
     */
    public void setCustomerId(UUID customerId) {
        Objects.requireNonNull(customerId);

        this.customerId = customerId;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    /**
     * Sets the title of the {@link ReviewBase}.
     *
     * @param title The title of the {@link ReviewBase}.
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
     * Sets the description of the {@link ReviewBase}.
     *
     * @param description The description of the {@link ReviewBase}.
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
     * Sets the {@link Rating} of the {@link ReviewBase}
     *
     * @param rating The {@link Rating} of the {@link ReviewBase}.
     * @throws NullPointerException Providing null will throw this.
     */
    public void setRating(Rating rating) {
        Objects.requireNonNull(rating);

        this.rating = rating;
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
