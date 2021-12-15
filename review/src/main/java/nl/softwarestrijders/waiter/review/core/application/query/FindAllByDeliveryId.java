package nl.softwarestrijders.waiter.review.core.application.query;

import java.util.UUID;

public record FindAllByDeliveryId(UUID id) implements FindAllByConcept {
    private static final String TYPE = "delivery";

    @Override
    public String type() {
        return TYPE;
    }

    @Override
    public UUID id() {
        return id;
    }
}
