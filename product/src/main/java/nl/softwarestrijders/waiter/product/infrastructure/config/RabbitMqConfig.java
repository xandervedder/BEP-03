package nl.softwarestrijders.waiter.product.infrastructure.config;

import nl.softwarestrijders.waiter.product.infrastructure.driven.messaging.RabbitMqEventPublisher;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * Class for the Rabbit Messaging Queue Configuration.
 * All values can be configured in application.properties.
 */
@Configuration
public class RabbitMqConfig {
    @Value("${spring.rabbitmq.host}")
    private String host;
    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${messaging.exchange.waiter}")
    private String waiterExchangeName;
    @Value("${messaging.queue.create-product}")
    private String createProductQueue;
    @Value("${messaging.routing-key.create-product}")
    private String createProductRoutingKey;
    @Value("${messaging.queue.delete-product}")
    private String deleteProductQueue;
    @Value("${messaging.routing-key.delete-product}")
    private String deleteProductRoutingKey;

    @Bean
    public TopicExchange waiterExchange() {
        return new TopicExchange(this.waiterExchangeName);
    }

    @Bean
    public Queue createProductQueue() { return QueueBuilder.durable(this.createProductQueue).build(); }

    @Bean
    public Queue deleteProductQueue() { return QueueBuilder.durable(this.deleteProductQueue).build(); }

    @Bean
    public Binding createProductBinding() {
        return BindingBuilder.bind(this.createProductQueue()).to(waiterExchange()).with(this.createProductRoutingKey);
    }

    @Bean
    public Binding deleteProductBinding() {
        return BindingBuilder.bind(this.createProductQueue()).to(this.waiterExchange()).with(this.deleteProductRoutingKey);
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory(this.host, this.port);
    }


    @Bean
    public RabbitMqEventPublisher eventPublisher(RabbitTemplate template) {
        return new RabbitMqEventPublisher(template, this.waiterExchangeName);
    }
    
    @Bean
    public Jackson2JsonMessageConverter messageConverter(Jackson2ObjectMapperBuilder builder) {
        var objectMapper = builder.createXmlMapper(false).build();
        var converter = new Jackson2JsonMessageConverter(objectMapper);
        converter.setAlwaysConvertToInferredType(true);

        return converter;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(Jackson2JsonMessageConverter messageConverter) {
        var rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(this.connectionFactory());
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }
}
