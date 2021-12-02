package nl.softwarestrijders.waiter.review.core.domain;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static nl.softwarestrijders.waiter.review.core.domain.ReviewBaseTest.*;
import static nl.softwarestrijders.waiter.review.core.domain.ReviewBaseTest.RATING;
import static org.junit.jupiter.api.Assertions.*;

class ProductReviewTest {
    @Test
    void shouldNotAcceptNullValueAsDeliveryId() {
        var customerId = UUID.randomUUID();
        assertThrows(NullPointerException.class, () -> new ProductReview(null, customerId, TITLE, DESCRIPTION, RATING));
    }

    @Test
    void shouldRetrieveValueCorrectly() {
        var deliveryId = UUID.randomUUID();
        assertEquals(deliveryId, new ProductReview(deliveryId, UUID.randomUUID(), TITLE, DESCRIPTION, RATING).getProductId());
    }
}
