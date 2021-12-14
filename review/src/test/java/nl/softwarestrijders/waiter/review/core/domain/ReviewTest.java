package nl.softwarestrijders.waiter.review.core.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ReviewTest {
    private static final UUID CONCEPT_ID = UUID.randomUUID();
    private static final UUID CUSTOMER_ID = UUID.randomUUID();
    private static final String TYPE = "product";
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
    void shouldThrowWhenGivingNullValues(UUID conceptId, UUID customerId, String type, String title, String description, Rating rating) {
        assertThrows(NullPointerException.class, () -> new Review(conceptId, customerId, type, title, description, rating));
    }

    private static Stream<Arguments> nullValueProvider() {
        return Stream.of(
                Arguments.of(null, CUSTOMER_ID, TYPE, TITLE, DESCRIPTION, RATING),
                Arguments.of(CONCEPT_ID, null, TYPE, TITLE, DESCRIPTION, RATING),
                Arguments.of(CONCEPT_ID, CUSTOMER_ID, null, TITLE, DESCRIPTION, RATING),
                Arguments.of(CONCEPT_ID, CUSTOMER_ID, TYPE, null, DESCRIPTION, RATING),
                Arguments.of(CONCEPT_ID, CUSTOMER_ID, TYPE, TITLE, null, RATING),
                Arguments.of(CONCEPT_ID, CUSTOMER_ID, TYPE, TITLE, DESCRIPTION, null)
        );
    }

    @ParameterizedTest
    @MethodSource("incorrectStringsProvider")
    void shouldThrowWhenGivingIncorrectStringValues(UUID conceptId, UUID customerId, String type, String title, String description, Rating rating) {
        assertThrows(IllegalArgumentException.class, () -> new Review(conceptId, customerId, type, title, description, rating));
    }

    private static Stream<Arguments> incorrectStringsProvider() {
        return Stream.of(
                // Titles
                Arguments.of(CONCEPT_ID, CUSTOMER_ID, TYPE, "", DESCRIPTION, RATING),
                Arguments.of(CONCEPT_ID, CUSTOMER_ID, TYPE, "   ", DESCRIPTION, RATING),
                Arguments.of(CONCEPT_ID, CUSTOMER_ID, TYPE, "S", DESCRIPTION, RATING),
                Arguments.of(CONCEPT_ID, CUSTOMER_ID, TYPE, LONG_TITLE, DESCRIPTION, RATING),
                // Descriptions
                Arguments.of(CONCEPT_ID, CUSTOMER_ID, TYPE, TITLE, "", RATING),
                Arguments.of(CONCEPT_ID, CUSTOMER_ID, TYPE, TITLE, "  ", RATING),
                Arguments.of(CONCEPT_ID, CUSTOMER_ID, TYPE, TITLE, "This description is too short.", RATING),
                Arguments.of(CONCEPT_ID, CUSTOMER_ID, TYPE, TITLE, LONG_DESCRIPTION, RATING),
                // Invalid review type
                Arguments.of(CONCEPT_ID, CUSTOMER_ID, "banana", TITLE, DESCRIPTION, RATING)

        );
    }

    @Test
    void shouldRetrieveValuesCorrectly() {
        var review = new Review(CONCEPT_ID, CUSTOMER_ID, TYPE, TITLE, DESCRIPTION, RATING);

        assertNotNull(review.getId());
        assertEquals(CONCEPT_ID, review.getConceptId());
        assertEquals(CUSTOMER_ID, review.getCustomerId());
        assertEquals(TYPE, review.getType());
        assertEquals("Good", review.getTitle());
        assertEquals("The delivery was on time, excellent!", review.getDescription());
        assertEquals(new Rating(4), review.getRating());
    }
}
