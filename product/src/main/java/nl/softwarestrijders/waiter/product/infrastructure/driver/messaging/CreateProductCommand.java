package nl.softwarestrijders.waiter.product.infrastructure.driver.messaging;

public record CreateProductCommand(
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
