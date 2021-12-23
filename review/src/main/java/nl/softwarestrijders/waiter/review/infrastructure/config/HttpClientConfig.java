package nl.softwarestrijders.waiter.review.infrastructure.config;

import nl.softwarestrijders.waiter.review.core.port.storage.CustomerRepository;
import nl.softwarestrijders.waiter.review.core.port.storage.DeliveryRepository;
import nl.softwarestrijders.waiter.review.core.port.storage.ProductRepository;
import nl.softwarestrijders.waiter.review.infrastructure.driven.storage.HttpCustomerRepository;
import nl.softwarestrijders.waiter.review.infrastructure.driven.storage.HttpDeliveryRepository;
import nl.softwarestrijders.waiter.review.infrastructure.driven.storage.HttpProductRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HttpClientConfig {
    @Value("${http-client.root-path.customer}")
    private String customerPath;
    @Value("${http-client.root-path.delivery}")
    private String deliveryPath;
    @Value("${http-client.root-path.product}")
    private String productPath;

    @Bean
    public CustomerRepository customerRepository() {
        return new HttpCustomerRepository(new RestTemplate(), this.customerPath);
    }

    @Bean
    public DeliveryRepository deliveryRepository() {
        return new HttpDeliveryRepository(new RestTemplate(), this.deliveryPath);
    }

    @Bean
    public ProductRepository productRepository() {
        return new HttpProductRepository(new RestTemplate(), this.productPath);
    }
}
