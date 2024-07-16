package com.ibrahimmohurlu.payment_service.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String PACKAGE_PURCHASE_EXCHANGE = "packagePurchaseExchange";
    public static final String PAYMENT_QUEUE = "paymentQueue";
    public static final String NOTIFICATION_QUEUE = "notificationQueue";

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(PACKAGE_PURCHASE_EXCHANGE);
    }

    @Bean
    public Queue paymentQueue() {
        return new Queue(PAYMENT_QUEUE);
    }

    @Bean
    public Queue notificationQueue() {
        return new Queue(NOTIFICATION_QUEUE);
    }

    @Bean
    public Binding paymentBinding(FanoutExchange fanoutExchange, Queue paymentQueue) {
        return BindingBuilder.bind(paymentQueue).to(fanoutExchange);
    }

    @Bean
    public Binding notificationBinding(FanoutExchange fanoutExchange, Queue notificationQueue) {
        return BindingBuilder.bind(notificationQueue).to(fanoutExchange);
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

