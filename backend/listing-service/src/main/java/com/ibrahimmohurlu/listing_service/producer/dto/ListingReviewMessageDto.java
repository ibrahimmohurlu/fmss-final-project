package com.ibrahimmohurlu.listing_service.producer.dto;


import com.ibrahimmohurlu.listing_service.model.Listing;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ListingReviewMessageDto {
    private Listing listing;

}
