package nl.softwarestrijders.waiter.review.core.domain;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static nl.softwarestrijders.waiter.review.core.domain.ReviewBaseTest.*;
import static org.junit.jupiter.api.Assertions.*;

class DeliveryReviewTest {
    @Test
    void shouldNotAcceptNullValueAsDeliveryId() {
        var customerId = UUID.randomUUID();
        assertThrows(NullPointerException.class, () -> new DeliveryReview(null, customerId, TITLE, DESCRIPTION, RATING));
    }

    @Test
    void shouldRetrieveValueCorrectly() {
        var deliveryId = UUID.randomUUID();
        assertEquals(deliveryId, new DeliveryReview(deliveryId, UUID.randomUUID(), TITLE, DESCRIPTION, RATING).getDeliveryId());
    }
}
