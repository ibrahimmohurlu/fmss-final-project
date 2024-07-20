package com.ibrahimmohurlu.listing_review_service.consumer.dto;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
@Data
public class Listing {

    private Long id;

    private String title;

    private String description;

    private double price;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private ListingStatus status;
}
