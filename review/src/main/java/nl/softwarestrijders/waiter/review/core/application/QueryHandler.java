package nl.softwarestrijders.waiter.review.core.application;

import nl.softwarestrijders.waiter.review.core.application.exception.ReviewNotFoundException;
import nl.softwarestrijders.waiter.review.core.application.query.concept.FindAllByConcept;
import nl.softwarestrijders.waiter.review.core.application.query.FindAllByCustomerId;
import nl.softwarestrijders.waiter.review.core.application.query.FindReviewById;
import nl.softwarestrijders.waiter.review.core.application.query.ListAll;
import nl.softwarestrijders.waiter.review.core.domain.Review;
import nl.softwarestrijders.waiter.review.core.port.storage.ReviewRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryHandler {
    private final ReviewRepository repository;

    public QueryHandler(ReviewRepository repository) {
        this.repository = repository;
    }

    public Review handle(FindReviewById query) {
        var id = query.id();
        return this.repository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException(id));
    }

    public List<Review> handle(FindAllByCustomerId query) {
        return this.repository.findAllByCustomerId(query.id());
    }

    public List<Review> handle(FindAllByConcept query) {
        return this.repository.findAllByConceptIdAndType(query.id(), query.type());
    }

    public List<Review> handle(ListAll query) {
        var sort = Sort.by(query.sort());
        return this.repository.findAll(
                query.direction().equals(ListAll.DIRECTION_ASCENDING)
                        ? sort.ascending()
                        : sort.descending()
        );
    }
}
