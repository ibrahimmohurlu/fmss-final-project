package com.ibrahimmohurlu.payment_service.producer.dto;

import com.ibrahimmohurlu.payment_service.model.UserPackage;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PackagePurchasedMessageDto {
    private UserPackage userPackage;
    private String userEmail;
}
