package nl.softwarestrijders.waiter.review.infrastructure.config;

import nl.softwarestrijders.waiter.review.infrastructure.driven.messaging.RabbitMqEventPublisher;
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

    @Value("${messaging.queue.create-review}")
    private String createReviewMessageQueue;
    @Value("${messaging.routing-key.create-review}")
    private String createReviewRoutingKey;
    @Value("${messaging.queue.delete-review}")
    private String deleteReviewMessageQueue;
    @Value("${messaging.routing-key.delete-review}")
    private String deleteReviewRoutingKey;
    @Value("${messaging.queue.edit-review}")
    private String editReviewMessageQueue;
    @Value("${messaging.routing-key.edit-review}")
    private String editReviewRoutingKey;

    @Bean
    public TopicExchange waiterExchange() {
        return new TopicExchange(this.waiterExchangeName);
    }

    @Bean
    public Queue createReviewQueue() {
        return QueueBuilder.durable(this.createReviewMessageQueue).build();
    }

    @Bean
    public Queue deleteReviewQueue() {
        return QueueBuilder.durable(this.deleteReviewMessageQueue).build();
    }

    @Bean
    public Queue editReviewQueue() {
        return QueueBuilder.durable(this.editReviewMessageQueue).build();
    }

    @Bean
    public Binding createReviewBinding() {
        return BindingBuilder.bind(this.createReviewQueue()).to(waiterExchange()).with(this.createReviewRoutingKey);
    }

    @Bean
    public Binding deleteReviewBinding() {
        return BindingBuilder.bind(this.deleteReviewQueue()).to(waiterExchange()).with(this.deleteReviewRoutingKey);
    }

    @Bean
    public Binding editReviewBinding() {
        return BindingBuilder.bind(this.editReviewQueue()).to(waiterExchange()).with(this.editReviewRoutingKey);
    }

    @Bean
    public RabbitMqEventPublisher eventPublisher(RabbitTemplate template) {
        return new RabbitMqEventPublisher(template, this.waiterExchangeName);
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory(this.host, this.port);
    }

    @Bean
    public Jackson2JsonMessageConverter converter(Jackson2ObjectMapperBuilder builder) {
        var objectMapper = builder.createXmlMapper(false).build();
        var converter = new Jackson2JsonMessageConverter(objectMapper);
        converter.setAlwaysConvertToInferredType(true);

        return converter;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(Jackson2JsonMessageConverter converter) {
        var template = new RabbitTemplate();
        template.setConnectionFactory(this.connectionFactory());
        template.setMessageConverter(converter);

        return template;
    }
}
