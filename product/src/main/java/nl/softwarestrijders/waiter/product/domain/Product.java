package nl.softwarestrijders.waiter.product.domain;

import nl.softwarestrijders.waiter.product.common.Generated;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;
import java.util.UUID;

/**
 * Class that contains all product information.
 */
@Document
public class Product {
    private @Id
    UUID id;
    private double price;
    private String name;
    private String description;
    private int weight;
    private NutritionalValue nutritionalValue;

    /**
     * Constructor of {@link Product} class.
     *
     * @param price            The price of the product in euro's.
     * @param name             The name of the product.
     * @param description      - The description of the product.
     * @param weight           The weight of the product in grams.
     * @param nutritionalValue The nutritional values {@link NutritionalValue} of the product.
     */
    public Product(double price, String name, String description, int weight, NutritionalValue nutritionalValue) {
        this.id = UUID.randomUUID();

        this.setPrice(price);
        this.setName(name);
        this.setDescription(description);
        this.setWeight(weight);
        this.setNutritionalValue(nutritionalValue);
    }

    /**
     * Sets the weight of the product.
     *
     * @param weight weight of the product in grams.
     */
    public void setWeight(int weight) {
        if (weight < 0) throw new IllegalArgumentException("Product weight has to be bigger than 0");
        this.weight = weight;
    }

    /**
     * Sets the nutritional value {@link NutritionalValue} of the product.
     *
     * @param nutritionalValue nutritional values of the product.
     */
    public void setNutritionalValue(NutritionalValue nutritionalValue) {
        Objects.requireNonNull(nutritionalValue, "The product needs to have nutritional values");
        this.nutritionalValue = nutritionalValue;
    }

    /**
     * Sets the price of the product.
     *
     * @param price price of the product.
     */
    public void setPrice(double price) {
        if (price < 0) throw new IllegalArgumentException("Product price has to be bigger than 0");
        this.price = price;
    }

    /**
     * Sets the name of the product.
     *
     * @param name name of the product.
     */
    public void setName(String name) {
        Objects.requireNonNull(name, "Product needs to have a name.");
        requireNonBlankOrEmpty(name, "Product name");
        this.name = name;
    }

    /**
     * Sets the description of the product.
     *
     * @param description description of the product.
     */
    public void setDescription(String description) {
        Objects.requireNonNull(description, "Product needs to have a description.");
        requireNonBlankOrEmpty(description, "Product description");
        this.description = description;
    }

    @Generated
    public UUID getId() {
        return id;
    }

    @Generated
    public double getPrice() {
        return price;
    }

    @Generated
    public String getDescription() {
        return description;
    }

    @Generated
    public String getName() {
        return name;
    }

    @Generated
    public NutritionalValue getNutritionalValue() {
        return nutritionalValue;
    }

    @Generated
    public int getWeight() {
        return weight;
    }

    /**
     * Checks if value is correct.
     *
     * @param value         The value that needs to be checked.
     * @param attributeType Type of attribute used in the exception.
     * @throws IllegalArgumentException When value is blank or empty.
     */
    private static void requireNonBlankOrEmpty(String value, String attributeType) {
        if (value.isEmpty() || value.isBlank()) {
            throw new IllegalArgumentException(attributeType + " cannot be blank or empty");
        }
    }
}
