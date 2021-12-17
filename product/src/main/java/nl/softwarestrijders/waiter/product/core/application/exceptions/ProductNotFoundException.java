package nl.softwarestrijders.waiter.product.core.application.exceptions;

import java.util.UUID;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(UUID productID) {
        super("Could not find product with id: " + productID.toString());
    }
}
