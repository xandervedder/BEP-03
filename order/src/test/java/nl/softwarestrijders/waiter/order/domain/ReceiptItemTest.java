package nl.softwarestrijders.waiter.order.domain;

import nl.softwarestrijders.waiter.order.common.exception.InvalidModificationException;
import nl.softwarestrijders.waiter.order.domain.id.ProductId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ReceiptItemTest {

    ProductId productId;
    ReceiptItem item;

    @BeforeEach
    void initialize() {
        this.productId = new ProductId(UUID.randomUUID());
        this.item = new ReceiptItem(this.productId, 2);
    }

    @Test
    @DisplayName("Should create receiptitem with amount 1")
    void shouldCreateNewItemWithAmount1() {
        var item = new ReceiptItem(this.productId);

        assertEquals(1, item.getAmount());
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
        assertThrows(InvalidModificationException.class, () -> this.item.removeAmount(2));

    }

    @Test
    @DisplayName("Should throw if amount to be added is negative")
    void shouldThrowIfAmountToAddIsNegative() {
        assertThrows(InvalidModificationException.class, () -> this.item.addAmount(-2));
    }

    @Test
    @DisplayName("Should throw if amount to be removed is negative")
    void shouldThrowIfAmountToRemoveIsNegative() {
        assertThrows(InvalidModificationException.class, () -> this.item.removeAmount(-2));
    }

    @Test
    @DisplayName("Should be same object")
    void shouldBeSameObject() {
        var item = new ReceiptItem(this.productId);
        assertEquals(this.item, item);

    }

    @Test
    @DisplayName("Objects that are not the same class should return null")
    void equalsShouldReturnNull() {
        assertNotEquals(this.item, this.productId);
    }
}
