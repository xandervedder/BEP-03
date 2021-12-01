package nl.softwarestrijders.waiter.review.core.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class RatingTest {
    @ParameterizedTest
    @MethodSource("correctRatingProvider")
    void shouldNotThrowWhenGivingCorrectValue(int value) {
        assertDoesNotThrow(() -> new Rating(value));
    }

    private static Stream<Arguments> correctRatingProvider() {
        return Stream.of(
                Arguments.of(1),
                Arguments.of(2),
                Arguments.of(3),
                Arguments.of(4),
                Arguments.of(5)
        );
    }

    @Test
    void shouldThrowWhenGivingAValueThatIsUnderTheMinimumValue() {
        assertThrows(IllegalArgumentException.class, () -> new Rating(0));
    }

    @Test
    void shouldThrowWhenGivingAValueThatIsAboveTheMaximumValue() {
        assertThrows(IllegalArgumentException.class, () -> new Rating(6));
    }

    @Test
    void shouldRetrieveCorrectValue() {
        var rating = new Rating(2);
        assertEquals(2, rating.value());
    }
}
