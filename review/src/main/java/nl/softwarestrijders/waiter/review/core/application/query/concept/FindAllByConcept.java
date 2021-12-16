package nl.softwarestrijders.waiter.review.core.application.query.concept;

import java.util.UUID;

public sealed interface FindAllByConcept permits FindAllByDeliveryId, FindAllByProductId {
    String type();
    UUID id();
}
