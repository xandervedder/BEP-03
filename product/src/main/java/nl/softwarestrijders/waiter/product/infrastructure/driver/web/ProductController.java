package nl.softwarestrijders.waiter.product.infrastructure.driver.web;

import nl.softwarestrijders.waiter.product.core.application.QueryHandler;
import nl.softwarestrijders.waiter.product.core.application.query.FindByProductIdQuery;
import nl.softwarestrijders.waiter.product.core.domain.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final QueryHandler queryHandler;

    public ProductController(QueryHandler queryHandler) {
        this.queryHandler = queryHandler;
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable UUID id){
        return this.toDto(queryHandler.handle(new FindByProductIdQuery(id)));
    }

    private ProductDto toDto(Product product) {
        var nutritionalValue = product.getNutritionalValue();
        return new ProductDto(
                product.getId(),
                product.getPrice(),
                product.getName(),
                product.getDescription(),
                product.getWeight(),
                new NutritionalValueDto(
                        nutritionalValue.kcal(),
                        nutritionalValue.fats(),
                        nutritionalValue.carbs(),
                        nutritionalValue.proteins(),
                        nutritionalValue.salts())
        );
    }
}
