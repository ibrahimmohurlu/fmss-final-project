package com.ibrahimmohurlu.user_service.model;

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

    @Column(name = "ad_count", nullable = false)
    private int adCount;

    @PrePersist
    protected void onCreate() {
        this.purchaseDate = LocalDateTime.now();
        this.expirationDate = this.purchaseDate.plusDays(userPackage.getDurationDays());
        this.adCount = userPackage.getAdLimit();
    }

    // Getters and setters
}

