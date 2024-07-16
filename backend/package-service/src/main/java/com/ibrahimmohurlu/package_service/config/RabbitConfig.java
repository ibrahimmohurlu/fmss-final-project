package com.ibrahimmohurlu.package_service.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String PACKAGE_PURCHASE_EXCHANGE = "packagePurchaseExchange";
    public static final String PAYMENT_CONFIRMATION_QUEUE = "paymentConfirmationQueue";
    public static final String PAYMENT_QUEUE = "paymentQueue";
    public static final String PAYMENT_ROUTING_KEY = "paymentRoutingKey";
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(PACKAGE_PURCHASE_EXCHANGE);
    }

    @Bean
    public Queue paymentQueue() {
        return new Queue(PAYMENT_QUEUE);
    }

    @Bean
    public Binding paymentBinding(DirectExchange directExchange, Queue paymentQueue) {
        return BindingBuilder.bind(paymentQueue).to(directExchange).with(PAYMENT_ROUTING_KEY);
    }


    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
        return rabbitTemplate;
    }
}

