package nl.softwarestrijders.waiter.product.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    private static final NutritionalValue nutritionalValue = new NutritionalValue(1,1,1,1,1);

    @Test
    void shouldNotThrowWithCorrectValues(){
        assertDoesNotThrow(() -> new Product(1,"test", "test", 100, nutritionalValue));
    }

    @ParameterizedTest
    @MethodSource("valuesWithNull")
    void shouldThrowWhenValueIsNull(double price, String name, String description, int weight, NutritionalValue nutritionalValue){
        assertThrows(NullPointerException.class, () -> new Product(price, name, description, weight, nutritionalValue));
    }

    @ParameterizedTest
    @MethodSource("valuesWithEmptyString")
    void shouldThrowWhenValueIsEmptyString(double price, String name, String description, int weight, NutritionalValue nutritionalValue){
        assertThrows(IllegalArgumentException.class, () -> new Product(price, name, description, weight, nutritionalValue));
    }

    @ParameterizedTest
    @MethodSource("lessThanZeroValues")
    void shouldThrowWhenValueIsLessThanZero(double price, String name, String description, int weight, NutritionalValue nutritionalValue){
        assertThrows(IllegalArgumentException.class, () -> new Product(price, name, description, weight, nutritionalValue));
    }

    private static Stream<Arguments> valuesWithNull() {
        return Stream.of(
                Arguments.of(1, null, "test", 1, nutritionalValue),
                Arguments.of(1, "test", null, 1, nutritionalValue),
                Arguments.of(1, "test", "test", 1, null)
        );
    }

    private static Stream<Arguments> valuesWithEmptyString() {
        return Stream.of(
                Arguments.of(1, "", "test", 1, nutritionalValue),
                Arguments.of(1, "test", "", 1, nutritionalValue),
                Arguments.of(1, " ", "test", 1, nutritionalValue),
                Arguments.of(1, "test", " ", 1, nutritionalValue)
        );
    }

    private static Stream<Arguments> lessThanZeroValues() {
        return Stream.of(
                Arguments.of(-1, "test", "test", 1, nutritionalValue),
                Arguments.of(1, "test", "test", -1, nutritionalValue)
        );
    }
}
