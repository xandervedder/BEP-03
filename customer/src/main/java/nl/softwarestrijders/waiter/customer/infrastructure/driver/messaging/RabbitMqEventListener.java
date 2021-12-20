package nl.softwarestrijders.waiter.customer.infrastructure.driver.messaging;

import nl.softwarestrijders.waiter.customer.core.application.CustomerCommandHandler;
import nl.softwarestrijders.waiter.customer.infrastructure.driver.messaging.event.delivery.DeliveryEvent;
import nl.softwarestrijders.waiter.customer.infrastructure.driver.messaging.event.order.OrderEvent;
import nl.softwarestrijders.waiter.customer.infrastructure.driver.messaging.event.product.ProductEvent;
import nl.softwarestrijders.waiter.customer.infrastructure.driver.messaging.event.review.ReviewEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqEventListener {
	private final CustomerCommandHandler commandHandler;

	public RabbitMqEventListener(CustomerCommandHandler commandHandler) {
		this.commandHandler = commandHandler;
	}

	@RabbitListener(queues = "#{'${messaging.queue.customer.review}'}")
	public void listen(ReviewEvent event) {
		var customerId = event.customerId();
		var reviewId = event.reviewId();
		var type = event.type();
		switch (event.eventKey()) {
			case "customer.review.created" -> this.commandHandler.handleReviewAdded(customerId, reviewId, type);
			case "customer.review.deleted" -> this.commandHandler.handleReviewRemoved(customerId, reviewId);
		}
	}

	@RabbitListener(queues = "#{'${messaging.queue.customer.order}'}")
	public void listen(OrderEvent event) {
		var customerId = event.customerId();
		var orderId = event.orderId();
		switch (event.eventKey()) {
			case "customer.order.created" -> this.commandHandler.handleOrderAdded(customerId, orderId);
			case "customer.order.deleted" -> this.commandHandler.handleOrderRemoved(customerId, orderId);
		}
	}

	@RabbitListener(queues = "#{'${messaging.queue.customer.delivery}'}")
	public void listen(DeliveryEvent event) {
		switch (event.eventKey()) {
			//TODO: create implementation
		}
	}

	@RabbitListener(queues = "#{'${messaging.queue.customer.product}'}")
	public void listen(ProductEvent event) {
		switch (event.eventKey()) {
			//TODO: create implementation
		}
	}
}
