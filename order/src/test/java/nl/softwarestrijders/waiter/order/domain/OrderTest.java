package nl.softwarestrijders.waiter.order.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    Order order;

    @BeforeEach
    void initialize() {
        this.order = new Order(new OrderId(1));

        var product = new ProductId(1);
        this.order.addProduct(product, 2);
    }

    @Test
    @DisplayName("Should create order correctly")
    void shouldCreateOrderCorrectly() {
        assertDoesNotThrow(() -> new Order(new OrderId(2)));
    }

    @Test
    @DisplayName("Should add product with amount 1 to order")
    void shouldAddProductToOrder() {
        var product = new ProductId(2);
        this.order.addProduct(product, 1);

        assertEquals(1, this.order.getReceipt().getItemByProductId(product).getAmount());
    }

    @Test
    @DisplayName("Should add amount to existing product in order")
    void shouldAddAmountToExistingProductInOrder() {
        var product = new ProductId(1);
        this.order.addProduct(product, 1);

        assertEquals(3, this.order.getReceipt().getItemByProductId(product).getAmount());
    }

    @Test
    @DisplayName("Should remove product with amount 1 of order")
    void shouldRemoveAmountProductOfOrder() {
        var product = new ProductId(1);
        this.order.removeProduct(product, 1);

        assertEquals(1, this.order.getReceipt().getItemByProductId(product).getAmount());
    }

    @Test
    @DisplayName("Should remove entry of product in order")
    void shouldRemoveProductInOrder() {
        var product = new ProductId(1);
        this.order.removeProduct(product, 2);

        assertNull(this.order.getReceipt().getItemByProductId(product));
    }
}