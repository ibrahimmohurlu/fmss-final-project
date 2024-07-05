package com.ibrahimmohurlu.user_service.repository;

import com.ibrahimmohurlu.user_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
