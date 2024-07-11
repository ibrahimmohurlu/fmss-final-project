package com.ibrahimmohurlu.listing_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/listings")
public class ListingController {
    @GetMapping
    public String get() {
        return "hello from listing service";
    }
}
