package nl.softwarestrijders.waiter.review.core.domain;

/**
 * Record that contains the {@link Rating} of a certain review, it is unknown if MongoDb supports record-like objects.
 */
public record Rating(int value) {
    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 5;

    /**
     * Constructor of the {@link Rating} record, checks if the value is between the min value and max value.
     *
     * @param value the value of the rating
     */
    public Rating {
        if (value < MIN_VALUE) {
            throw new IllegalArgumentException(
                    String.format("Value %d cannot be lower than minimum value %s", value, MIN_VALUE)
            );
        }

        if (value > MAX_VALUE) {
            throw new IllegalArgumentException(
                    String.format("Value %d cannot be higher than maximum value %s", value, MAX_VALUE)
            );
        }
    }
}
