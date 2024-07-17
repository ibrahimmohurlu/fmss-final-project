package com.ibrahimmohurlu.listing_service.controller;

import com.ibrahimmohurlu.listing_service.converters.ListingDtoConverter;
import com.ibrahimmohurlu.listing_service.dto.CreateListingRequestDto;
import com.ibrahimmohurlu.listing_service.dto.ListingResponseDto;
import com.ibrahimmohurlu.listing_service.dto.UserPackageResponseDto;
import com.ibrahimmohurlu.listing_service.model.Listing;
import com.ibrahimmohurlu.listing_service.service.ListingService;
import com.ibrahimmohurlu.listing_service.service.UserServiceWebClient;
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
    public ResponseEntity<Void> createListing(@RequestHeader("Authorization") String authHeader, @RequestBody CreateListingRequestDto createListingRequestDto) {
        System.out.println(createListingRequestDto);
        List<UserPackageResponseDto> activeUserPackages = userServiceWebClient.getUserActivePackages(authHeader);
        System.out.println(activeUserPackages);
        if (activeUserPackages.isEmpty()) {
            return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).build();
        }
        // check for is paackage allowance and decrement using other service (package service maybe)
        return ResponseEntity.ok().build();
    }
}
