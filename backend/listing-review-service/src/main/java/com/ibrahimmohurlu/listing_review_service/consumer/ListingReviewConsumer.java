package com.ibrahimmohurlu.listing_review_service.consumer;


import com.ibrahimmohurlu.listing_review_service.config.RabbitConfig;
import com.ibrahimmohurlu.listing_review_service.consumer.dto.Listing;
import com.ibrahimmohurlu.listing_review_service.consumer.dto.ListingReviewMessageDto;
import com.ibrahimmohurlu.listing_review_service.producer.RabbitMessageProducer;
import com.ibrahimmohurlu.listing_review_service.producer.dto.ListingReviewConfirmMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ListingReviewConsumer {


    private final RabbitMessageProducer producer;

    @RabbitListener(queues = RabbitConfig.LISTING_REVIEW_QUEUE)
    public void handleListingReview(ListingReviewMessageDto message) {
        Listing listing = message.getListing();
        log.info("Reviewing the listing:{}", message);
        /*
         * We can run various algorithms to review the content of the listing
         * to determine we should activate it or not we are just logging
         * in here.
         * */
        if (listing.getTitle().isBlank()) {
            // if listing title is blank do nothing just return
            return;
        }
        ListingReviewConfirmMessage confirmMessage = new ListingReviewConfirmMessage(listing.getId());
        producer.sendListingReviewConfirmationMessage(confirmMessage);
    }
}
