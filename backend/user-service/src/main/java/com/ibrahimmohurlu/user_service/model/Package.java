package com.ibrahimmohurlu.user_service.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "packages")
@Data
public class Package {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private double price;

    @Column(name = "duration_days", nullable = false)
    private int durationDays;

    @Column(name = "listing_allowance", nullable = false)
    private int listingAllowance;

//    @OneToMany(mappedBy = "userPackage")
//    private List<UserPackage> userPackages;

    // Getters and setters
}

