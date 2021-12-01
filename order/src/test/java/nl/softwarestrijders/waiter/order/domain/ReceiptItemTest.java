package nl.softwarestrijders.waiter.order.domain;

import nl.softwarestrijders.waiter.order.domain.id.ProductId;
import nl.softwarestrijders.waiter.order.domain.id.ReceiptItemId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReceiptItemTest {

    ProductId productId;
    ReceiptItem item;

    @BeforeEach
    void initialize() {
        this.productId = new ProductId(1);
        this.item = new ReceiptItem(new ReceiptItemId(1), this.productId, 2);
    }

    @Test
    @DisplayName("Should create receiptitem with amount 1")
    void shouldCreateNewItemWithAmount1() {
        var item = new ReceiptItem(new ReceiptItemId(2), this.productId);

        assertEquals(1, item.getAmount());
    }

    @Test
    @DisplayName("Should return correct Id")
    void shouldReturnCorrectItemId() {
        var id = new ReceiptItemId(2);
        var item = new ReceiptItem(id, this.productId);

        assertEquals(id, item.getId());
    }

    @Test
    @DisplayName("Should return correct product Id")
    void shouldReturnCorrectProductId() {
        assertEquals(this.productId, this.item.getProductId());
    }

    @Test
    @DisplayName("Should add correct amount to item")
    void shouldAddAmountToItem() {
        this.item.addAmount(5);
        assertEquals(7, this.item.getAmount());
    }

    @Test
    @DisplayName("Should remove correct amount of item")
    void shouldRemoveAmountOfItem() {
        this.item.removeAmount(1);

        assertEquals(1, this.item.getAmount());
    }

    @Test
    @DisplayName("Should throw if new amount will be below 1")
    void shouldThrowIfAmountWillBeBelow1() {
        assertThrows(RuntimeException.class, () -> this.item.removeAmount(2));

    }

    @Test
    @DisplayName("Should throw if amount to be added is negative")
    void shouldThrowIfAmountToAddIsNegative() {
        assertThrows(RuntimeException.class, () -> this.item.addAmount(-2));
    }

    @Test
    @DisplayName("Should throw if amount to be removed is negative")
    void shouldThrowIfAmountToRemoveIsNegative() {
        assertThrows(RuntimeException.class, () -> this.item.removeAmount(-2));
    }

    @Test
    @DisplayName("Should be same object")
    void shouldBeSameObject() {
        var item = new ReceiptItem(new ReceiptItemId(2), this.productId);
        assertEquals(this.item, item);

    }

    @Test
    @DisplayName("Objects that are not the same class should return null")
    void equalsShouldReturnNull() {
        assertNotEquals(this.item, this.productId);
    }
}
