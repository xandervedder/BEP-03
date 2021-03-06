package nl.softwarestrijders.waiter.product.domain;

import nl.softwarestrijders.waiter.product.core.domain.NutritionalValue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class NutritionalValueTest {
    @ParameterizedTest
    @MethodSource("goodNutritionalValues")
    void shouldNotThrowWhenGivenCorrectValues(int kcal, int fats, int carbs, int proteins, int salts) {
        assertDoesNotThrow(() -> new NutritionalValue(kcal, fats, carbs, proteins, salts));
    }

    @ParameterizedTest
    @MethodSource("wrongNutritionalValues")
    void ShouldThrowWhenGivenAWrongValue(int kcal, int fats, int carbs, int proteins, int salts) {
        assertThrows(IllegalArgumentException.class, () -> new NutritionalValue(kcal, fats, carbs, proteins, salts));
    }

    @Test
    void allGettersGiveCorrectValue() {
        var nutritionalValue = new NutritionalValue(10,10,10,10,10);
        assertEquals(10, nutritionalValue.kcal());
        assertEquals(10, nutritionalValue.fats());
        assertEquals(10, nutritionalValue.carbs());
        assertEquals(10, nutritionalValue.proteins());
        assertEquals(10, nutritionalValue.salts());
    }

    private static Stream<Arguments> wrongNutritionalValues() {
        return Stream.of(
                Arguments.of(-1, 0, 0, 0, 0),
                Arguments.of(0, -1, 0, 0, 0),
                Arguments.of(0, 0, -1, 0, 0),
                Arguments.of(0, 0, 0, -1, 0),
                Arguments.of(0, 0, 0, 0, -1),
                Arguments.of(-1, -1, -1, -1, -1)
        );
    }

    private static Stream<Arguments> goodNutritionalValues() {
        return Stream.of(
                Arguments.of(0, 0, 0, 0, 0),
                Arguments.of(1, 1, 1, 1, 1),
                Arguments.of(100, 100, 100, 100, 100)
        );
    }
}
