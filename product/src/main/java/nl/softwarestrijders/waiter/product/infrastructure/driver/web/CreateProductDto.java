package nl.softwarestrijders.waiter.product.infrastructure.driver.web;

public record CreateProductDto(
        double price,
        String name,
        String description,
        int weight,
        int kcal,
        int fats,
        int carbs,
        int proteins,
        int salts
) {
}
