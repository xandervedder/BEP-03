package nl.softwarestrijders.waiter.review.core.application;

import nl.softwarestrijders.waiter.review.core.application.command.CreateReview;
import nl.softwarestrijders.waiter.review.core.application.command.DeleteReview;
import nl.softwarestrijders.waiter.review.core.application.command.EditReview;
import nl.softwarestrijders.waiter.review.core.application.exception.AlreadyReviewedException;
import nl.softwarestrijders.waiter.review.core.application.exception.InvalidOperationException;
import nl.softwarestrijders.waiter.review.core.application.exception.ReviewNotFoundException;
import nl.softwarestrijders.waiter.review.core.domain.Rating;
import nl.softwarestrijders.waiter.review.core.domain.Review;
import nl.softwarestrijders.waiter.review.core.domain.event.ReviewCreatedEvent;
import nl.softwarestrijders.waiter.review.core.domain.event.ReviewDeletedEvent;
import nl.softwarestrijders.waiter.review.core.port.messaging.ReviewEventPublisher;
import nl.softwarestrijders.waiter.review.core.port.storage.ReviewRepository;
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

    public Review handle(CreateReview command) {
        var customer = command.customerId();
        var concept = command.conceptId();
        if (this.repository.existsByConceptIdAndCustomerId(customer, customer)) {
            throw new AlreadyReviewedException(concept, customer);
        }

        var review = new Review(
                concept,
                customer,
                command.reviewType(),
                command.title(),
                command.description(),
                new Rating(command.rating())
        );
        var entity = this.repository.save(review);
        this.eventPublisher.publish(new ReviewCreatedEvent(entity.getId(), entity.getType()));
        return entity;
    }

    public Review handle(EditReview command) {
        var review = this.findReviewById(command.reviewId());
        review.setTitle(command.title());
        review.setDescription(command.description());
        review.setRating(new Rating(command.rating()));

        return this.repository.save(review);
    }

    private Review findReviewById(UUID reviewId) {
        return this.repository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException(reviewId));
    }

    public void handle(DeleteReview command) {
        var review = this.findReviewById(command.reviewId());
        if (!review.getCustomerId().equals(command.customerId())) {
            throw new InvalidOperationException();
        }

        this.repository.delete(review);
        this.eventPublisher.publish(new ReviewDeletedEvent(review.getId(), review.getType()));
    }
}
