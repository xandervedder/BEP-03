package nl.softwarestrijders.waiter.order.adapters.http.repository;

import nl.softwarestrijders.waiter.order.ports.http.ProductRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HttpClientConfig {
    @Value("${http-client.root-path.product}")
    private String rootPath;

    @Bean
    public ProductRepository httpProductRepository(RestTemplate restTemplate) {
        return new HttpProductRepository(rootPath, restTemplate);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
