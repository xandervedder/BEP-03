package nl.softwarestrijders.waiter.order.adapters.http.repository;

import nl.softwarestrijders.waiter.order.adapters.http.repository.dto.ProductDto;
import nl.softwarestrijders.waiter.order.ports.http.ProductRepository;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

public class HttpProductRepository implements ProductRepository {
    private String rootPath;
    private RestTemplate restTemplate;

    public HttpProductRepository(String rootPath, RestTemplate restTemplate) {
        this.rootPath = rootPath;
        this.restTemplate = restTemplate;
    }

    @Override
    public boolean productExists(UUID productId) {
        var uri = URI.create(this.rootPath + "/" + productId);
        try {
            this.restTemplate.getForObject(uri, ProductDto.class);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
