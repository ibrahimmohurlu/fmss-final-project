package com.ibrahimmohurlu.user_service.service;

import com.ibrahimmohurlu.user_service.model.User;
import com.ibrahimmohurlu.user_service.model.UserPackage;
import com.ibrahimmohurlu.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public List<UserPackage> getActiveUserPackagesByEmail(String email) {
        return userRepository.findUsersWithActiveUserPackages(email);
    }
}
