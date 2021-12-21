package nl.softwarestrijders.waiter.review.infrastructure.driver.web;

import nl.softwarestrijders.waiter.review.core.application.CommandHandler;
import nl.softwarestrijders.waiter.review.core.application.QueryHandler;
import nl.softwarestrijders.waiter.review.core.application.command.CreateReview;
import nl.softwarestrijders.waiter.review.core.application.command.DeleteReview;
import nl.softwarestrijders.waiter.review.core.application.command.EditReview;
import nl.softwarestrijders.waiter.review.core.application.exception.AlreadyReviewedException;
import nl.softwarestrijders.waiter.review.core.application.exception.InvalidOperationException;
import nl.softwarestrijders.waiter.review.core.application.exception.ReviewNotFoundException;
import nl.softwarestrijders.waiter.review.core.application.query.FindAllByCustomerId;
import nl.softwarestrijders.waiter.review.core.application.query.FindReviewById;
import nl.softwarestrijders.waiter.review.core.application.query.ListAll;
import nl.softwarestrijders.waiter.review.core.application.query.concept.FindAllByDeliveryId;
import nl.softwarestrijders.waiter.review.core.application.query.concept.FindAllByProductId;
import nl.softwarestrijders.waiter.review.core.domain.Review;
import nl.softwarestrijders.waiter.review.infrastructure.driver.web.dto.CreateReviewDto;
import nl.softwarestrijders.waiter.review.infrastructure.driver.web.dto.DeleteReviewDto;
import nl.softwarestrijders.waiter.review.infrastructure.driver.web.dto.EditReviewDto;
import nl.softwarestrijders.waiter.review.infrastructure.driver.web.dto.ReviewDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/review")
public class ReviewController {
    private static final Logger LOGGER =Logger.getLogger(ReviewController.class.getName());

    private final QueryHandler queryHandler;
    private final CommandHandler commandHandler;

    public ReviewController(QueryHandler queryHandler, CommandHandler commandHandler) {
        this.queryHandler = queryHandler;
        this.commandHandler = commandHandler;
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@RequestBody DeleteReviewDto request, @PathVariable UUID id) {
        this.commandHandler.handle(new DeleteReview(request.customerId(), id));
    }

    @GetMapping
    public List<ReviewDto> listAll(@RequestParam String direction, @RequestParam String sort) {
        return this.toDto(this.queryHandler.handle(new ListAll(direction, sort)));
    }

    @GetMapping("/{id}")
    public ReviewDto findByReview(@PathVariable UUID id) {
        return this.toDto(this.queryHandler.handle(new FindReviewById(id)));
    }

    @GetMapping("/customer/{id}")
    public List<ReviewDto> findByCustomer(@PathVariable UUID id) {
        return this.toDto(this.queryHandler.handle(new FindAllByCustomerId(id)));
    }

    @GetMapping("/product/{id}")
    public List<ReviewDto> findByProduct(@PathVariable UUID id) {
        return this.toDto(this.queryHandler.handle(new FindAllByProductId(id)));
    }

    @GetMapping("/delivery/{id}")
    public List<ReviewDto> findByDelivery(@PathVariable UUID id) {
        return this.toDto(this.queryHandler.handle(new FindAllByDeliveryId(id)));
    }

    @PatchMapping("/{id}")
    public ReviewDto editReview(@PathVariable UUID id, @RequestBody EditReviewDto request) {
        return this.toDto(this.commandHandler.handle(
                new EditReview(id, request.title(), request.description(), request.rating())
        ));
    }

    @PostMapping
    public ReviewDto createReview(@RequestBody CreateReviewDto request) {
        return this.toDto(this.commandHandler.handle(
                new CreateReview(
                        request.customerId(),
                        request.conceptId(),
                        request.type(),
                        request.title(),
                        request.description(),
                        request.rating()
                )
        ));
    }

    private List<ReviewDto> toDto(List<Review> reviews) {
        return reviews.stream().map(this::toDto).toList();
    }

    private ReviewDto toDto(Review review) {
        return new ReviewDto(
                review.getId(),
                review.getConceptId(),
                review.getCustomerId(),
                review.getType(),
                review.getTitle(),
                review.getDescription(),
                review.getRating().value()
        );
    }

    @ExceptionHandler(AlreadyReviewedException.class)
    public ResponseEntity<Void> handleAlreadyReviewed(Exception exception) {
        return this.withLogging(HttpStatus.BAD_REQUEST, exception);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Void> handleIllegalArgument(Exception exception) {
        return this.withLogging(HttpStatus.BAD_REQUEST, exception);
    }

    @ExceptionHandler(InvalidOperationException.class)
    public ResponseEntity<Void> handleInvalidOperation(Exception exception) {
       return this.withLogging(HttpStatus.BAD_REQUEST, exception);
    }

    @ExceptionHandler(ReviewNotFoundException.class)
    public ResponseEntity<Void> handleNotFound(Exception exception) {
        return this.withLogging(HttpStatus.NOT_FOUND, exception);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Void> handleNullPointer(Exception exception) {
        return withLogging(HttpStatus.BAD_REQUEST, exception);
    }

    private ResponseEntity<Void> withLogging(HttpStatus status, Exception exception) {
        LOGGER.log(Level.SEVERE, exception.getMessage(), exception);
        return ResponseEntity.status(status).build();
    }
}
