package nl.softwarestrijders.waiter.delivery.infrastructure.config;

import nl.softwarestrijders.waiter.delivery.infrastructure.driven.messaging.RabbitMqEventPublisher;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class RabbitMqConfig {
    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${messaging.exchange.waiter}")
    private String waiterExchangeName;

    @Value("${messaging.queue.delivery}")
    private String deliveryQueueName;
    @Value("order")
    private String orderQueueName;

    @Value("${messaging.routing-key.delivery}")
    private String deliveryRoutingKey;
    @Value("order.#")
    private String orderRoutingKey;

    @Bean
    public TopicExchange waiterExchange() {
        return new TopicExchange(waiterExchangeName);
    }

    @Bean
    public Queue deliveryQueue() {
        return QueueBuilder.durable(deliveryQueueName).build();
    }

    @Bean
    public Binding deliveryBinding() {
        return BindingBuilder.bind(deliveryQueue()).to(waiterExchange()).with(deliveryRoutingKey);
    }

    @Bean
    public Queue orderQueue() {
        return QueueBuilder.durable(orderQueueName).build();
    }

    @Bean
    public Binding orderBinding() {
        return BindingBuilder.bind(orderQueue()).to(waiterExchange()).with(orderRoutingKey);
    }

    @Bean
    public RabbitMqEventPublisher eventPublisher(RabbitTemplate template) {
        return new RabbitMqEventPublisher(template, waiterExchangeName);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(Jackson2JsonMessageConverter converter) {
        var rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory());
        rabbitTemplate.setMessageConverter(converter);

        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter converter(Jackson2ObjectMapperBuilder builder) {
        var objectMapper = builder.createXmlMapper(false).build();
        var converter = new Jackson2JsonMessageConverter(objectMapper);
        converter.setAlwaysConvertToInferredType(true);

        return converter;
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory(host, port);
    }
}
