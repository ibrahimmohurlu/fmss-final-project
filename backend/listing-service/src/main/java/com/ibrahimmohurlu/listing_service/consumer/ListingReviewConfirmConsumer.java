package com.ibrahimmohurlu.listing_service.consumer;


import com.ibrahimmohurlu.listing_service.config.RabbitConfig;
import com.ibrahimmohurlu.listing_service.consumer.dto.ListingReviewConfirmMessage;
import com.ibrahimmohurlu.listing_service.model.Listing;
import com.ibrahimmohurlu.listing_service.model.ListingStatus;
import com.ibrahimmohurlu.listing_service.repository.ListingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ListingReviewConfirmConsumer {

    private final ListingRepository listingRepository;

    @RabbitListener(queues = RabbitConfig.LISTING_REVIEW_CONFIRM_QUEUE)
    public void handleListingReviewConfirmMessage(ListingReviewConfirmMessage message) {
        Long listingId = message.getListingId();
        Optional<Listing> optionalListing = listingRepository.findById(listingId);
        if (optionalListing.isEmpty()) {
            log.error("Confirmed listing with id:{} not found", listingId);
        }
        Listing listing = optionalListing.get();
        listing.setStatus(ListingStatus.ACTIVE);
        listingRepository.save(listing);
        log.info("Listing with id:{} status updated to {}", listingId, ListingStatus.ACTIVE);
    }
}
