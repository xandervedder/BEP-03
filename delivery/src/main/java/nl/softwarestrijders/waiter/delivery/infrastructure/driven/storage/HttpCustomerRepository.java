package nl.softwarestrijders.waiter.delivery.infrastructure.driven.storage;

import nl.softwarestrijders.waiter.delivery.core.port.storage.CustomerRepository;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Objects;
import java.util.UUID;

public class HttpCustomerRepository implements CustomerRepository {
    private final String rootPath;
    private final RestTemplate client;

    public HttpCustomerRepository(String rootPath, RestTemplate client) {
        this.rootPath = rootPath;
        this.client = client;
    }

    @Override
    public CustomerDeliveryAddress getCustomerDeliveryAddress(UUID customerId) {
        var uri = URI.create(String.format("%s/customer/%s/retrieve-address", this.rootPath, customerId));
        var customerDeliveryAddress = this.client.getForObject(uri, CustomerDeliveryAddress.class);
        return Objects.requireNonNull(customerDeliveryAddress);
    }
}
