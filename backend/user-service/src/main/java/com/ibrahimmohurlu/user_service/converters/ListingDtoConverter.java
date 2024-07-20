package com.ibrahimmohurlu.user_service.converters;


import com.ibrahimmohurlu.user_service.dto.ListingResponseDto;
import com.ibrahimmohurlu.user_service.model.Listing;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ListingDtoConverter {

    public static List<ListingResponseDto> listingToListingResponseDto(List<Listing> listingList) {
        return listingList
                .stream()
                .map(ListingDtoConverter::listingToListingResponseDto)
                .collect(Collectors.toList());
    }

    public static ListingResponseDto listingToListingResponseDto(Listing listing) {
        return ListingResponseDto
                .builder()
                .id(listing.getId())
                .userId(listing.getUser().getId())
                .title(listing.getTitle())
                .description(listing.getDescription())
                .price(listing.getPrice())
                .status(listing.getStatus())
                .updatedAt(listing.getUpdatedAt())
                .createdAt(listing.getCreatedAt())
                .build();
    }
}
