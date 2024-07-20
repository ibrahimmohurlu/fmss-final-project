package com.ibrahimmohurlu.listing_service.dto;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class UpdateListingRequestDto {

    private String title;

    private String description;

    @Positive(message = "price must be positive")
    private Double price;
}
