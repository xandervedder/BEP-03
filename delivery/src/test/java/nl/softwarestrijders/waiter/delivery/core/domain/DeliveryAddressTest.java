package nl.softwarestrijders.waiter.delivery.core.domain;

import nl.softwarestrijders.waiter.delivery.core.domain.exceptions.InvalidHouseNumberException;
import nl.softwarestrijders.waiter.delivery.core.domain.exceptions.InvalidPostalCodeException;
import nl.softwarestrijders.waiter.delivery.core.domain.exceptions.InvalidStreetNameException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryAddressTest {

    @Test
    void shouldNotThrowWhenAddressIsCorrect() {
        assertDoesNotThrow(() -> new DeliveryAddress("Spinozaweg", 71, "", "3532SE", "Utrecht"));
    }

    @Test
    void shouldThrowWhenStreetNameIsNotCorrect() {
        assertThrows(InvalidStreetNameException.class, () -> new DeliveryAddress("", 71, "", "3532SE", "Utrecht"));
    }

    @Test
    void shouldThrowWhenHouseNumberIsNotCorrect() {
        assertThrows(InvalidHouseNumberException.class, () -> new DeliveryAddress("Spinozaweg", 0, "", "3532SE", "Utrecht"));
    }

    @Test
    void shouldChangeAdditionFromNullToBlank() {
        var address = new DeliveryAddress("Spinozaweg", 71, null, "3532SE", "Utrecht");
        assertEquals("", address.addition());
    }

    @ParameterizedTest
    @MethodSource("incorrectPostalCodes")
    void shouldThrowWhenPostalCodeIsNotCorrect(String postalCode) {
        assertThrows(InvalidPostalCodeException.class, () -> new DeliveryAddress("Spinozaweg", 71, "", postalCode, "Utrecht"));
    }

    private static Stream<Arguments> incorrectPostalCodes() {
        return Stream.of(
                Arguments.of("1111A"),
                Arguments.of("111AA"),
                Arguments.of("1111A1"),
                Arguments.of("11111A"),
                Arguments.of("111AAA"),
                Arguments.of("11A1AA"),
                Arguments.of("1A11AA"),
                Arguments.of("A111AA"),
                Arguments.of("0111AA"),
                Arguments.of("111111"),
                Arguments.of("1111AAA"),
                Arguments.of("11111AA")
        );
    }

    @Test
    void gettersTestForJacocoCoverage() {
        var address = new DeliveryAddress("Spinozaweg", 71, "", "3532SE", "Utrecht");
        assertNotNull(address.streetName());
        assertNotNull(address.addition());
        assertNotNull(address.postalCode());
        assertNotNull(address.city());
        assertEquals(71, address.houseNumber());
    }
}
