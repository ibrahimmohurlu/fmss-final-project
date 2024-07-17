package com.ibrahimmohurlu.listing_service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateListingRequestDto {

    private String title;

    private String description;

    private double price;
}
