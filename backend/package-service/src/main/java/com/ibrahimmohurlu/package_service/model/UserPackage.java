package com.ibrahimmohurlu.package_service.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_packages")
@Data
public class UserPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "package_id", nullable = false)
    private Package userPackage;

    @Column(name = "purchase_date", nullable = false)
    private LocalDateTime purchaseDate;

    @Column(name = "expiration_date", nullable = false)
    private LocalDateTime expirationDate;

    @Column(name = "confirmation_date")
    private LocalDateTime confirmationDate;

    @Column(name = "is_confirmed")
    private boolean isConfirmed;

    @Column(name = "remaining_listing_allowance", nullable = false)
    private int remainingListingAllowance;

    @PrePersist
    protected void onCreate() {
        this.purchaseDate = LocalDateTime.now();
        this.expirationDate = this.purchaseDate.plusDays(userPackage.getDurationDays());
        this.remainingListingAllowance = userPackage.getListingAllowance();
        this.isConfirmed = false;
        this.confirmationDate = null;
    }

    // Getters and setters
}

