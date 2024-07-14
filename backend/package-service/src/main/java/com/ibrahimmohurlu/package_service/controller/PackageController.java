package com.ibrahimmohurlu.package_service.controller;

import com.ibrahimmohurlu.package_service.model.Package;
import com.ibrahimmohurlu.package_service.service.PackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

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
    public ResponseEntity<Void> purchasePackage(@PathVariable Long packageId, Principal principal) {
        System.out.println(principal);
        return ResponseEntity.ok().build();
    }
}
