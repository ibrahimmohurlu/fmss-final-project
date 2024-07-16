package com.ibrahimmohurlu.payment_service.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@Document("payment")
public class Payment {
    @Id
    private String id;
    private String userEmail;
    private Package purchasedPackage;
    private LocalDateTime purchaseDate;
}
