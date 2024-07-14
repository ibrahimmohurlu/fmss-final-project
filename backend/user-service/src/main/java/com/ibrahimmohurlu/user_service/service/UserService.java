package com.ibrahimmohurlu.user_service.service;

import com.ibrahimmohurlu.user_service.model.User;
import com.ibrahimmohurlu.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Optional<User> getUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }
}
