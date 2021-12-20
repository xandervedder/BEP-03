package nl.softwarestrijders.waiter.product.infrastructure.driver.web;

public record NutritionalValueDto(
        int kcal,
        int fats,
        int carbs,
        int proteins,
        int salts
) {
}
