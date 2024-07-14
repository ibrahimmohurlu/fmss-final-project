package com.ibrahimmohurlu.listing_service.controller;

import com.ibrahimmohurlu.listing_service.converters.ListingResponseDtoConverter;
import com.ibrahimmohurlu.listing_service.dto.ListingResponseDto;
import com.ibrahimmohurlu.listing_service.model.Listing;
import com.ibrahimmohurlu.listing_service.service.ListingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/listings")
@RequiredArgsConstructor
public class ListingController {
    private final ListingService listingService;

    @GetMapping
    public ResponseEntity<List<ListingResponseDto>> getAllListings() {
        List<Listing> allListings = listingService.getAllListings();
        List<ListingResponseDto> listingResponseDtos = ListingResponseDtoConverter.listingToListingResponseDto(allListings);
        return ResponseEntity.ok(listingResponseDtos);
    }
}
