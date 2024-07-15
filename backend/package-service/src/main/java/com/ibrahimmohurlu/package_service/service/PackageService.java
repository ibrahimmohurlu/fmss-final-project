package com.ibrahimmohurlu.package_service.service;

import com.ibrahimmohurlu.package_service.model.Package;
import com.ibrahimmohurlu.package_service.model.User;
import com.ibrahimmohurlu.package_service.model.UserPackage;
import com.ibrahimmohurlu.package_service.repository.PackageRepository;
import com.ibrahimmohurlu.package_service.repository.UserPackageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PackageService {
    private final PackageRepository packageRepository;
    private final UserPackageRepository userPackageRepository;

    public List<Package> getAllPackages() {
        return packageRepository.findAll();
    }

    public Optional<Package> getPackageById(Long id) {
        return packageRepository.findById(id);
    }

    public UserPackage createUserPackage(Package purchasePackage, Long userId) {

        User user = new User();
        user.setId(userId);
        UserPackage userPackage = new UserPackage();
        userPackage.setUserPackage(purchasePackage);
        userPackage.setUser(user);
        return userPackageRepository.save(userPackage);
    }
}
