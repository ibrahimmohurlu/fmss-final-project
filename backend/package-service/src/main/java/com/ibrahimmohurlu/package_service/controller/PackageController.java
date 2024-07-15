package com.ibrahimmohurlu.package_service.controller;

import com.ibrahimmohurlu.package_service.dto.UserByEmailResponseDto;
import com.ibrahimmohurlu.package_service.model.Package;
import com.ibrahimmohurlu.package_service.model.UserPackage;
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

        UserPackage createPackage = packageService.createUserPackage(purchasedPackage, userResponse.getId());
        // TODO: send async message to payment service
        return ResponseEntity.noContent().build();
    }
}
