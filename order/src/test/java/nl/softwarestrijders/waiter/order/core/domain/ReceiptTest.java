package nl.softwarestrijders.waiter.order.core.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ReceiptTest {

    Receipt receipt;
    UUID productId;

    @BeforeEach
    void initialize() {
        this.receipt = new Receipt();
        this.productId = UUID.randomUUID();
        this.receipt.addItem(productId, 2);

    }

    @Test
    @DisplayName("getItems should return an unmodifiable list")
    void shouldReturnUnmodifiableList() {
        assertEquals("UnmodifiableRandomAccessList", this.receipt.getItems().getClass().getSimpleName());
    }

    @Test
    @DisplayName("getItems should return correct items")
    void shouldReturnCorrectList() {
        assertEquals(1, this.receipt.getItems().size());
    }

    @Test
    @DisplayName("Add product should add new product")
    void shouldAddNewProductToItemList() {
        var product = UUID.randomUUID();
        this.receipt.addItem(product, 1);

        assertEquals(ReceiptItem.class, this.receipt.getItemByProductId(product).getClass());
    }

    @Test
    @DisplayName("Add product should add amount product")
    void shouldAddProductToItemList() {
        this.receipt.addItem(productId, 1);

        assertEquals(3, this.receipt.getItemByProductId(productId).getAmount());
    }

    @Test
    @DisplayName("Add product should remove product entirely")
    void shouldRemoveProductFromItemList() {
        this.receipt.removeItem(productId, 2);

        assertNull(this.receipt.getItemByProductId(productId));
    }

    @Test
    @DisplayName("Add product remove product")
    void shouldRemoveProductAmountFromItemList() {
        this.receipt.removeItem(productId, 1);

        assertEquals(1, this.receipt.getItemByProductId(productId).getAmount());
    }
}
