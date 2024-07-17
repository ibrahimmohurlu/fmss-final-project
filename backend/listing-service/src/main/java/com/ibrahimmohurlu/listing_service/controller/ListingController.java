package com.ibrahimmohurlu.listing_service.controller;

import com.ibrahimmohurlu.listing_service.converters.ListingDtoConverter;
import com.ibrahimmohurlu.listing_service.dto.CreateListingRequestDto;
import com.ibrahimmohurlu.listing_service.dto.ListingResponseDto;
import com.ibrahimmohurlu.listing_service.dto.UserPackageResponseDto;
import com.ibrahimmohurlu.listing_service.model.Listing;
import com.ibrahimmohurlu.listing_service.service.ListingService;
import com.ibrahimmohurlu.listing_service.service.UserServiceWebClient;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/listings")
@RequiredArgsConstructor
public class ListingController {

    private final ListingService listingService;
    private final UserServiceWebClient userServiceWebClient;

    @GetMapping
    public ResponseEntity<List<ListingResponseDto>> getAllListings() {
        List<Listing> allListings = listingService.getAllListings();
        List<ListingResponseDto> listingResponseDtos = ListingDtoConverter.listingToListingResponseDto(allListings);
        return ResponseEntity.ok(listingResponseDtos);
    }

    @PostMapping
    public ResponseEntity<Void> createListing(
            @RequestHeader("Authorization") String authHeader,
            @RequestHeader(value = "USER_ID") Long userId,
            @Valid @RequestBody CreateListingRequestDto createListingRequestDto
    ) {
        List<UserPackageResponseDto> activeUserPackages = userServiceWebClient.getUserActivePackages(authHeader);
         /*
          because endpoint returns us only valid (usable) packages
          we only need to check for the list size in here
         */
        if (activeUserPackages.isEmpty()) {
            return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).build();
        }

        Listing createdListing = listingService.createListing(createListingRequestDto, userId);
        // TODO:send message to listing-review service
        // TODO:return 201 created with location header
        return ResponseEntity.noContent().build();
    }
}
