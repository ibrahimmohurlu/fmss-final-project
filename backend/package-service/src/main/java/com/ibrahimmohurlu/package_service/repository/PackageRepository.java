package com.ibrahimmohurlu.package_service.repository;

import com.ibrahimmohurlu.package_service.model.Package;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageRepository extends JpaRepository<Package,Long> {
}
