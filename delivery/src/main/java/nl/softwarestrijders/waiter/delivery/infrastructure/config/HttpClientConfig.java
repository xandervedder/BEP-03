package nl.softwarestrijders.waiter.delivery.infrastructure.config;

import nl.softwarestrijders.waiter.delivery.infrastructure.driven.storage.HttpCustomerRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HttpClientConfig {
    @Value("${http-client.root-path.customer}")
    private String rootPath;

    @Bean
    public HttpCustomerRepository httpDeliveryRepository() {
        return new HttpCustomerRepository(rootPath, restTemplate());
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
