package nl.softwarestrijders.waiter.order.core.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PriceTest {

    @Test
    @DisplayName("Should create new Price object")
    void shouldCreateNewPrice() {
        assertDoesNotThrow(() -> new Price(1.00, 2.00, 0.21));
    }

}
