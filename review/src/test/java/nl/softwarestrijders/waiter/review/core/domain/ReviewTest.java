package nl.softwarestrijders.waiter.review.core.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ReviewTest {
    private static final String TITLE = "Good";
    private static final String DESCRIPTION = "The delivery was on time, excellent!";
    private static final Rating RATING = new Rating(4);
    private static final String LONG_TITLE = "This title is too long, I would know.";
    private static final String LONG_DESCRIPTION = """
            This description is going to be too long. It isn't there yet, but it will be, you'll see.
            The previous sentence was almost one fourth of the total characters allowed. See, it's easy.
            Actually I lied, that wasn't one fourth at all. I actually don't know how far we are.
            Just like that, we've made it halfway there, or did we? Frankly I don't know anymore.
            What I do know, however, is that this sentence is going to be the nail in the coffin, we're done.
            Not yet, almost. Just a little bit more. I can see it, we are here.
            """;

    @ParameterizedTest
    @MethodSource("nullValueProvider")
    void shouldThrowWhenGivingNullValues(UUID customerId, ReviewType type, String title, String description, Rating rating) {
        assertThrows(NullPointerException.class, () -> new Review(customerId, type, title, description, rating));
    }

    private static Stream<Arguments> nullValueProvider() {
        return Stream.of(
                Arguments.of(null, ReviewType.DELIVERY, TITLE, DESCRIPTION, RATING),
                Arguments.of(UUID.randomUUID(), null, TITLE, DESCRIPTION, RATING),
                Arguments.of(UUID.randomUUID(), ReviewType.DELIVERY, null, DESCRIPTION, RATING),
                Arguments.of(UUID.randomUUID(), ReviewType.DELIVERY, TITLE, null, RATING),
                Arguments.of(UUID.randomUUID(), ReviewType.DELIVERY, TITLE, DESCRIPTION, null)
        );
    }

    @ParameterizedTest
    @MethodSource("incorrectStringsProvider")
    void shouldThrowWhenGivingIncorrectStringValues(UUID customerId, ReviewType type, String title, String description, Rating rating) {
        assertThrows(IllegalArgumentException.class, () -> new Review(customerId, type, title, description, rating));
    }

    private static Stream<Arguments> incorrectStringsProvider() {
        return Stream.of(
                // Titles
                Arguments.of(UUID.randomUUID(), ReviewType.DELIVERY, "", DESCRIPTION, RATING),
                Arguments.of(UUID.randomUUID(), ReviewType.DELIVERY, "   ", DESCRIPTION, RATING),
                Arguments.of(UUID.randomUUID(), ReviewType.DELIVERY, "S", DESCRIPTION, RATING),
                Arguments.of(UUID.randomUUID(), ReviewType.DELIVERY, LONG_TITLE, DESCRIPTION, RATING),
                // Descriptions
                Arguments.of(UUID.randomUUID(), ReviewType.DELIVERY, TITLE, "", RATING),
                Arguments.of(UUID.randomUUID(), ReviewType.DELIVERY, TITLE, "  ", RATING),
                Arguments.of(UUID.randomUUID(), ReviewType.DELIVERY, TITLE, "This description is too short.", RATING),
                Arguments.of(UUID.randomUUID(), ReviewType.DELIVERY, TITLE, LONG_DESCRIPTION, RATING)
        );
    }

    @Test
    void shouldRetrieveValuesCorrectly() {
        var uuid = UUID.randomUUID();
        var review = new Review(uuid, ReviewType.PRODUCT, TITLE, DESCRIPTION, RATING);

        assertNotNull(review.getId());
        assertEquals(uuid, review.getCustomerId());
        assertEquals(ReviewType.PRODUCT, review.getType());
        assertEquals("Good", review.getTitle());
        assertEquals("The delivery was on time, excellent!", review.getDescription());
        assertEquals(new Rating(4), review.getRating());
    }
}
