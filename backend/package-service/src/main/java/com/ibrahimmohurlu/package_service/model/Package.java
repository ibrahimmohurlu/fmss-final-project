package com.ibrahimmohurlu.package_service.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "package")
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

    @Column(name = "ad_limit", nullable = false)
    private int adLimit;

//    @OneToMany(mappedBy = "aPackage")
//    private List<UserPackage> userPackages;
}
