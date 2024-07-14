package com.ibrahimmohurlu.package_service.service;

import com.ibrahimmohurlu.package_service.model.Package;
import com.ibrahimmohurlu.package_service.repository.PackageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PackageService {
    private final PackageRepository packageRepository;

    public List<Package> getAllPackages() {
        return packageRepository.findAll();
    }
    public Optional<Package> getPackageById(Long id){
        return packageRepository.findById(id);
    }
}
