package nl.softwarestrijders.waiter.product.domain;

import java.util.List;

/**
 * Record that contains all nutritional values of the {@link Product}.
 */
public record NutritionalValue(int kcal, int fats, int carbs, int proteins, int salts) {
    /**
     * Constructor that checks if any value is smaller than 0. if true, throw exception.
     */
    public NutritionalValue {
        for(var value : List.of(kcal, fats, carbs, proteins, salts)) {
            if(value < 0) throw new IllegalArgumentException("Nutritional value has to be bigger than 0.");
        }
    }
}
