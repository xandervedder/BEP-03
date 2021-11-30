package nl.softwarestrijders.waiter.order.domain;

import java.util.Collections;
import java.util.List;

public class Reciept {

    private List<ProductId> products;

    public Reciept() {}

    public List<ProductId> getProducts() {
        return Collections.unmodifiableList(this.products);
    }

    public void addProduct(ProductId product) {
        this.products.add(product);
    }
}
