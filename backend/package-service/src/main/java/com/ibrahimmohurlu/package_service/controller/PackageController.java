package com.ibrahimmohurlu.package_service.controller;

import com.ibrahimmohurlu.package_service.model.Package;
import com.ibrahimmohurlu.package_service.service.PackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/packages")
@RequiredArgsConstructor
public class PackageController {

    private final PackageService packageService;

    @GetMapping
    public ResponseEntity<List<Package>> getAllPackages() {
        return ResponseEntity.ok(packageService.getAllPackages());
    }

    @PostMapping("/{packageId}/purchase")
    public ResponseEntity<Void> purchasePackageById(@PathVariable Long packageId) {
        Optional<Package> optionalPackage = packageService.getPackageById(packageId);
        if (optionalPackage.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
