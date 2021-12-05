package nl.softwarestrijders.waiter.delivery.core.domain;

import nl.softwarestrijders.waiter.delivery.core.domain.exceptions.InvalidStatusUpdateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeliveryTest {
    private DeliveryAddress address;

    @BeforeEach
    void initialize() {
        this.address = new DeliveryAddress("Spinozaweg", 71, "", "3532SE", "Utrecht");
    }

    @Test
    void shouldNotThrowWhenDeliveryIsCorrect() {
        assertDoesNotThrow(() -> new Delivery(this.address, Status.REGISTERED, UUID.randomUUID()));
    }

    @Test
    void shouldThrowWhenTryingToUpdateTheStatusOfAFinalizedDelivery() {
        assertThrows(InvalidStatusUpdateException.class, () -> new Delivery(this.address, Status.DELIVERED, UUID.randomUUID()));
    }

    @Test
    void shouldThrowWhenTryingToUpdateTheStatusOfAFailedDelivery() {
        assertThrows(InvalidStatusUpdateException.class, () -> new Delivery(this.address, Status.FAILED, UUID.randomUUID()));
    }

    @Test
    void shouldThrowWhenStatusIsNull() {
        assertThrows(NullPointerException.class, () -> new Delivery(this.address, null, UUID.randomUUID()));
    }

    @Test
    void shouldSetNewStatusCorrectly() {
        var delivery = new Delivery(this.address, Status.REGISTERED, UUID.randomUUID());
        assertDoesNotThrow(() -> delivery.setStatus(Status.INPICKUP));
    }

    @Test
    void shouldThrowWhenAddressIsNull() {
        assertThrows(NullPointerException.class, () -> new Delivery(null, Status.REGISTERED, UUID.randomUUID()));
    }

    @Test
    void shouldThrowWhenOrderReferenceIsNull() {
        assertThrows(NullPointerException.class, () -> new Delivery(this.address, Status.REGISTERED, null));
    }
}
