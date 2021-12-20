package nl.softwarestrijders.waiter.order;

import nl.softwarestrijders.waiter.order.common.annotation.TestExcludeGenerated;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
@TestExcludeGenerated
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
