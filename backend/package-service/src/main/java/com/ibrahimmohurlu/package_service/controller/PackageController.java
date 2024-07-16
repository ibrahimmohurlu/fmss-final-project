package com.ibrahimmohurlu.package_service.controller;

import com.ibrahimmohurlu.package_service.dto.UserByEmailResponseDto;
import com.ibrahimmohurlu.package_service.model.Package;
import com.ibrahimmohurlu.package_service.model.UserPackage;
import com.ibrahimmohurlu.package_service.producer.RabbitMessageProducer;
import com.ibrahimmohurlu.package_service.producer.dto.PackagePurchasedMessageDto;
import com.ibrahimmohurlu.package_service.service.PackageService;
import com.ibrahimmohurlu.package_service.service.UserServiceWebClient;
import com.ibrahimmohurlu.package_service.utils.AuthorizationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/packages")
@RequiredArgsConstructor
public class PackageController {

    private final PackageService packageService;
    private final UserServiceWebClient userService;
    private final RabbitMessageProducer producer;

    @GetMapping
    public ResponseEntity<List<Package>> getAllPackages() {
        return ResponseEntity.ok(packageService.getAllPackages());
    }

    @PostMapping("/{packageId}/purchase")
    public ResponseEntity<UserPackage> purchasePackageById(@PathVariable Long packageId, @RequestHeader("Authorization") String authHeader) {
        Optional<Package> optionalPackage = packageService.getPackageById(packageId);
        if (optionalPackage.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Package purchasedPackage = optionalPackage.get();
        String userEmail = AuthorizationUtils.getUsernameFromAuthHeader(authHeader);

        UserByEmailResponseDto userResponse = userService.getUserByEmail(userEmail, authHeader);

        UserPackage createdPackage = packageService.createUserPackage(purchasedPackage, userResponse.getId());

        PackagePurchasedMessageDto messageDto = PackagePurchasedMessageDto
                .builder()
                .userPackage(createdPackage)
                .userEmail(userEmail)
                .build();
        producer.sendPackagePurchaseMessage(messageDto);
        return ResponseEntity.noContent().build();
    }
}
