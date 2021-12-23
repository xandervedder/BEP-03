package nl.softwarestrijders.waiter.order.core.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PriceTest {

    Receipt receipt;
    Price price;

    @BeforeEach
    void initialize() {
        this.receipt = new Receipt();
        this.receipt.addItem(UUID.randomUUID(), 1, 1.00);
        this.receipt.addItem(UUID.randomUUID(), 1, 2.00);

        this.price = new Price(0.00, 0.00, 0.21);
    }

    @Test
    @DisplayName("Should create new Price object")
    void shouldCreateNewPrice() {
        assertDoesNotThrow(() -> new Price(1.00, 2.00, 0.21));
    }

    @Test
    @DisplayName("Should calculate total correctly")
    void shouldCalculateTotal() {
        this.price.calculatePrice(this.receipt);

        assertEquals(3.00, price.getSubTotal());
        assertEquals(0.21, price.getVat());
        assertEquals(3.63, price.getTotal());
    }
}
