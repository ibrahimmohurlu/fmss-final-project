package com.ibrahimmohurlu.listing_review_service.producer;


import com.ibrahimmohurlu.listing_review_service.config.RabbitConfig;
import com.ibrahimmohurlu.listing_review_service.producer.dto.ListingReviewConfirmMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitMessageProducer {


    private final RabbitTemplate rabbitTemplate;

    public void sendListingReviewConfirmationMessage(ListingReviewConfirmMessage message) {
        rabbitTemplate.convertAndSend(RabbitConfig.LISTING_REVIEW_CONFIRM_EXCHANGE, RabbitConfig.LISTING_REVIEW_CONFIRM_ROUTING_KEY, message);
        log.info("{} message sent to {} queue", message, RabbitConfig.LISTING_REVIEW_CONFIRM_QUEUE);
    }
}

