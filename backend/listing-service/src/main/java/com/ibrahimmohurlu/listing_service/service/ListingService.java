package com.ibrahimmohurlu.listing_service.service;

import com.ibrahimmohurlu.listing_service.dto.CreateListingRequestDto;
import com.ibrahimmohurlu.listing_service.model.Listing;
import com.ibrahimmohurlu.listing_service.model.ListingStatus;
import com.ibrahimmohurlu.listing_service.model.User;
import com.ibrahimmohurlu.listing_service.repository.ListingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListingService {
    private final ListingRepository listingRepository;

    public List<Listing> getAllListings() {
        return listingRepository.findByStatusEquals(ListingStatus.ACTIVE);
    }

    public Listing createListing(CreateListingRequestDto dto, Long userId) {
        User user = new User();
        user.setId(userId);
        Listing listing = new Listing();
        listing.setTitle(dto.getTitle());
        listing.setDescription(dto.getDescription());
        listing.setPrice(dto.getPrice());
        listing.setUser(user);
        return listingRepository.save(listing);
    }
}
