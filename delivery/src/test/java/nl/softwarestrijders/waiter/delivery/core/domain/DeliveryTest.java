package nl.softwarestrijders.waiter.delivery.core.domain;

import nl.softwarestrijders.waiter.delivery.core.domain.exceptions.InvalidStatusUpdateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeliveryTest {
    private DeliveryAddress address;
    private UUID id;

    @BeforeEach
    void initialize() {
        this.id = UUID.randomUUID();
        this.address = new DeliveryAddress("Spinozaweg", 71, "", "3532SE", "Utrecht");
    }

    @Test
    void shouldNotThrowWhenDeliveryIsCorrect() {
        assertDoesNotThrow(() -> new Delivery(this.address, Status.REGISTERED, id));
    }

    @Test
    void shouldThrowWhenTryingToUpdateTheStatusOfAFinalizedDelivery() {
        var delivery = new Delivery(this.address, Status.DELIVERED, id);
        assertThrows(InvalidStatusUpdateException.class, () -> delivery.setStatus(Status.PICKUP));
    }

    @Test
    void shouldThrowWhenTryingToUpdateTheStatusOfAFailedDelivery() {
        var delivery = new Delivery(this.address, Status.FAILED, id);
        assertThrows(InvalidStatusUpdateException.class, () -> delivery.setStatus(Status.PICKUP));
    }

    @Test
    void shouldThrowWhenStatusIsNull() {
        assertThrows(NullPointerException.class, () -> new Delivery(this.address, null, id));
    }

    @Test
    void shouldSetNewStatusCorrectly() {
        var delivery = new Delivery(this.address, Status.REGISTERED, id);
        assertDoesNotThrow(() -> delivery.setStatus(Status.INPICKUP));
    }

    @Test
    void shouldThrowWhenAddressIsNull() {
        assertThrows(NullPointerException.class, () -> new Delivery(null, Status.REGISTERED, id));
    }

    @Test
    void shouldThrowWhenOrderReferenceIsNull() {
        assertThrows(NullPointerException.class, () -> new Delivery(this.address, Status.REGISTERED, null));
    }
}
