package nl.softwarestrijders.waiter.customer.infrastructure.config;

import nl.softwarestrijders.waiter.customer.infrastructure.driven.messaging.RabbitMqEventPublisher;
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
	@Value("${messaging.queue.customer}")
	private String customerMessageQueue;
	@Value("${messaging.routing-key.customer}")
	private String customerRoutingKey;

	@Value("${messaging.queue.customer.review}")
	private String customerReviewMessageQueue;
	@Value("${messaging.routing-key.customer.review}")
	private String customerReviewRoutingKey;

	@Value("${messaging.queue.customer.order}")
	private String customerOrderMessageQueue;
	@Value("${messaging.routing-key.customer.order}")
	private String customerOrderRoutingKey;

	@Bean
	public TopicExchange waiterExchange() {
		return new TopicExchange(this.waiterExchangeName);
	}

	@Bean
	public Queue customerQueue() {
		return QueueBuilder.durable(this.customerMessageQueue).build();
	}

	@Bean
	public Binding customerBinding() {
		return BindingBuilder.bind(customerQueue()).to(waiterExchange()).with(this.customerRoutingKey);
	}

	@Bean
	public Queue customerReviewQueue() {
		return QueueBuilder.durable(this.customerReviewMessageQueue).build();
	}

	@Bean
	public Binding customerReviewBinding() {
		return BindingBuilder.bind(customerReviewQueue()).to(waiterExchange()).with(this.customerReviewRoutingKey);
	}

	@Bean
	public Queue customerOrderQueue() {
		return QueueBuilder.durable(this.customerOrderMessageQueue).build();
	}

	@Bean
	public Binding customerOrderBinding() {
		return BindingBuilder.bind(customerOrderQueue()).to(waiterExchange()).with(this.customerOrderRoutingKey);
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
