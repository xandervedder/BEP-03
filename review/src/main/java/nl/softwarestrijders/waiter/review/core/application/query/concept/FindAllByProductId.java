package nl.softwarestrijders.waiter.review.core.application.query.concept;

import java.util.UUID;

public record FindAllByProductId(UUID id) implements FindAllByConcept {
    private static final String TYPE = "product";

    @Override
    public String type() {
        return TYPE;
    }

    @Override
    public UUID id() {
        return this.id;
    }
}
