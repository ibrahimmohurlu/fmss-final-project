package com.ibrahimmohurlu.listing_service.producer;


import com.ibrahimmohurlu.listing_service.config.RabbitConfig;
import com.ibrahimmohurlu.listing_service.producer.dto.ListingReviewMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMessageProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendListingReviewMessage(ListingReviewMessageDto message) {
        rabbitTemplate.convertAndSend(RabbitConfig.LISTING_REVIEW_EXCHANGE, RabbitConfig.LISTING_REVIEW_ROUTING_KEY, message);
    }
}

