package nl.softwarestrijders.waiter.product.core.application.exceptions;

public class ProductAlreadyExistsException extends RuntimeException {
    public ProductAlreadyExistsException(String productName) {
        super(String.format("Product %s already exists.", productName));
    }
}
