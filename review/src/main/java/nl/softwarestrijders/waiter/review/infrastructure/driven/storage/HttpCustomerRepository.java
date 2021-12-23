package nl.softwarestrijders.waiter.review.infrastructure.driven.storage;

import nl.softwarestrijders.waiter.review.core.port.storage.CustomerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

public class HttpCustomerRepository implements CustomerRepository {
    private final RestTemplate template;
    private final String rootPath;

    public HttpCustomerRepository(RestTemplate template, String rootPath) {
        this.template = template;
        this.rootPath = rootPath;
    }

    @Override
    public boolean existsById(UUID customerId) {
        ResponseEntity<Void> response;
        try {
            response = this.template.getForEntity(String.format("%s/%s", this.rootPath, customerId), Void.class);
        } catch (Exception e) {
            return false;
        }
        return response.getStatusCode().is2xxSuccessful();
    }
}
