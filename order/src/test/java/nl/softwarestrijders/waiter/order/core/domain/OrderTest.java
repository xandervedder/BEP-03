package nl.softwarestrijders.waiter.order.core.domain;

import nl.softwarestrijders.waiter.order.common.exception.InvalidModificationException;
import nl.softwarestrijders.waiter.order.core.domain.id.OrderId;
import nl.softwarestrijders.waiter.order.core.domain.id.ProductId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    Order order;
    ProductId productId;

    @BeforeEach
    void initialize() {
        this.order = new Order(new OrderId(UUID.randomUUID()));

        this.productId = new ProductId(UUID.randomUUID());
        this.order.addProduct(this.productId, 2);
    }

    @Test
    @DisplayName("Should create order correctly")
    void shouldCreateOrderCorrectly() {
        assertDoesNotThrow(() -> new Order(new OrderId(UUID.randomUUID())));
    }

    @Test
    @DisplayName("Should add product with amount 1 to order")
    void shouldAddProductToOrder() {
        var product = new ProductId(UUID.randomUUID());
        this.order.addProduct(product, 1);

        assertEquals(1, this.order.getReceipt().getItemByProductId(product).getAmount());
    }

    @Test
    @DisplayName("Should add amount to existing product in order")
    void shouldAddAmountToExistingProductInOrder() {
        this.order.addProduct(this.productId, 1);

        assertEquals(3, this.order.getReceipt().getItemByProductId(this.productId).getAmount());
    }

    @Test
    @DisplayName("Should throw exception when adding a negative amount")
    void shouldThrowAddProductNegativeAmount() {
        assertThrows(InvalidModificationException.class, () -> this.order.addProduct(this.productId, -2));
    }

    @Test
    @DisplayName("Should remove product with amount 1 of order")
    void shouldRemoveAmountProductOfOrder() {
        this.order.removeProduct(this.productId, 1);

        assertEquals(1, this.order.getReceipt().getItemByProductId(this.productId).getAmount());
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
