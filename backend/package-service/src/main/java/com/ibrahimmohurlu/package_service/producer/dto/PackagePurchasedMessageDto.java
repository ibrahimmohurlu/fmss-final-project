package com.ibrahimmohurlu.package_service.producer.dto;

import com.ibrahimmohurlu.package_service.model.UserPackage;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PackagePurchasedMessageDto {
    private UserPackage userPackage;
    private String userEmail;
}
