package nl.softwarestrijders.waiter.review.infrastructure.driven.storage;

import nl.softwarestrijders.waiter.review.core.port.storage.DeliveryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

public class HttpDeliveryRepository implements DeliveryRepository {
    private final RestTemplate template;
    private final String rootPath;

    public HttpDeliveryRepository(RestTemplate template, String rootPath) {
        this.template = template;
        this.rootPath = rootPath;
    }

    @Override
    public boolean existsById(UUID deliveryId) {
        ResponseEntity<Void> response;
        try {
            response = this.template.getForEntity(String.format("%s/%s", this.rootPath, deliveryId), Void.class);
        } catch (Exception e) {
            return false;
        }
        return response.getStatusCode().is2xxSuccessful();
    }
}
