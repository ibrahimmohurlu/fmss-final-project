package com.ibrahimmohurlu.listing_service.repository;

import com.ibrahimmohurlu.listing_service.model.Listing;
import com.ibrahimmohurlu.listing_service.model.ListingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListingRepository extends JpaRepository<Listing, Long> {
    List<Listing> findByStatusEquals(ListingStatus status);
}
