package com.ibrahimmohurlu.payment_service.model;

import lombok.Data;
import java.time.LocalDateTime;


@Data
public class UserPackage {

    private Long id;


    private User user;


    private Package userPackage;


    private LocalDateTime purchaseDate;


    private LocalDateTime expirationDate;


    private LocalDateTime confirmationDate;


    private boolean isConfirmed;


    private int remainingListingAllowance;

}

