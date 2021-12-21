package nl.softwarestrijders.waiter.customer.infrastructure.driver.messaging;

import nl.softwarestrijders.waiter.customer.core.application.CustomerCommandHandler;
import nl.softwarestrijders.waiter.customer.infrastructure.driver.messaging.event.order.OrderEvent;
import nl.softwarestrijders.waiter.customer.infrastructure.driver.messaging.event.review.ReviewEvent;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqEventListener {
	private final CustomerCommandHandler commandHandler;

	public RabbitMqEventListener(CustomerCommandHandler commandHandler) {
		this.commandHandler = commandHandler;
	}

	@RabbitListener(queues = "#{'${messaging.queue.customer.review}'}")
	public void listen(Message message, ReviewEvent event) {
		var key = this.keyFrom(message);
		var customerId = event.customerId();
		var reviewId = event.reviewId();
		var type = event.type();
		switch (key) {
			case "events.review.created" -> this.commandHandler.handleReviewAdded(customerId, reviewId, type);
			case "events.review.deleted" -> this.commandHandler.handleReviewRemoved(customerId, reviewId);
		}
	}

	@RabbitListener(queues = "#{'${messaging.queue.customer.order}'}")
	public void listen(Message message, OrderEvent event) {
		var key = this.keyFrom(message);
		var customerId = event.customer();
		var orderId = event.order();
		switch (key) {
			case "order.created" -> this.commandHandler.handleOrderAdded(customerId, orderId);
			case "order.deleted" -> this.commandHandler.handleOrderRemoved(customerId, orderId);
		}
	}

	private String keyFrom(Message message) {
		return message.getMessageProperties().getReceivedRoutingKey();
	}
}
