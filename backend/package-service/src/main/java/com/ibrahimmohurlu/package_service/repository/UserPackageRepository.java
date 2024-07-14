package com.ibrahimmohurlu.package_service.repository;

import com.ibrahimmohurlu.package_service.model.UserPackage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPackageRepository extends JpaRepository<UserPackage, Long> {

}
