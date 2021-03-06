package nl.softwarestrijders.waiter.product.infrastructure.driver.web;

import nl.softwarestrijders.waiter.product.core.application.CommandHandler;
import nl.softwarestrijders.waiter.product.core.application.QueryHandler;
import nl.softwarestrijders.waiter.product.core.application.query.FindProductByIdQuery;
import nl.softwarestrijders.waiter.product.core.domain.Product;
import nl.softwarestrijders.waiter.product.infrastructure.driver.messaging.CreateProductCommand;
import nl.softwarestrijders.waiter.product.infrastructure.driver.messaging.DeleteProductCommand;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final QueryHandler queryHandler;
    private final CommandHandler commandHandler;

    public ProductController(QueryHandler queryHandler, CommandHandler commandHandler) {
        this.queryHandler = queryHandler;
        this.commandHandler = commandHandler;
    }

    @GetMapping
    public List<ProductDto> findAllProducts() {
        return this.toDto(queryHandler.handle());
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable String id){
        return this.toDto(queryHandler.handle(new FindProductByIdQuery(UUID.fromString(id))));
    }

    @PostMapping
    public ProductDto createProduct(@RequestBody CreateProductDto dto) {
        return this.toDto(this.commandHandler.handle(
                new CreateProductCommand(
                        dto.price(),
                        dto.name(),
                        dto.description(),
                        dto.weight(),
                        dto.kcal(),
                        dto.fats(),
                        dto.carbs(),
                        dto.proteins(),
                        dto.salts()
                )
        ));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable String id) {
        this.commandHandler.handle(new DeleteProductCommand(UUID.fromString(id)));
    }

    private List<ProductDto> toDto(List<Product> products) {
        return products.stream().map(this::toDto).toList();
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
