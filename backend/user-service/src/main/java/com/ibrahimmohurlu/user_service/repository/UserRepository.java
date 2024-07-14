package com.ibrahimmohurlu.user_service.repository;

import com.ibrahimmohurlu.user_service.model.User;
import com.ibrahimmohurlu.user_service.model.UserPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);

    @Query("SELECT up FROM User u JOIN u.userPackages up WHERE u.email = :email AND up.remainingListingAllowance > 0 AND up.expirationDate > CURRENT_DATE")
    List<UserPackage> findUsersWithActiveUserPackages(@Param("email") String email);
}
