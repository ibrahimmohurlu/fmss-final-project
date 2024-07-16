package com.ibrahimmohurlu.notification_service.consumer.dto;


import com.ibrahimmohurlu.notification_service.model.UserPackage;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PackagePurchasedMessageDto {
    private UserPackage userPackage;
    private String userEmail;
}
