package nl.softwarestrijders.waiter.review.core.application;

import nl.softwarestrijders.waiter.review.core.application.exception.AlreadyReviewedException;
import nl.softwarestrijders.waiter.review.core.application.exception.InvalidOperationException;
import nl.softwarestrijders.waiter.review.core.application.exception.ReviewNotFoundException;
import nl.softwarestrijders.waiter.review.core.domain.Rating;
import nl.softwarestrijders.waiter.review.core.domain.Review;
import nl.softwarestrijders.waiter.review.core.domain.event.ReviewCreatedEvent;
import nl.softwarestrijders.waiter.review.core.domain.event.ReviewDeletedEvent;
import nl.softwarestrijders.waiter.review.core.port.messaging.ReviewEventPublisher;
import nl.softwarestrijders.waiter.review.core.port.storage.ReviewRepository;
import nl.softwarestrijders.waiter.review.infrastructure.driver.messaging.event.CreateEvent;
import nl.softwarestrijders.waiter.review.infrastructure.driver.messaging.event.DeleteEvent;
import nl.softwarestrijders.waiter.review.infrastructure.driver.messaging.event.EditEvent;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CommandHandler {
    private final ReviewEventPublisher eventPublisher;
    private final ReviewRepository repository;

    public CommandHandler(ReviewEventPublisher eventPublisher, ReviewRepository repository) {
        this.eventPublisher = eventPublisher;
        this.repository = repository;
    }

    public void handle(CreateEvent event) {
        var customer = event.customerId();
        var concept = event.conceptId();
        if (this.repository.existsByConceptIdAndCustomerId(customer, customer)) {
            throw new AlreadyReviewedException(concept, customer);
        }

        var review = new Review(
                concept,
                customer,
                event.reviewType(),
                event.title(),
                event.description(),
                new Rating(event.rating())
        );
        var entity = this.repository.save(review);
        this.eventPublisher.publish(new ReviewCreatedEvent(entity.getId(), entity.getType()));
    }

    public void handle(EditEvent event) {
        var review = this.findReviewById(event.reviewId());

        review.setTitle(event.title());
        review.setDescription(event.description());
        review.setRating(new Rating(event.rating()));
        this.repository.save(review);
    }

    private Review findReviewById(UUID reviewId) {
        return this.repository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException(reviewId));
    }

    public void handle(DeleteEvent event) {
        var review = this.findReviewById(event.reviewId());
        if (!review.getCustomerId().equals(event.customerId())) {
            throw new InvalidOperationException();
        }

        this.repository.delete(review);
        this.eventPublisher.publish(new ReviewDeletedEvent(review.getId(), review.getType()));
    }
}
