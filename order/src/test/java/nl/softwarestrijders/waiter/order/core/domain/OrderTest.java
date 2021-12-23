package nl.softwarestrijders.waiter.order.core.domain;

import nl.softwarestrijders.waiter.order.common.exception.InvalidModificationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    Order order;
    UUID productId;

    @BeforeEach
    void initialize() {
        this.order = new Order(UUID.randomUUID(), UUID.randomUUID());

        this.productId = UUID.randomUUID();
        this.order.addProduct(this.productId, 2, 1.00);
    }

    @Test
    @DisplayName("Should create order correctly")
    void shouldCreateOrderCorrectly() {
        assertDoesNotThrow(() -> new Order(UUID.randomUUID(), UUID.randomUUID()));
    }

    @Test
    @DisplayName("Should add product with amount 1 to order")
    void shouldAddProductToOrder() {
        var product = UUID.randomUUID();
        this.order.addProduct(product, 1, 1.00);

        assertEquals(1, this.order.getReceipt().getItemByProductId(product).getAmount());
    }

    @Test
    @DisplayName("Should add amount to existing product in order")
    void shouldAddAmountToExistingProductInOrder() {
        this.order.addProduct(this.productId, 1, 1.00);

        assertEquals(3, this.order.getReceipt().getItemByProductId(this.productId).getAmount());
    }

    @Test
    @DisplayName("Should throw exception when adding a negative amount")
    void shouldThrowAddProductNegativeAmount() {
        assertThrows(InvalidModificationException.class, () -> this.order.addProduct(this.productId, -2, 1.00));
    }

    @Test
    @DisplayName("Should remove product with amount 1 of order")
    void shouldRemoveAmountProductOfOrder() {
        this.order.removeProduct(this.productId, 1);

        assertEquals(1, this.order.getReceipt().getItemByProductId(this.productId).getAmount());
    }

    @Test
    @DisplayName("Should return correct id")
    void shouldReturnCorrectId() {
        var uuid = UUID.randomUUID();
        var order = new Order(uuid, UUID.randomUUID());

        assertEquals(uuid, order.getId());
    }

    @Test
    @DisplayName("Should return correct customer id")
    void shouldReturnCorrectCustomerId() {
        var uuid = UUID.randomUUID();
        var order = new Order(UUID.randomUUID(), uuid);

        assertEquals(uuid, order.getCustomerId());
    }

    @Test
    @DisplayName("Should return correct price object")
    void shouldReturnPriceObject() {
        var order = new Order(UUID.randomUUID(), UUID.randomUUID());

        assertEquals(Price.class, order.getPrice().getClass());
    }

    @Test
    @DisplayName("Should remove entry of product in order")
    void shouldRemoveProductInOrder() {
        this.order.removeProduct(this.productId, 2);

        assertNull(this.order.getReceipt().getItemByProductId(this.productId));
    }

    @Test
    @DisplayName("Should throw exception when removing a negative amount")
    void shouldThrowRemoveProductNegativeAmount() {
        assertThrows(InvalidModificationException.class, () -> this.order.removeProduct(this.productId, -2));
    }
}
