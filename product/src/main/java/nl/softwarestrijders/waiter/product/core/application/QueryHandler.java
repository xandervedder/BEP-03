package nl.softwarestrijders.waiter.product.core.application;

import nl.softwarestrijders.waiter.product.core.application.exceptions.ProductNotFoundException;
import nl.softwarestrijders.waiter.product.core.application.query.FindProductByIdQuery;
import nl.softwarestrijders.waiter.product.core.domain.Product;
import nl.softwarestrijders.waiter.product.core.port.data.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryHandler {
    private final ProductRepository repository;

    public QueryHandler(ProductRepository repository) {
        this.repository = repository;
    }

    public Product handle(FindProductByIdQuery query) {
        var id = query.id();
        return this.repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public List<Product> handle() {
        return this.repository.findAll();
    }
}
