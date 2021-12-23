package nl.softwarestrijders.waiter.order.adapters.http.repository;

import nl.softwarestrijders.waiter.order.adapters.http.repository.dto.ProductDto;
import nl.softwarestrijders.waiter.order.ports.http.ProductRepository;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

public class HttpProductRepository implements ProductRepository {
    private final String rootPath;
    private final RestTemplate restTemplate;

    public HttpProductRepository(String rootPath, RestTemplate restTemplate) {
        this.rootPath = rootPath;
        this.restTemplate = restTemplate;
    }

    @Override
    public Optional<ProductDto> findProduct(UUID productId) {
        var uri = URI.create(this.rootPath + "/" + productId);
        try {
            return Optional.ofNullable(this.restTemplate.getForObject(uri, ProductDto.class));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
