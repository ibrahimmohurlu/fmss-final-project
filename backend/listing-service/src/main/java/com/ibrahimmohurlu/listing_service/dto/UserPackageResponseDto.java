package com.ibrahimmohurlu.listing_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserPackageResponseDto {

    private Long id;

    @JsonProperty("package_id")
    private Long packageId;
    @JsonProperty("purchase_date")
    private LocalDateTime purchaseDate;
    @JsonProperty("expiration_date")
    private LocalDateTime expirationDate;
    @JsonProperty("remaining_listing_allowance")
    private int remainingListingAllowance;
}
