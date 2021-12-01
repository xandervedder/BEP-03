package nl.softwarestrijders.waiter.order.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReceiptTest {

    Receipt receipt;

    @BeforeEach
    void initialize() {
        this.receipt = new Receipt();
        this.receipt.addItem(new ProductId(1), 2);
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
        var product = new ProductId(2);
        this.receipt.addItem(product, 1);

        assertEquals(ReceiptItem.class, this.receipt.getItemByProductId(product).getClass());
    }

    @Test
    @DisplayName("Add product should add amount product")
    void shouldAddProductToItemList() {
        var product = new ProductId(1);
        this.receipt.addItem(product, 1);

        assertEquals(3, this.receipt.getItemByProductId(product).getAmount());
    }

    @Test
    @DisplayName("Add product should remove product entirely")
    void shouldRemoveProductFromItemList() {
        var product = new ProductId(1);
        this.receipt.removeItem(product, 2);

        assertNull(this.receipt.getItemByProductId(product));
    }

    @Test
    @DisplayName("Add product remove product")
    void shouldRemoveProductAmountFromItemList() {
        var product = new ProductId(1);
        this.receipt.removeItem(product, 1);

        assertEquals(1, this.receipt.getItemByProductId(product).getAmount());
    }

}