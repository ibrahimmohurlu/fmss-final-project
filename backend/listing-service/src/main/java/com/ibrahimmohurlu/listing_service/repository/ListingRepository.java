package com.ibrahimmohurlu.listing_service.repository;

import com.ibrahimmohurlu.listing_service.model.Listing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListingRepository extends JpaRepository<Listing, Long> {
}
