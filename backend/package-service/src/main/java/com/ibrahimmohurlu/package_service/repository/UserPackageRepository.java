package com.ibrahimmohurlu.package_service.repository;

import com.ibrahimmohurlu.package_service.model.UserPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface UserPackageRepository extends JpaRepository<UserPackage, Long> {
    @Query("SELECT up FROM UserPackage up WHERE up.user.id = :userId AND up.expirationDate > :currentDate AND up.remainingListingAllowance > 0")
    List<UserPackage> findActiveUserPackage(@Param("userId") Long userId, @Param("currentDate") LocalDateTime currentDate);
}
