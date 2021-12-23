package nl.softwarestrijders.waiter.review.core.application;

import nl.softwarestrijders.waiter.review.core.application.command.CreateReview;
import nl.softwarestrijders.waiter.review.core.application.command.DeleteReview;
import nl.softwarestrijders.waiter.review.core.application.command.EditReview;
import nl.softwarestrijders.waiter.review.core.application.exception.AlreadyReviewedException;
import nl.softwarestrijders.waiter.review.core.application.exception.InvalidOperationException;
import nl.softwarestrijders.waiter.review.core.application.exception.ReviewNotFoundException;
import nl.softwarestrijders.waiter.review.core.application.exception.UnknownConceptException;
import nl.softwarestrijders.waiter.review.core.domain.Rating;
import nl.softwarestrijders.waiter.review.core.domain.Review;
import nl.softwarestrijders.waiter.review.core.domain.event.ReviewCreatedEvent;
import nl.softwarestrijders.waiter.review.core.domain.event.ReviewDeletedEvent;
import nl.softwarestrijders.waiter.review.core.port.messaging.ReviewEventPublisher;
import nl.softwarestrijders.waiter.review.core.port.storage.CustomerRepository;
import nl.softwarestrijders.waiter.review.core.port.storage.DeliveryRepository;
import nl.softwarestrijders.waiter.review.core.port.storage.ProductRepository;
import nl.softwarestrijders.waiter.review.core.port.storage.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CommandHandler {
    private static final String TYPE_DELIVERY = "delivery";
    private static final String TYPE_PRODUCT = "product";

    private final ReviewEventPublisher eventPublisher;
    private final ReviewRepository reviewRepository;
    private final CustomerRepository customerRepository;
    private final DeliveryRepository deliveryRepository;
    private final ProductRepository productRepository;

    public CommandHandler(
            ReviewEventPublisher eventPublisher,
            ReviewRepository repository,
            CustomerRepository customerRepository,
            DeliveryRepository deliveryRepository,
            ProductRepository productRepository
    ) {
        this.eventPublisher = eventPublisher;
        this.reviewRepository = repository;
        this.customerRepository = customerRepository;
        this.deliveryRepository = deliveryRepository;
        this.productRepository = productRepository;
    }

    public Review handle(CreateReview command) {
        var customer = command.customerId();
        var concept = command.conceptId();
        if (this.reviewRepository.existsByConceptIdAndCustomerId(customer, customer)) {
            throw new AlreadyReviewedException(concept, customer);
        }

        if (!this.customerRepository.existsById(customer)) {
            throw new IllegalArgumentException(String.format("Customer with id %s does not exist", customer));
        }

        if (this.conceptDoesNotExist(command.reviewType(), concept)) {
            throw new UnknownConceptException(concept);
        }

        var review = new Review(
                concept,
                customer,
                command.reviewType(),
                command.title(),
                command.description(),
                new Rating(command.rating())
        );
        var entity = this.reviewRepository.save(review);
        this.eventPublisher.publish(new ReviewCreatedEvent(entity.getId(), entity.getCustomerId(), entity.getType()));
        return entity;
    }

    private boolean conceptDoesNotExist(String type, UUID id) {
        if (type.equals(TYPE_DELIVERY)) {
            return !this.deliveryRepository.existsById(id);
        } else if (type.equals(TYPE_PRODUCT)) {
            return !this.productRepository.existsById(id);
        } else {
            throw new IllegalArgumentException(String.format("Unknown option %s", type));
        }
    }

    public Review handle(EditReview command) {
        var review = this.findReviewById(command.reviewId());
        review.setTitle(command.title());
        review.setDescription(command.description());
        review.setRating(new Rating(command.rating()));

        return this.reviewRepository.save(review);
    }

    private Review findReviewById(UUID reviewId) {
        return this.reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException(reviewId));
    }

    public void handle(DeleteReview command) {
        var review = this.findReviewById(command.reviewId());
        if (!review.getCustomerId().equals(command.customerId())) {
            throw new InvalidOperationException();
        }

        this.reviewRepository.delete(review);
        this.eventPublisher.publish(new ReviewDeletedEvent(review.getId(), review.getCustomerId()));
    }
}
