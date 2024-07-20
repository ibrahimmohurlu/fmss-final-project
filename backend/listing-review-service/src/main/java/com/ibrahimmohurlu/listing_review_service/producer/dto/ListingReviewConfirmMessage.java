package com.ibrahimmohurlu.listing_review_service.producer.dto;

import lombok.Data;

@Data
public class ListingReviewConfirmMessage {
    private Long listingId;

    public ListingReviewConfirmMessage(Long listingId) {
        this.listingId = listingId;
    }

    public ListingReviewConfirmMessage() {
    }
}
