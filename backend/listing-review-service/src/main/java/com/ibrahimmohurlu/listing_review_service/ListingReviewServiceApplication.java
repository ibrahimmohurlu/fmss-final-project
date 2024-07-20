package com.ibrahimmohurlu.listing_review_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ListingReviewServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ListingReviewServiceApplication.class, args);
	}

}
