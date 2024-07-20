package com.ibrahimmohurlu.listing_review_service.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String LISTING_REVIEW_QUEUE = "listingReviewQueue";
    public static final String LISTING_REVIEW_CONFIRM_QUEUE = "listingReviewConfirmQueue";
    public static final String LISTING_REVIEW_CONFIRM_EXCHANGE = "listingReviewConfirmExchange";
    public static final String LISTING_REVIEW_CONFIRM_ROUTING_KEY = "listingReviewConfirmRoutingKey";

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(LISTING_REVIEW_CONFIRM_EXCHANGE);
    }

    @Bean
    public Queue listingReviewConfirmQueue() {
        return new Queue(LISTING_REVIEW_CONFIRM_QUEUE);
    }

    @Bean
    public Binding listingReviewBinding(DirectExchange directExchange, Queue listingReviewConfirmQueue) {
        return BindingBuilder.bind(listingReviewConfirmQueue).to(directExchange).with(LISTING_REVIEW_CONFIRM_ROUTING_KEY);
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
