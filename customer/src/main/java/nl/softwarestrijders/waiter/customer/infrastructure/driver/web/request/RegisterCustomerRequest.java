package nl.softwarestrijders.waiter.customer.infrastructure.driver.web.request;

public record RegisterCustomerRequest(String firstname, String lastname, String email,
                                      int housenumber, String addition, String street, String postalCode, String city) {

}
