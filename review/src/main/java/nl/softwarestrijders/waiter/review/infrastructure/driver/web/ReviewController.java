package nl.softwarestrijders.waiter.review.infrastructure.driver.web;

import nl.softwarestrijders.waiter.review.core.application.QueryHandler;
import nl.softwarestrijders.waiter.review.core.application.query.FindAllByCustomerId;
import nl.softwarestrijders.waiter.review.core.application.query.FindReviewById;
import nl.softwarestrijders.waiter.review.core.application.query.ListAll;
import nl.softwarestrijders.waiter.review.core.application.query.concept.FindAllByDeliveryId;
import nl.softwarestrijders.waiter.review.core.application.query.concept.FindAllByProductId;
import nl.softwarestrijders.waiter.review.core.domain.Review;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/review")
public class ReviewController {
    private final QueryHandler queryHandler;

    public ReviewController(QueryHandler queryHandler) {
        this.queryHandler = queryHandler;
    }

    @GetMapping("/{id}")
    public Review findById(@PathVariable UUID id) {
        return this.queryHandler.handle(new FindReviewById(id));
    }

    @GetMapping("/customer/{id}")
    public List<Review> findByCustomer(@PathVariable UUID id) {
        return this.queryHandler.handle(new FindAllByCustomerId(id));
    }

    @GetMapping("/product/{id}")
    public List<Review> findByProduct(@PathVariable UUID id) {
        return this.queryHandler.handle(new FindAllByProductId(id));
    }

    @GetMapping("/delivery/{id}")
    public List<Review> findByDelivery(@PathVariable UUID id) {
        return this.queryHandler.handle(new FindAllByDeliveryId(id));
    }

    @GetMapping
    public List<Review> listAll(@RequestParam String direction, @RequestParam String sort) {
        return this.queryHandler.handle(new ListAll(direction, sort));
    }
}
