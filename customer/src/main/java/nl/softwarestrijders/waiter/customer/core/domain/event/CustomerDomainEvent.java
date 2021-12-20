package nl.softwarestrijders.waiter.customer.core.domain.event;

public interface CustomerDomainEvent {
	/**
	 * The key of the event. This is only used for routing the message.
	 *
	 * @return String
	 */
	String key();
}
