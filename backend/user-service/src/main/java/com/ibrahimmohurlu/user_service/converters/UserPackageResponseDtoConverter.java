package com.ibrahimmohurlu.user_service.converters;

import com.ibrahimmohurlu.user_service.dto.UserPackageResponseDto;
import com.ibrahimmohurlu.user_service.model.UserPackage;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserPackageResponseDtoConverter {
    public static List<UserPackageResponseDto> UserPackageListToUserPackageDtoList(List<UserPackage> userPackages) {
        return userPackages
                .stream()
                .map(p -> UserPackageResponseDto.builder()
                        .id(p.getId())
                        .packageId(p.getUserPackage().getId())
                        .expirationDate(p.getExpirationDate())
                        .purchaseDate(p.getPurchaseDate())
                        .remainingListingAllowance(p.getRemainingListingAllowance())
                        .build())
                .collect(Collectors.toList());
    }
}
