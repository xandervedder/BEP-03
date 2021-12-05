package nl.softwarestrijders.waiter.delivery.core.domain;

import nl.softwarestrijders.waiter.delivery.core.common.Generated;
import nl.softwarestrijders.waiter.delivery.core.domain.exceptions.InvalidHouseNumberException;
import nl.softwarestrijders.waiter.delivery.core.domain.exceptions.InvalidPostalCodeException;
import nl.softwarestrijders.waiter.delivery.core.domain.exceptions.InvalidStreetNameException;

import java.util.Objects;

/**
 * This is the address where the order should be delivered and does not have to be the same as the Customer's address.
 */
public record DeliveryAddress(String streetName, int houseNumber, String addition, String postalCode, String city) {
    public DeliveryAddress {
        Objects.requireNonNull(streetName);
        if (streetName.length() < 1)
            throw new InvalidStreetNameException();
        if (houseNumber < 1)
            throw new InvalidHouseNumberException();
        if (addition == null)
            addition = "";
        if (!postalCode.matches("[1-9][0-9]{3}[a-zA-Z]{2}"))
            throw new InvalidPostalCodeException();
        Objects.requireNonNull(city);
    }
}
