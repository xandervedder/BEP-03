package nl.softwarestrijders.waiter.review.core.domain.event;

public sealed interface DomainEvent permits ReviewCreatedEvent, ReviewDeletedEvent {
    /**
     * The key of the event. This is only used for routing the event.
     *
     * @return String
     */
    String key();
}
