package nl.softwarestrijders.waiter.customer.core.application.command;

public record RegisterCustomer(String firstname, String lastname, String email,
                               int housenumber, String addition, String street, String postalCode, String city) {
}
