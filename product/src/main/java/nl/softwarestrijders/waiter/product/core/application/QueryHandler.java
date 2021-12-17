package nl.softwarestrijders.waiter.product.core.application;

import nl.softwarestrijders.waiter.product.core.application.exceptions.ProductNotFoundException;
import nl.softwarestrijders.waiter.product.core.application.query.FindByProductIdQuery;
import nl.softwarestrijders.waiter.product.core.domain.Product;
import nl.softwarestrijders.waiter.product.core.port.data.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class QueryHandler {
    private final ProductRepository repository;

    public QueryHandler(ProductRepository repository) {
        this.repository = repository;
    }

    public Product handle(FindByProductIdQuery query) {
        var id = query.id();
        return this.repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }
}
