package com.ibrahimmohurlu.listing_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ibrahimmohurlu.listing_service.model.ListingStatus;
import com.ibrahimmohurlu.listing_service.model.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ListingResponseDto {

    private Long id;

    @JsonProperty("user_id")
    private Long userId;

    private String title;

    private String description;

    private double price;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    private ListingStatus status;
}
