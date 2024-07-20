package com.ibrahimmohurlu.listing_service.service;

import com.ibrahimmohurlu.listing_service.dto.CreateListingRequestDto;
import com.ibrahimmohurlu.listing_service.dto.UpdateListingRequestDto;
import com.ibrahimmohurlu.listing_service.exception.ForbiddenException;
import com.ibrahimmohurlu.listing_service.exception.NotFoundException;
import com.ibrahimmohurlu.listing_service.model.Listing;
import com.ibrahimmohurlu.listing_service.model.ListingStatus;
import com.ibrahimmohurlu.listing_service.model.User;
import com.ibrahimmohurlu.listing_service.repository.ListingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public void getUpdateListing(Long listingId, Long userId, UpdateListingRequestDto updateListingRequestDto) {

        Optional<Listing> optionalListing = listingRepository.findById(listingId);

        String title = updateListingRequestDto.getTitle();
        String desc = updateListingRequestDto.getDescription();
        Double price = updateListingRequestDto.getPrice();

        if (optionalListing.isEmpty()) {
            throw new NotFoundException("Listing with id:" + listingId + " not found");
        }

        Listing listing = optionalListing.get();

        if (!listing.getUser().getId().equals(userId)) {
            throw new ForbiddenException("Forbidden");
        }

        if (title != null && !title.isBlank()) {
            listing.setTitle(title);
        }
        if (desc != null && !desc.isBlank()) {
            listing.setDescription(desc);
        }
        if (price != null) {
            listing.setPrice(price);
        }

        listingRepository.save(listing);
    }

    public void deleteById(Long listingId, Long userId) {

        Optional<Listing> optionalListing = listingRepository.findById(listingId);

        if (optionalListing.isEmpty()) {
            throw new NotFoundException("Listing with id:" + listingId + " not found");
        }

        Listing listing = optionalListing.get();

        if (!listing.getUser().getId().equals(userId)) {
            throw new ForbiddenException("Forbidden");
        }

        listingRepository.deleteById(listing.getId());
    }
}
