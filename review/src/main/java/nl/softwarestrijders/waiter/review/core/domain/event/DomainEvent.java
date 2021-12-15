package nl.softwarestrijders.waiter.review.core.domain.event;

public sealed interface DomainEvent permits ReviewCreatedEvent, ReviewDeletedEvent {
    /**
     * The key of the event. This is only used for routing the message.
     * TODO: Do we want to change this when we want to send to multiple destinations?
     *
     * @return String
     */
    String key();
}
